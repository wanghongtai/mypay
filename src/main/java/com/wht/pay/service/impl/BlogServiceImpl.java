package com.wht.pay.service.impl;

import com.wht.pay.dao.BlogDao;
import com.wht.pay.entity.Blog;
import com.wht.pay.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
