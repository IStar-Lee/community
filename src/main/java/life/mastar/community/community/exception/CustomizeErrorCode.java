package life.mastar.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    DEFAULT_ERROR(2000,"服务器冒烟啦，要不等会再试试？"),
    NO_LOGIN(2001,"您当前没有登录，请先登录后重试！"),
    QUESTION_NOT_FOUND(2002,"你找的问题不存在，要不换个试试？"),
    TARGET_PARAM_NOT_FOUND(2003,"未选中任何问题或评论进行回复！"),
    TYPE_PARAM_WRONG(2004,"回复的评论类型错误！"),
    COMMENT_NOT_FOUND(2005,"您回复的评论不存在，要不换一个试试？");
    private Integer code;
    private String message;
    CustomizeErrorCode(Integer code,String message) {
        this.code = code;
       this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
