package com.wht.pay.service;

import com.wht.pay.entity.User;

import java.util.List;

public interface UserService {
  User getUserById(Integer id);
  List<User> getUserByPage(int start, int pageSize);
}
