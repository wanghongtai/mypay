package com.wht.pay.controller;

import com.alipay.api.domain.Video;
import com.wht.pay.entity.Blog;
import com.wht.pay.entity.User;
import com.wht.pay.service.BlogService;
import com.wht.pay.service.UserService;
import com.wht.pay.service.impl.RedisUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/redis")
public class RedisController {

  Logger logger = LogManager.getLogger(RedisController.class);

  @Autowired
  private RedisUtils redisUtils;
  @Autowired
  private UserService userService;
  @Resource
  private BlogService blogService;

  @GetMapping(value="user/{userId}")
  @ResponseBody
  public Object getAllVideo(@PathVariable String userId){

    //如果缓存存在
    boolean hasKey = redisUtils.exists(userId);
    User user = null;
    if(hasKey){
      //获取缓存
      user = (User) redisUtils.get(userId);
      logger.info("从缓存获取的数据"+ user);
    }else{
      //从DB中获取信息
      logger.info("从数据库中获取数据");
      user = userService.getUserById(Integer.parseInt(userId));//根据ID查询
      //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
      redisUtils.set(userId, user,10L, TimeUnit.MINUTES);
      logger.info("数据插入缓存"+user.toString());
    }
    return user;
  }

  @GetMapping(value="blog/all")
  @ResponseBody
  public List<Blog> getAllBlogs(){
    List<Blog> blogs = blogService.getBlogByPage(1,10);
    return blogs;
  }

  @GetMapping(value="/oneUser/{id}")
  @ResponseBody
//  @Cacheable(value = "getOneUser")
  public User getOneUser(@PathVariable String id){
    System.out.println("没redis缓存");
    return userService.getUserById(Integer.parseInt(id));
  }

  @GetMapping(value="/oneBlog/{id}")
  @ResponseBody
//  @Cacheable(value = "getOneUser")
  public Blog getOneBlog(@PathVariable String id){
    System.out.println("没redis缓存");
    Blog blog =  blogService.getBlogById(Integer.parseInt(id));
    return blog;
  }

  /**
   * 事务验证
   * @return
   */
  @GetMapping(value="blog/add")
  @ResponseBody
  public String addBlog(){

    try {
      Blog blog = new Blog();
      blog.setUserId(1);
      blog.setContent("my test");
      blog.setName("test");
      Integer affectRow = blogService.addBlog(blog);
      System.out.println(affectRow);
      return "Transactional YES";
    } catch (Exception e) {
      e.printStackTrace();
      return "Transactional NO";
    }
  }


}
