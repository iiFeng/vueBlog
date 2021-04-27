package com.example.markerhub.controller;

import com.example.markerhub.common.Result;
import com.example.markerhub.entity.Blog;
import com.example.markerhub.service.BlogService;
import com.example.markerhub.utils.Pager;
import com.example.markerhub.utils.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blogs")
    public Result getBlogList(@RequestParam(defaultValue = "1") Integer currentPage) {
        Pager<Blog> orders = blogService.findBlogsByPager(currentPage, 5);
        return Result.succ(orders);
    }

    @GetMapping("/blog/{id}")
    public Result getBlogDetail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getBlogDetailById(id);
        return Result.succ(blog);
    }

    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result getEditBlog(@Validated @RequestBody Blog blog) {
        Blog temp = null;
        //进行编辑
        if (blog.getId() != null) {
            temp = blogService.getBlogDetailById(blog.getId());

            if (!temp.getId().equals(ShiroUtil.getProfile().getId())) {
                BeanUtils.copyProperties(blog, temp, "id", "userId", "created", "status");
                blogService.updateBlog(temp);
            } else {
                //当前登录的 id 和 博客所属 id 相等
                Result.fail("没有权限编辑");
            }

        } else {
            //新建 blog
            temp = new Blog();
            temp.setId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
            BeanUtils.copyProperties(blog, temp, "id", "userId", "created", "status");
            blogService.insertBlog(temp);
        }


        return Result.succ(null);
    }

}
