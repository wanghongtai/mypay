package com.wht.pay.entity;

import java.time.LocalDateTime;

/**
 * 之前的章节将的实体类属性名和表字段名都是相同的，MyBatis 会自动去映射。那么问题来了，如果实体类属性名和表字段名不相同时，MyBatis 能智能地去映射到吗？答案是：不能。这里用两种解决方案：
 * 1. 在使用 SQL 语句的时候，为每个字段定义别名；
 * 2. 使用 MyBatis 映射文件的 resultMap 标签。
 */
public class Blog {
  private Integer id;
  private String name;
  private String content;
  private Integer userId;
  private LocalDateTime createDate;
  private LocalDateTime updateTime;
  private Integer reviewCount;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public LocalDateTime getCreateDate() {
    return createDate;
  }

  public void setCreateDate(LocalDateTime createDate) {
    this.createDate = createDate;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public Integer getReviewCount() {
    return reviewCount;
  }

  public void setReviewCount(Integer reviewCount) {
    this.reviewCount = reviewCount;
  }
}
