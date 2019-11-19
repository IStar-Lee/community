package life.mastar.community.community.Controller;

import life.mastar.community.community.dto.QuestionDTO;
import life.mastar.community.community.model.Question;
import life.mastar.community.community.model.User;
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
 * 发布问题处理
 */
@Controller
public class PublishController {
    @Autowired
    private QuestionService questionService;

    /**
     * 在详情页点击编辑按钮，执行更新操作
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        model.addAttribute("id",questionDTO.getId());
        return "publish";
    }
    /**
     * get请求渲染页面
     * @return
     */
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    /**
     * post请求接受publish页面数据
     */
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("id") Integer id,
            HttpServletRequest request,
            Model model){
        model.addAttribute("tag",tag);
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        if(title == null || title.equals("")){
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if(description == null || description.equals("")){
            model.addAttribute("error","问题补充不能为空！");
            return "publish";
        }
        if(tag == null || tag.equals("")){
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }
        //首先获取到当前登录用户的信息
        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            model.addAttribute("error","您还没有登陆!");
            return "publish";
        }
        Question question = new Question();
        question.setCreator(user.getId());
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
