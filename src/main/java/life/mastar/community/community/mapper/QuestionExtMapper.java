package life.mastar.community.community.mapper;

import life.mastar.community.community.dto.QuestionQueryDTO;
import life.mastar.community.community.model.Question;
import life.mastar.community.community.model.QuestionExample;

import java.util.List;

/**
 * 自定义扩展sql
 */
public interface QuestionExtMapper {
    //增加阅读数
    int incView(Question record);

    //增加评论数
    int incCommentCount(Question record);

    //通过正则表达式找到问题的相关问题
    List<Question> selectRelated(Question question);

    int countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}
