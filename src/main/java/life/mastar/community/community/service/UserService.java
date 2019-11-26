package life.mastar.community.community.service;

import life.mastar.community.community.mapper.UserMapper;
import life.mastar.community.community.model.User;
import life.mastar.community.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息中间层
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void createOrUpdate(User user){
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users = userMapper.selectByExample(example);
        if(users.size() != 0){
            //执行更新
            User updateUser = users.get(0);
            updateUser.setGmtModify(System.currentTimeMillis());
            updateUser.setToken(user.getToken());
            updateUser.setName(user.getName());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            UserExample example1 = new UserExample();
            example1.createCriteria().andIdEqualTo(updateUser.getId());
            userMapper.updateByExampleSelective(updateUser, example1);
        }else {
            //执行创建
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);
        }
    }
}
