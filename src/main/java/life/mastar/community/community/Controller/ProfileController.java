package life.mastar.community.community.Controller;

import life.mastar.community.community.dto.PaginationDTO;
import life.mastar.community.community.model.User;
import life.mastar.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 个人中心  控制器
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable("action") String action, Model model,
                          HttpServletRequest request,
                          @RequestParam(name="page" ,defaultValue = "1") Integer page,    //page记录当前页码
                          @RequestParam(name = "size",defaultValue = "5") Integer size){    //size记录每页显示条数
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return "redirect:/";
        }
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的问题");
        }else if("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PaginationDTO paginationDTO = questionService.list(user.getId(),page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        return "/profile";
    }
}
