package life.mastar.community.community.dto;

import lombok.Data;

import java.util.List;

/**
 * 定义了一个标签类
 */
@Data
public class TagDTO {
    /**
     * 标签分类
     */
    private String categoryName;
    /**
     * 标签名
     */
    private List<String> tags;

}
