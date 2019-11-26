package life.mastar.community.community.service;

import life.mastar.community.community.dto.PaginationDTO;
import life.mastar.community.community.dto.QuestionDTO;
import life.mastar.community.community.mapper.QuestionMapper;
import life.mastar.community.community.mapper.UserMapper;
import life.mastar.community.community.model.Question;
import life.mastar.community.community.model.QuestionExample;
import life.mastar.community.community.model.User;
import life.mastar.community.community.model.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 中间层，service，这里的QuestionService可以使用UserMapper和QuestionMapper
 */
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    /**
     * 根据页数，每页问题数获取问题集
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        //获取总页数
        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount / size + 1;
        }
        Integer offSet = size*(page-1);
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offSet, size));//从question表中查所有的数据,并分页
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(question.getCreator());
            List<User> user = userMapper.selectByExample(example);//将question中的creator拿出来到user表中找到这个实体，以便于拿到头像
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//BeanUtils.copyProperties就是把question的属性copy到questionDTO
            questionDTO.setUser(user.get(0));
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    /**
     * “我的问题”
     * @param userId
     * @param page
     * @param size
     * @return
     */
    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(example);
        //获取总页数
        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount / size + 1;
        }
        Integer offSet = size*(page-1);
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);//分页信息
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offSet, size));//从question表中查所有的数据,并分页
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            UserExample example1 = new UserExample();
            example.createCriteria().andIdEqualTo(question.getCreator());
            List<User> user = userMapper.selectByExample(example1);//将question中的creator拿出来到user表中找到这个实体，以便于拿到头像
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//BeanUtils.copyProperties就是把question的属性copy到questionDTO
            questionDTO.setUser(user.get(0));
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    /**
     * 根据问题id获取问题
     * @param id
     * @return
     */
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 根据id删除问题
     * @param id
     * @return
     */
    public void deleteById(Integer id) {
        questionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 点击发布，判断是新建问题还是修改问题
     * @param question
     */
    public void createOrUpdate(Question question) {
        if(question.getId() != null){
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModify(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            System.out.println(question.getId());
            questionMapper.updateByExampleSelective(updateQuestion,example);
        }else{
            //新建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            questionMapper.insert(question);
        }
    }

}
