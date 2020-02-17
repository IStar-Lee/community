package life.mastar.community.community.dto;

import lombok.Data;

/**
 * 添加评论内容需要用的实体类
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
}
