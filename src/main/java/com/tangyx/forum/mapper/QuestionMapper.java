package com.tangyx.forum.mapper;

import com.tangyx.forum.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author tangyx
 * @date 2021/10/8 23:23
 * +
 */
@Mapper
public interface QuestionMapper {

    /*
    *     title varchar(50),
    description text,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    creator int,
    comment_count int default 0,
    view_count int default 0,
    like_count int default 0,
    tag varchar(256)
    * */

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values" +
            "(#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void createQuestion(Question question);

    @Select("select * from question")
    List<Question> list();
}
