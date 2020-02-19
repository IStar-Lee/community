package life.mastar.community.community.model;

public class Comment {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.ID
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.PARENT_ID
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    private Long parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.TYPE
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    private Integer type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.COMMENTATOR
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    private Long commentator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.GMT_CREATE
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.GMT_MODIFIED
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    private Long gmtModified;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.LIKE_COUNT
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    private Long likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.CONTENT
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.COMMENT_COUNT
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    private Integer commentCount;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.ID
     *
     * @return the value of comment.ID
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.ID
     *
     * @param id the value for comment.ID
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.PARENT_ID
     *
     * @return the value of comment.PARENT_ID
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.PARENT_ID
     *
     * @param parentId the value for comment.PARENT_ID
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.TYPE
     *
     * @return the value of comment.TYPE
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.TYPE
     *
     * @param type the value for comment.TYPE
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.COMMENTATOR
     *
     * @return the value of comment.COMMENTATOR
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public Long getCommentator() {
        return commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.COMMENTATOR
     *
     * @param commentator the value for comment.COMMENTATOR
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public void setCommentator(Long commentator) {
        this.commentator = commentator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.GMT_CREATE
     *
     * @return the value of comment.GMT_CREATE
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.GMT_CREATE
     *
     * @param gmtCreate the value for comment.GMT_CREATE
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.GMT_MODIFIED
     *
     * @return the value of comment.GMT_MODIFIED
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.GMT_MODIFIED
     *
     * @param gmtModified the value for comment.GMT_MODIFIED
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.LIKE_COUNT
     *
     * @return the value of comment.LIKE_COUNT
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public Long getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.LIKE_COUNT
     *
     * @param likeCount the value for comment.LIKE_COUNT
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.CONTENT
     *
     * @return the value of comment.CONTENT
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.CONTENT
     *
     * @param content the value for comment.CONTENT
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.COMMENT_COUNT
     *
     * @return the value of comment.COMMENT_COUNT
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.COMMENT_COUNT
     *
     * @param commentCount the value for comment.COMMENT_COUNT
     *
     * @mbg.generated Wed Feb 19 12:03:41 CST 2020
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }
}