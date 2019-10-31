package life.mastar.community.community.mapper;

import life.mastar.community.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 对数据库user的操作
 */
@Mapper
@Component
public interface UserMapper {
    /**
     * 插入数据
     * @param user
     */
    @Insert("insert into user(account_id,name,token,gmt_create,gmt_modify,avatar_url) values (#{accountId},#{name},#{token},#{gmtCreate},#{gmtModify},#{avatarUrl})")
    public void insert(User user);

    /**
     * 根据token找
     * @param token
     * @return
     */
    @Select("select * from user where token = #{token}")
    public User findByToken(@Param("token") String token);

    /**
     * 根据id找
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);
}
