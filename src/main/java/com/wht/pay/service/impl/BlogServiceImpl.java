package com.wht.pay.service.impl;

import com.wht.pay.dao.BlogDao;
import com.wht.pay.entity.Blog;
import com.wht.pay.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("blogService")
public class BlogServiceImpl implements BlogService {
  @Autowired
  private BlogDao blogDao;

  @Override
  public Blog getBlogById(Integer id) {
    return blogDao.getBlogById(id);
  }

  @Override
  public List<Blog> getBlogByPage(int pageNum, int pageSize) {
    Map<String, Object> data = new HashMap<>();
    data.put("start", (pageNum - 1) * pageSize);
    data.put("pageSize", pageSize);
    return blogDao.getBlogByPage(data);
  }

  @Override
  public Integer addBlog(Blog blog) {
    Assert.notNull(blog, "blog is null");
    Map<String, Object> data = new HashMap<>();

    data.put("userId", blog.getUserId());
    data.put("name", blog.getName());
    data.put("content", blog.getContent());

    LocalDateTime dateTime = LocalDateTime.now();
    data.put("createDate", dateTime);
    data.put("updateTime", dateTime);
    data.put("reviewCount", 0);
    return blogDao.addBlog(data);
  }

}
