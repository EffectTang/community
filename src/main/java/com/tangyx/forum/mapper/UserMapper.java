package com.tangyx.forum.mapper;

import com.tangyx.forum.model.User;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @author tangyx
 * @date 2021/9/28 23:42
 * +
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) " +
            "values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insertUser(User user);

    @Select("select * from user where token =#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where account_id =#{account_id}")
    User findByAccount(@Param("account_id")String account);

    @Select("select * from user where ID = #{ID}")
    User findByCreator(@Param("ID") Integer creator);
}
