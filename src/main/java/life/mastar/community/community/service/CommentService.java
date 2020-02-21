package life.mastar.community.community.service;

import life.mastar.community.community.dto.CommentDTO;
import life.mastar.community.community.enums.CommentTypeEnum;
import life.mastar.community.community.enums.NotificationStatusEnum;
import life.mastar.community.community.enums.NotificationTypeEnum;
import life.mastar.community.community.exception.CustomizeErrorCode;
import life.mastar.community.community.exception.CustomizeException;
import life.mastar.community.community.mapper.*;
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
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private NotificationMapper notificationMapper;
    /**
     * 回复评论到数据库
     */
    @Transactional
    public void insert(Comment comment,User user) {
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
            dbComment.setCommentCount(1);
            commentExtMapper.incCommentCount(dbComment);
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //发送通知
            createNotify(comment.getCommentator(),dbComment.getParentId(),question.getCreator(), NotificationStatusEnum.UNREAD.getStatus(), NotificationTypeEnum.REPLY_COMMENT.getType(),user.getName(),question.getTitle());
        } else {
            //回复的是问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
            //发送通知
            createNotify(comment.getCommentator(),question.getId(),question.getCreator(), NotificationStatusEnum.UNREAD.getStatus(), NotificationTypeEnum.REPLY_QUESTION.getType(),user.getName(),question.getTitle());
        }
    }

    /**
     * 发送通知 向数据库中set值
     * @param notifier      谁回复了你
     * @param outerId       问题的id
     * @param receiver      谁接受通知
     * @param status        已读  未读
     * @param type          回复的是问题还是评论
     * @param notifierName  回复人的姓名
     * @param outerTitle    问题的标题
     */
    private void createNotify(Long notifier,Long outerId,Long receiver,Integer status,Integer type,String notifierName,String outerTitle){
        if(receiver == notifier){
            return;
        }
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setNotifier(notifier);
        notification.setOuterid(outerId);
        notification.setReceiver(receiver);
        notification.setStatus(status);
        notification.setType(type);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    /**
     * 根据问题的id首先找到这个问题的评论
     * @param id
     * @return
     */
    public List<CommentDTO> listByQuestionOrCommentId(Long id,CommentTypeEnum type) {
        //首先根据问题的id查出这个问题的所有评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        commentExample.setOrderByClause("GMT_CREATE desc");
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
