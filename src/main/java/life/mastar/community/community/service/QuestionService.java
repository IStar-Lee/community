package life.mastar.community.community.service;

import life.mastar.community.community.dto.PaginationDTO;
import life.mastar.community.community.dto.QuestionDTO;
import life.mastar.community.community.mapper.QuestionMapper;
import life.mastar.community.community.mapper.UserMapper;
import life.mastar.community.community.model.Question;
import life.mastar.community.community.model.User;
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
        Integer totalCount = questionMapper.questionCount();
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
        List<Question> questionList = questionMapper.list(offSet,size);//从question表中查所有的数据
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());//将question中的creator拿出来到user表中找到这个实体，以便于拿到头像
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//BeanUtils.copyProperties就是把question的属性copy到questionDTO
            questionDTO.setUser(user);
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
        Integer totalCount = questionMapper.questionCountByUserId(userId);
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
        List<Question> questionList = questionMapper.listByUserId(userId,offSet,size);//根据userid从question表中查所有的数据
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());//将question中的creator拿出来到user表中找到这个实体，以便于拿到头像
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//BeanUtils.copyProperties就是把question的属性copy到questionDTO
            questionDTO.setUser(user);
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
        Question question = questionMapper.getById(id);
        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);//BeanUtils.copyProperties就是把question的属性copy到questionDTO
        questionDTO.setUser(user);
        return questionDTO;
    }

    /**
     * 点击发布，判断是新建问题还是修改问题
     * @param question
     */
    public void createOrUpdate(Question question) {
        if(question.getId() != null){
            //更新
            question.setGmtModify(System.currentTimeMillis());
            questionMapper.update(question);
        }else{
            //新建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModify(question.getGmtCreate());
            questionMapper.create(question);
        }
    }
}
