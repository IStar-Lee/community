package life.mastar.community.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private String content;
}
