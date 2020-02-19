package life.mastar.community.community.dto;

import life.mastar.community.community.model.User;
import lombok.Data;

/**
 * 从数据库中查的Comment
 */
@Data
public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
    private Integer commentCount;
    private User user;
}
