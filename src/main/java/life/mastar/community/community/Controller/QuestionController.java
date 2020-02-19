package life.mastar.community.community.Controller;

import life.mastar.community.community.dto.CommentDTO;
import life.mastar.community.community.dto.QuestionDTO;
import life.mastar.community.community.enums.CommentTypeEnum;
import life.mastar.community.community.model.Comment;
import life.mastar.community.community.model.Question;
import life.mastar.community.community.service.CommentService;
import life.mastar.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 编辑发布过的问题，用于前后台数据的交互
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;

    /**
     * 点击列表页的问题，进入到question详情页
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        //找到这个问题
        QuestionDTO questionDTO = questionService.getById(id);
        //找到该问题的相关问题
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        //找到该问题的评论
        List<CommentDTO> comments = commentService.listByQuestionOrCommentId(id, CommentTypeEnum.QUESTION);
        questionService.incView(id);
        model.addAttribute("question", questionDTO);
        model.addAttribute("comment", comments);
        model.addAttribute("relatedQuestions" ,relatedQuestions);
        return "question";
    }

    /**
     * 点击详情页的“删除”
     *
     * @param id
     * @param model
     * @param isDeleteQuestion
     * @return
     */
    @GetMapping("/question/{id}/{isDeleteQuestion}")
    public String delete(@PathVariable(name = "id") Long id, Model model,
                         @PathVariable(name = "isDeleteQuestion") String isDeleteQuestion) {
        if ("delete".equals(isDeleteQuestion)) {
            questionService.deleteById(id);
        }
        return "redirect:/";
    }
}
