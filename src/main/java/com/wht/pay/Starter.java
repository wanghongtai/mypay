package com.wht.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *  Spring Boot 添加JSP支持
 * （1）           创建Maven web project；
 * （2）           在pom.xml文件添加依赖；
 * （3）           配置application.properties支持jsp
 * （4）           编写测试Controller
 * （5）          编写JSP页面
 * （6）          编写启动类Application.java
 *
 * 1，FreeMarker
 * 2，Groovy
 * 3，Thymeleaf （spring 官网使用这个）
 * 4，Velocity
 * 5，JSP （貌似Spring Boot官方不推荐，STS创建的项目会在src/main/resources 下有个templates 目录，这里就是让我们放模版文件的，然后并没有生成诸如 SpringMVC 中的webapp目录）
 * 不过本文还是选择大家都熟悉的JSP来举例，因为使用JSP与默认支持的模版需要特殊处理，所以拿来举例更好。
 *
 * 1、在pom.xml中排除org.springframework.boot的内置tomcat容器
 * 2、spring-boot入口实现SpringBootServletInitializer接口
 * 补充：SpringBootServletInitializer接口依赖javax.servlet包，需要在pom.xml中引入该包
 */
@SpringBootApplication
public class Starter extends SpringBootServletInitializer {
	/**
	 * 实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行
	 */
	@Override
	protected final SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Starter.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Starter.class, args);
	}
}
