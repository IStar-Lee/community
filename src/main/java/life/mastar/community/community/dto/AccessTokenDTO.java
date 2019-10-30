package life.mastar.community.community.dto;

import lombok.Data;

/**
 * 注册了github的OAuth信息以后生成的信息，这个实体类就是OAuth信息
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
