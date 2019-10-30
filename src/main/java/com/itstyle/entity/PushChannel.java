package com.itstyle.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PushChannel
{
  private String id;
  private String pushId;
  private String pushChannelType;
  private String userid;
  private Boolean isDelete;
  private Date createTime;
  private Date updateTime;

  public String getId()
  {
    return this.id;
  }

  public void setId(String id) {
    this.id = (id == null ? null : id.trim());
  }

  public String getPushId() {
    return this.pushId;
  }

  public void setPushId(String pushId) {
    this.pushId = (pushId == null ? null : pushId.trim());
  }

  public String getPushChannelType() {
    return this.pushChannelType;
  }

  public void setPushChannelType(String pushChannelType) {
    this.pushChannelType = (pushChannelType == null ? null : pushChannelType.trim());
  }

  public String getUserid() {
    return this.userid;
  }

  public void setUserid(String userid) {
    this.userid = (userid == null ? null : userid.trim());
  }

  public Boolean getIsDelete() {
    return this.isDelete;
  }

  public void setIsDelete(Boolean isDelete) {
    this.isDelete = isDelete;
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
    return "PushChannel [id=" + this.id + ", pushId=" + this.pushId + ", pushChannelType=" + this.pushChannelType + ", userid=" + this.userid + ", isDelete=" + this.isDelete + ", createTime=" + this.createTime + ", updateTime=" + this.updateTime + "]";
  }
}