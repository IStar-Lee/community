package life.mastar.community.community.service;

import life.mastar.community.community.mapper.UserMapper;
import life.mastar.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户信息中间层
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void createOrUpdate(User user){
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if(dbUser != null){
            //执行更新
            dbUser.setGmtModify(System.currentTimeMillis());
            dbUser.setToken(user.getToken());
            dbUser.setName(user.getName());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            userMapper.update(dbUser);
        }else {
            //执行创建
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);
        }
    }
}
