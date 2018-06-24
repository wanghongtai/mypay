package com.wht.pay.service.impl;

import com.wht.pay.dao.UserMapper;
import com.wht.pay.entity.User;
import com.wht.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserMapper userMapper;

  @Override
  public User getUserById(Integer id) {
    return userMapper.getUserById(id);
  }

  @Override
  public List<User> getUserByPage(int pageNum, int pageSize) {
    Map<String, Object> data = new HashMap<>();
    data.put("start", (pageNum - 1) * pageSize);
    data.put("pageSize", pageSize);
    return userMapper.getUserByPage(data);
  }
}
