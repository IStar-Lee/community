package life.mastar.community.community.Controller;

import life.mastar.community.community.constant.UserConstant;
import life.mastar.community.community.dto.AccessTokenDTO;
import life.mastar.community.community.dto.GithubUser;
import life.mastar.community.community.model.User;
import life.mastar.community.community.provider.GitHubProvider;
import life.mastar.community.community.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 获取github上注册的OAuth信息
 */
@Controller
@Slf4j
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    //@Value注解将application.properties的内容同步到这个类中
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param code
     * @param state
     * @param response
     * @return
     */
    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = gitHubProvider.getUser(accessToken);
        if(githubUser != null && githubUser.getId() != null){
            //登录成功，写cookie和session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            if(githubUser.getName() == null || githubUser.getName() == ""){
                user.setName(UserConstant.DEFAULTNAME);
            }else{
                user.setName(githubUser.getName());
            }
            user.setAccountId(String.valueOf(githubUser.getId()));
            if(githubUser.getAvatarUrl() == null || githubUser.getAvatarUrl() == ""){
                user.setName(UserConstant.DEFAULTAVATORURL);
            }else{
                user.setAvatarUrl(githubUser.getAvatarUrl());
            }
            userService.createOrUpdate(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            //登陆失败
            log.error("AuthorizeController/callBack（）获取github用户错误{}",githubUser);
            return "redirect:/";
        }
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
