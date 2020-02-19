package life.mastar.community.community.cache;

import life.mastar.community.community.dto.TagDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 存放所有标签
 */
public class TagCache {

    private TagDTO program;

    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        //开发语言
        TagDTO program = new TagDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("javascript", "前端","vue.js", "css", "html", "html5", "node.js", "react.js", "jquery", "css3", "es6", "typescript", "chrome", "npm", "bootstrap", "sass", "less", "chrome-devtools", "firefox", "angular", "coffee", "script", "safari", "postcss", "postman", "fiddler", "webkit", "yarn", "firebug", "edge","php"
                ,"java","node.js","python","c++","c","golang","spring","django","springboot","flask","后端","c#","swoole","ruby","asp.net","ruby-on-rails","scala","rust","lavarel","爬虫"));
        tagDTOS.add(program);
        //数据库
        TagDTO db = new TagDTO();
        db.setCategoryName("数据库");
        db.setTags(Arrays.asList("mysql","redis","mongodb","sql","数据库","json","elasticsearch","nosql","memcached","postgresql","sqlite","mariadb"));
        tagDTOS.add(db);
        //运维
        TagDTO devOps = new TagDTO();
        devOps.setCategoryName("运维");
        devOps.setTags(Arrays.asList("linux","nginx","docker","apache","centos","ubuntu","服务器","负载均衡","运维","ssh","vagrant","容器","jenkins","devops","debian","fabric"));
        tagDTOS.add(devOps);
        //人工智能
        TagDTO AI = new TagDTO();
        AI.setCategoryName("人工智能");
        AI.setTags(Arrays.asList("算法","机器学习","人工智能","深度学习","数据挖掘","tensorflow","神经网络","图像识别","人脸识别","机器人","pytorch","自动驾驶","自然语言处理"));
        tagDTOS.add(AI);
        return tagDTOS;
    }

    public static String filterInvalid(String tags){
        String[] split = tags.split(",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
