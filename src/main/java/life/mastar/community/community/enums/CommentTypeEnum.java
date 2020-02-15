package life.mastar.community.community.enums;

/**
 * 对于评论的type字段的常量值
 */
public enum  CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    CommentTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    /**
     * 判断传过来的type 是不是符合要求的
     * @param type
     * @return
     */
    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(commentTypeEnum.type == type){
                return true;
            }
        }
        return false;
    }
}
