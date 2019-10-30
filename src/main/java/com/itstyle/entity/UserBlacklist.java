package com.itstyle.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBlacklist
{
  private String blacklistId;
  private String userid;
  private String blacklistUserid;
  private Date createTime;
  private Date updateTime;

  public String getBlacklistId()
  {
    return this.blacklistId;
  }

  public void setBlacklistId(String blacklistId) {
    this.blacklistId = (blacklistId == null ? null : blacklistId.trim());
  }

  public String getUserid() {
    return this.userid;
  }

  public void setUserid(String userid) {
    this.userid = (userid == null ? null : userid.trim());
  }

  public String getBlacklistUserid() {
    return this.blacklistUserid;
  }

  public void setBlacklistUserid(String blacklistUserid) {
    this.blacklistUserid = (blacklistUserid == null ? null : blacklistUserid.trim());
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

  public String toString()
  {
    return "UserBlacklist [blacklistId=" + this.blacklistId + ", userid=" + this.userid + ", blacklistUserid=" + this.blacklistUserid + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "]";
  }
}