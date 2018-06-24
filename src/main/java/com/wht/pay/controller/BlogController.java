package com.wht.pay.controller;

import com.wht.pay.entity.Blog;
import com.wht.pay.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

  @Resource
  private BlogService blogService;

  @RequestMapping("/getBlog")
  @ResponseBody
  public Blog toIndex(HttpServletRequest request, Model model){
    int userId = Integer.parseInt(request.getParameter("id"));
    Blog blog = blogService.getBlogById(userId);
    System.out.println(blog);
    return blog;
  }

  @RequestMapping("/getBlogByPage")
  @ResponseBody
  public Object getBlogByPage(HttpServletRequest request, Model model) {
    List<Blog> users = blogService.getBlogByPage(1,10);
    return users;
  }
}
