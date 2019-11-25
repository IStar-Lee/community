package life.mastar.community.community.Controller;

import life.mastar.community.community.dto.QuestionDTO;
import life.mastar.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 编辑发布过的问题，用于前后台数据的交互
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * 点击详情页的“编辑”
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable (name = "id") Integer id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }

    /**
     * 点击详情页的“删除”
     * @param id
     * @param model
     * @param isDeleteQuestion
     * @return
     */
    @GetMapping("/question/{id}/{isDeleteQuestion}")
    public String delete(@PathVariable (name = "id") Integer id, Model model,
                         @PathVariable (name = "isDeleteQuestion") String isDeleteQuestion){
        if("delete".equals(isDeleteQuestion)){
            questionService.deleteById(id);
        }
        return "redirect:/";
    }
}
