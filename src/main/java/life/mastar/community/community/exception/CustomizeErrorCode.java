package life.mastar.community.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    DEFAULT_ERROR("服务器冒烟啦，要不等会再试试？"),
    QUESTION_NOT_FOUND("你找的问题不存在，要不换个试试？");
    private String message;
    CustomizeErrorCode(String message) {
       this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
