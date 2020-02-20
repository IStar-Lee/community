package life.mastar.community.community.service;

import com.sun.javaws.exceptions.ErrorCodeResponseException;
import life.mastar.community.community.dto.NotificationDTO;
import life.mastar.community.community.dto.PaginationDTO;
import life.mastar.community.community.enums.NotificationStatusEnum;
import life.mastar.community.community.enums.NotificationTypeEnum;
import life.mastar.community.community.exception.CustomizeErrorCode;
import life.mastar.community.community.exception.CustomizeException;
import life.mastar.community.community.mapper.NotificationMapper;
import life.mastar.community.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知service
 */
@Service
public class NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO();
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(example);
        //获取总页数
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        Integer offSet = size * (page - 1);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);//分页信息
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        notificationExample.setOrderByClause("GMT_CREATE DESC");
        List<Notification> notificationList = notificationMapper.selectByExampleWithRowbounds(notificationExample, new RowBounds(offSet, size));//从question表中查所有的数据,并分页
        if (notificationList.size() == 0) {
            return paginationDTO;
        }
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for (Notification notification : notificationList) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOf(notification.getType()));
            notificationDTOList.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOList);
        return paginationDTO;
    }

    /**
     * 返回未读通知数
     *
     * @param id
     * @return
     */
    public Long unreadCount(Long id) {
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(id).andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        long unreadCount = notificationMapper.countByExample(example);
        return unreadCount;
    }

    /**
     * 点击问题，将这个通知更新为已读
     *
     * @param id
     * @param user
     * @return
     */
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null) {
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (notification.getReceiver() != user.getId()) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOf(notification.getType()));
        return notificationDTO;
    }
}
