package life.mastar.community.community.Controller;

import life.mastar.community.community.dto.PaginationDTO;
import life.mastar.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 一个入口，欢迎入口
 */
@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name="page" ,defaultValue = "1") Integer page,    //page记录当前页码
                        @RequestParam(name = "size",defaultValue = "10") Integer size,   //size记录每页显示条数
                        @RequestParam(name = "search",required = false) String search){
        PaginationDTO paginationDTO = questionService.list(search,page,size);
        model.addAttribute("paginationDTO",paginationDTO);
        model.addAttribute("search", search);
        return "index";
    }
}
