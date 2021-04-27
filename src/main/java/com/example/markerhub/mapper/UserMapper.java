package com.example.markerhub.mapper;

import com.example.markerhub.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from m_user where id = #{id}")
    User findUserById(@Param("id") Long id);

    @Select("select * from m_user where username = #{username}")
    User findUserByUsername(@Param("username") String username);

    @Insert("insert into m_user(username,password) values(#{user.username},#{user.password})")
    void insert(@Param("user") User user);

    @Select("select * from m_user where username=#{username} and password=#{password}")
    User login(@Param("username") String username, @Param("password") String password);
}
