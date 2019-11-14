package life.mastar.community.community.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 个人中心  控制器
 */
@Controller
public class ProfileController {
    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String name, Model model){
        if("questions".equals(name)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if("replies".equals(name)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "/profile";
    }
}
