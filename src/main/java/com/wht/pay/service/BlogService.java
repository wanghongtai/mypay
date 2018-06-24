package com.wht.pay.service;

import com.wht.pay.entity.Blog;

import java.util.List;

public interface BlogService {

  Blog getBlogById(Integer id);
  List<Blog> getBlogByPage(int start, int pageSize);
}
