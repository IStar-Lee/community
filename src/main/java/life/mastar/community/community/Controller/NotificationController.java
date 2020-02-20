package life.mastar.community.community.Controller;

import life.mastar.community.community.dto.NotificationDTO;
import life.mastar.community.community.dto.PaginationDTO;
import life.mastar.community.community.enums.NotificationStatusEnum;
import life.mastar.community.community.model.User;
import life.mastar.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 通知  controller
 */
@Controller
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable("id") Long id,HttpServletRequest request){    //size记录每页显示条数
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);

        return "redirect:/question/"+notificationDTO.getOuterid();
    }
}
