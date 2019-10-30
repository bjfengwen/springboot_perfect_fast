package com.itstyle.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDeviceSort
{
  private Integer sortId;
  private String userid;
  private String consumerIds;
  private Date createTime;
  private Date updateTime;

  public Integer getSortId()
  {
    return this.sortId;
  }

  public void setSortId(Integer sortId) {
    this.sortId = sortId;
  }

  public String getUserid() {
    return this.userid;
  }

  public void setUserid(String userid) {
    this.userid = (userid == null ? null : userid.trim());
  }

  public String getConsumerIds() {
    return this.consumerIds;
  }

  public void setConsumerIds(String consumerIds) {
    this.consumerIds = (consumerIds == null ? null : consumerIds.trim());
  }

  public Date getCreateTime() {
    return this.createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getUpdateTime() {
    return this.updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}