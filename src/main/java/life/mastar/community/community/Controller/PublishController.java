package life.mastar.community.community.Controller;

import life.mastar.community.community.mapper.QuestionMapper;
import life.mastar.community.community.mapper.UserMapper;
import life.mastar.community.community.model.Question;
import life.mastar.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
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
        Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null && cookies.length != 0){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if(user != null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if(user == null){
            model.addAttribute("error","您还没有登陆!");
            return "publish";
        }
        Question question = new Question();
        question.setCreator(user.getId());
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModify(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";
    }
}
