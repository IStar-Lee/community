package life.mastar.community.community.model;

import lombok.Data;

/**
 * 存放用户信息的数据库字段信息
 */
@Data
public class User {
    private Integer id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModify;
    private String avatarUrl;
}
