package life.mastar.community.community.exception;

public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode code) {
        this.message = code.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
