package life.mastar.community.community.service;

import life.mastar.community.community.dto.CommentDTO;
import life.mastar.community.community.enums.CommentTypeEnum;
import life.mastar.community.community.exception.CustomizeErrorCode;
import life.mastar.community.community.exception.CustomizeException;
import life.mastar.community.community.mapper.CommentMapper;
import life.mastar.community.community.mapper.QuestionExtMapper;
import life.mastar.community.community.mapper.QuestionMapper;
import life.mastar.community.community.mapper.UserMapper;
import life.mastar.community.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 处理问题或评论回复的service
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    /**
     * 回复评论到数据库
     */
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复的是评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {
            //回复的是问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }

    /**
     * 根据问题的id首先找到这个问题的评论
     * @param id
     * @return
     */
    public List<CommentDTO> listByQuestionId(Long id) {
        //首先根据问题的id查出这个问题的所有评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id)
                .andTypeEqualTo(CommentTypeEnum.QUESTION.getType());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        if (comments.size() == 0){
            return new ArrayList<>();
        }
        //获取到去重的评论人的id
        Set<Long> collect = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<Long>();
        userIds.addAll(collect);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIn(userIds);
        //获取到所有的评论人
        List<User> users = userMapper.selectByExample(userExample);
        //以map（id,user）的形式存储评论人
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //将comment对象转换为CommentDTO形式，并且返回
        List<CommentDTO> commentList = comments.stream().map(comment ->
        {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentator()));
            return commentDTO;
        }).collect(Collectors.toList());
        return commentList;
    }
}
