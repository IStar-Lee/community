package life.mastar.community.community.dto;

import lombok.Data;

@Data
public class GithubUser {
    /**
     * github名字
     */
    private String name;
    /**
     * 用户github的id
     */
    private Long id;
    /**
     * 用户github的bio，即描述
     */
    private String bio;
    /**
     * 头像
     */
    private String avatarUrl;
}
