package life.mastar.community.community.dto;

import lombok.Data;

/**
 * 图片上传，返回的信息
 */
@Data
public class FileDTO {
    private Integer success;
    private String message;
    private  String url;
}
