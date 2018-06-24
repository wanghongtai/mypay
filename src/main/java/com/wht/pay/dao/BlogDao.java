package com.wht.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import com.wht.pay.entity.Blog;

import java.util.List;
import java.util.Map;

@Mapper
public interface BlogDao {
  Blog getBlogById(Integer id);
  List<Blog> getBlogByPage(Map<String,Object> data);
}
