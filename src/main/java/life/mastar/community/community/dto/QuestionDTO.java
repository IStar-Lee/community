package life.mastar.community.community.dto;

import life.mastar.community.community.model.User;
import lombok.Data;

/**
 * 相对于Question类多了一个User字段，用来发布问题后在index页面获取头像
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
