package life.mastar.community.community.model;

import lombok.Data;

/**
 * 发布问题，存储了数据库中发布问题的数据库表
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModify;
    private Integer creator;
    private Integer contentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
}
