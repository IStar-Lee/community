package life.mastar.community.community.dto;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
