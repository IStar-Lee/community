package life.mastar.community.community.mapper;

import life.mastar.community.community.model.Comment;

public interface CommentExtMapper {
    //增加评论数
    int incCommentCount(Comment record);
}
