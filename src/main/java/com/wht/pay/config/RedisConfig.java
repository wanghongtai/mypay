package com.wht.pay.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
  private static Logger logger = LogManager.getLogger(RedisConfig.class);

  @Value("${spring.redis.host}")
  private String host;
  @Value("${spring.redis.port}")
  private int port;
  @Value("${spring.redis.timeout}")
  private int timeout;

//  @Bean
//  public KeyGenerator wiselyKeyGenerator(){
//    return new KeyGenerator() {
//      @Override
//      public Object generate(Object target, Method method, Object... params) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(target.getClass().getName());
//        sb.append(method.getName());
//        for (Object obj : params) {
//          sb.append(obj.toString());
//        }
//        return sb.toString();
//      }
//    };
//  }

  /**
   * @Bean 和 @ConfigurationProperties
   * 该功能在官方文档是没有提到的，我们可以把@ConfigurationProperties和@Bean和在一起使用。
   * 举个例子，我们需要用@Bean配置一个Config对象，Config对象有a，b，c成员变量需要配置，
   * 那么我们只要在yml或properties中定义了a=1,b=2,c=3，
   * 然后通过@ConfigurationProperties就能把值注入进Config对象中
   * @return
   */
  @Bean
  @ConfigurationProperties(prefix = "spring.redis.pool")
  public JedisPoolConfig getRedisConfig() {
    JedisPoolConfig config = new JedisPoolConfig();
    return config;
  }

  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory factory = new JedisConnectionFactory();
//    factory.setHostName(host);
//    factory.setPort(port);
//    factory.setTimeout(timeout); //设置连接超时时间
    factory.setUsePool(true);
    JedisPoolConfig config = getRedisConfig();
    factory.setPoolConfig(config);
    logger.info("JedisConnectionFactory bean init success.");
    logger.info(this.host+","+factory.getHostName()+","+factory.getDatabase());
    logger.info(this.port+","+factory.getPort());
    logger.info(factory.getPoolConfig().getMaxIdle());
    return factory;
  }
//  @Bean
//  public CacheManager cacheManager(RedisTemplate redisTemplate) {
//    RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//    // Number of seconds before expiration. Defaults to unlimited (0)
//    // cacheManager.setDefaultExpiration(10); //设置key-value超时时间
//    return cacheManager;
//  }
  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

    StringRedisTemplate template = new StringRedisTemplate(factory);
    setSerializer(template); //设置序列化工具，这样ReportBean不需要实现Serializable接口
    template.afterPropertiesSet();
    return template;
  }
  private void setSerializer(StringRedisTemplate template) {
    Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
    ObjectMapper om = new ObjectMapper();
    om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(om);
    template.setValueSerializer(jackson2JsonRedisSerializer);
  }
}
