package com.example.markerhub.mapper;

import com.example.markerhub.entity.Blog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BlogMapper {
    @Insert("insert into m_blog values(#{blog})")
    Blog insertBlog(@Param("blog") Blog blog);

    @Update("update m_blog set title = #{blog.tile}, description = #{blog.description} ,content= #{blog.content} WHERE id = #{blog.id}")
    void update(@Param("blog") Blog blog);

    @Select("select * from m_blog where id = #{id}")
    Blog getBlogDetailById(@Param("id") Long id);

    @Select("select * from m_blog order by created desc limit #{currentPage},#{size}")
    List<Blog> findBlogsByPager(@Param("currentPage") int currentPage, @Param("size") int size);

    @Select("select count(1) from m_blog")
    long countBlogs();


}
