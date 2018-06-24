package com.wht.pay.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 因为是返回页面，所以不能用@RestController,因为Rest包含@ResponseBody返回json格式包含）
 */
@Controller
public class ViewController {

    //从application中读取配置，如取不到默认值为hello jack
    @Value("${application.name:jack}")
    private String name;

    //无论是@RestController还是@Controller都不影响返回页面
    @RequestMapping("/hello")
    @ResponseBody
    public ModelAndView home(ModelMap map){
        map.put("name", name);
        return new ModelAndView("hello");
    }

    //因为有@ResponseBody作用只返回字符串
    @RequestMapping("/hello2")
    @ResponseBody
    public String home2(ModelMap map){
        map.put("name", name);
        return "hello";
    }

    //不要@ResponseBody 就返回页面
    @RequestMapping("/hello3")
    public String home3(ModelMap map){
        map.put("name", name);
        return "hello";
    }

    @RequestMapping("/demo1")
    public String demo1(ModelMap map){
        map.put("name", name);
        return "demo1";
    }
}
