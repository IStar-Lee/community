package life.mastar.community.community.enums;

/**
 * 通知 类型  分为“回复了问题”和“回复了评论”
 */
public enum NotificationTypeEnum {
    REPLY_QUESTION(0,"回复了问题"),REPLY_COMMENT(1,"回复了评论");

    private Integer type;
    private String typeName;

    NotificationTypeEnum(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public static String nameOf(Integer type){
        for (NotificationTypeEnum value : NotificationTypeEnum.values()) {
            if (value.getType() == type) {
                return value.getTypeName();
            }
        }
        return "";
    }
}
