package com.wht.pay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 动态和静态区别
 * 静态页面的return默认是跳转到/static/index.html，当在pom.xml中引入了thymeleaf组件，
 * 动态跳转会覆盖默认的静态跳转，默认就会跳转到/templates/index.html，注意看两者return代码也有区别，动态没有html后缀。
 * 注：因为xml中引入了thymeleaf组件,所以该controller中/index路径跳转到的是/templates目录，
 * /html和/html2都返回/templates/index.html页面。
 *
 * 重定向
 * 如果在使用动态页面时还想跳转到/static/index.html，可以使用重定向return "redirect:/index.html"。
 */
@Controller
public class HtmlController {
  @GetMapping("/html")
  public String html() {
    return "/index.html";
  }

  @GetMapping("/html2")
  public String html2() {
    return "/index";
  }

  //重定向
  //如果在使用动态页面时还想跳转到/static/index.html，可以使用重定向return "redirect:/index.html"。
  @GetMapping("/html3")
  public String html3() {
    return "redirect:/index.html";
  }
}
