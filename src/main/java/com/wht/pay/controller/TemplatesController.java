package com.wht.pay.controller;

import com.wht.pay.entity.User;
import com.wht.pay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * RestController：上一篇中用于将返回值转换成json
 * Controller：现在要返回的是一个页面，所以不能再用@RestController，而用普通的@Controller/
 * request.setAttribute(" key ", " hello world ")：这是最基本的语法，向页面转参数 key和value。
 * return "/index"： 跳转到 templates/index.html动态页面，templates目录为spring boot默认配置的动态页面路径。
 */
@Controller
public class TemplatesController {

  @Autowired
  private UserService userService;

  @GetMapping("/templates")
  String test(HttpServletRequest request) {
    //逻辑处理
    request.setAttribute("key", "hello world");
    return "/my_index";
  }

  @GetMapping("/viewUser")
  String viewUser(HttpServletRequest request) {
    //逻辑处理
    List<User> users = this.userService.getUserByPage(1,10);
    request.setAttribute("users", users);
    return "/users";
  }

}
