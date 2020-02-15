package life.mastar.community.community.dto;

import life.mastar.community.community.exception.CustomizeErrorCode;
import lombok.Data;

/**
 * 关注的是在回复问题或评论的时候，相关的异常处理
 */
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO requestDTO = new ResultDTO();
        requestDTO.setCode(code);
        requestDTO.setMessage(message);
        return requestDTO;
    }

    public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
        return errorOf(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultDTO okOf() {
        ResultDTO requestDTO = new ResultDTO();
        requestDTO.setCode(200);
        requestDTO.setMessage("请求成功");
        return requestDTO;
    }
}
