package life.mastar.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页信息，包括要在前端index页面返回的问题列表
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;    //问题列表
    private boolean showPrevious;           //是否展示上一页
    private boolean showNext;               //是否展示下一页
    private boolean showFirstPage;          //是否展示首页
    private boolean showEndPage;            //是否展示最后一页
    private Integer page;                   //当前页
    private List<Integer> pages = new ArrayList<>();            //在前台展示的页码列表
    private Integer totalPage;              //总页数
    //装载信息，返回给前端页面
    public void setPagination(Integer totalCount, Integer size, Integer page) {
        if(totalCount % size == 0){
            totalPage = totalCount/size;
        }else{
            totalPage = totalCount / size + 1;
        }
        //将当前页放到页码列表中
        this.page = page;
        pages.add(page);
        for(int i=1;i<=3;i++){
            //将该页面的前3个页码装到列表中
            if(page - i > 0){
                pages.add(0,page-i);
            }
            //将该页面的后3个页码装到列表中
            if(page + i <= totalPage){
                pages.add(page+i);
            }
        }
        //判断是否显示上一页
        if(page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        //判断是否显示下一页
        if(page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }
        //是否显示首页
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }
        //是否显示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }

    }
}
