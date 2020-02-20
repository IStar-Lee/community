package life.mastar.community.community.dto;

import life.mastar.community.community.model.User;
import lombok.Data;

/**
 * 页面上需要的通知的内容
 */
@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private Long notifier;
    private String notifierName;
    private Long outerid;
    private String outerTitle;
    private Integer type;
    private String typeName;//回复了问题   回复了评论
}
