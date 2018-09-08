package com.wht.pay.controller;

import com.wht.pay.entity.User;
import com.wht.pay.service.UserService;
import com.wht.pay.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping("/getUser")
  @ResponseBody
  public Object getUser(HttpServletRequest request, Model model) {
    String userId = request.getParameter("id");
    if (!NumberUtils.isValidNumber(userId)) {
      return "id is invalid";
    }
    User user = userService.getUserById(Integer.parseInt(userId));
    return user;
  }

  @RequestMapping("/getUserByPage")
  @ResponseBody
  public Object getUserByPage(HttpServletRequest request, Model model) {
    //String pageNum = request.getParameter("pageNum");
    //String pageSize = request.getParameter("pageSize");
    List<User> users = userService.getUserByPage(1, 10);
    return users;
  }

}
