package com.tangyx.forum.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tangyx
 * @date 2021/11/4 22:36
 * +
 */
@Data
public class PageDTO {
    private List<QuestionDTO> questionsdto;
    private boolean isPre;
    private boolean isNext;
    private boolean isFirstPage;
    private boolean isEndPage;

    private Integer totalPage;
    private Integer currentPage;
//    为什么不在方法区内定义
    private List<Integer> pages = new ArrayList<>();;

//  为什么放在实体类中进行数据封装 能否封装成一个公用方法
    public void setPageTion(Integer totalcount, Integer page, Integer size) {
//        计算一共有多少页
        Integer total ;
        total = (totalcount%size==0) ? totalcount/size : totalcount/size+1;
        totalPage = total;

        //是否有上一页
        isPre = !(page==1);
        //是否有下一页
        isNext = !(page.equals(total));

        //展示可见的页数  是一个集合
        currentPage = page;
        pages.add(currentPage);
        for (int i=1;i<4;i++)
        {
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=total){
                pages.add(page+i);
            }
        }

        //        下面这2个判断有问题 结果始终为 true
        //是否展示首页
        isFirstPage = !pages.contains(1);
        //是否展示尾页
        isEndPage = !pages.contains(total);


    }
}
