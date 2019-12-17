package life.mastar.community.community.mapper;

import life.mastar.community.community.model.Question;

/**
 * 自定义扩展sql
 */
public interface QuestionExtMapper {

    int incView(Question record);
}
