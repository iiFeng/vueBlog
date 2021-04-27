package com.example.markerhub.service;

import com.example.markerhub.entity.Blog;
import com.example.markerhub.mapper.BlogMapper;
import com.example.markerhub.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogService {
    @Autowired
    BlogMapper blogMapper;

    public Blog insertBlog(Blog blog) {
        return blogMapper.insertBlog(blog);
    }

    public void updateBlog(Blog blog) {
        blogMapper.update(blog);
    }

    public Blog getBlogDetailById(Long id) {
        return blogMapper.getBlogDetailById(id);
    }

    public Pager<Blog> findBlogsByPager(int page, int size) {
        Pager<Blog> pager = new Pager<Blog>();
        List<Blog> list = blogMapper.findBlogsByPager((page - 1) * size, size);
        pager.setCurrentPage(page);
        pager.setSize(size);
        pager.setRows(list);
        long total = blogMapper.countBlogs();
        pager.setTotal(total);
        int pages = 0;
        if (total % size != 0) {
            pages = (int) (total / size) + 1;
        } else {
            pages = (int) total / size;
        }
        pager.setPages(pages);
        return pager;
    }
}