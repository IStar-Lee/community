package life.mastar.community.community.mapper;

import life.mastar.community.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 处理数据库表question相关信息
 */
@Mapper
@Component
public interface QuestionMapper {
    /**
     * 发布问题，向question表中插入数据
     * @param question
     */
    @Insert("insert into question (title,description,creator,gmt_create,gmt_modify,tag) values(#{title},#{description},#{creator},#{gmtCreate},#{gmtModify},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{offSet},#{size}")
    List<Question> list(Integer offSet, Integer size);
    @Select("select count(1) from question")
    Integer questionCount();
    @Select("select * from question where creator=#{userId} limit #{offSet},#{size}")
    List<Question> listByUserId(Integer userId, Integer offSet, Integer size);
    @Select("select count(1) from question where creator=#{userId}")
    Integer questionCountByUserId(Integer userId);
}
