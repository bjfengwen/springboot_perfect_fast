package com.itstyle.entity;

import java.util.Date;

public class UserAccredit
{
  private String accreditId;
  private String userid;
  private String accreditToUser;
  private String remarkName;
  private String accreditRemarkName;
  private String accreditStatusRemark;
  private Integer accreditStatus;
  private String accreditMessage;
  private Date createTime;
  private Date updateTime;
  private Boolean isDisabled;
  private Boolean isBlacklist;

  public String getAccreditId()
  {
    return this.accreditId;
  }

  public void setAccreditId(String accreditId) {
    this.accreditId = (accreditId == null ? null : accreditId.trim());
  }

  public String getUserid() {
    return this.userid;
  }

  public void setUserid(String userid) {
    this.userid = (userid == null ? null : userid.trim());
  }

  public String getAccreditToUser() {
    return this.accreditToUser;
  }

  public void setAccreditToUser(String accreditToUser) {
    this.accreditToUser = (accreditToUser == null ? null : accreditToUser.trim());
  }

  public String getRemarkName() {
    return this.remarkName;
  }

  public void setRemarkName(String remarkName) {
    this.remarkName = (remarkName == null ? null : remarkName.trim());
  }

  public String getAccreditRemarkName() {
    return this.accreditRemarkName;
  }

  public void setAccreditRemarkName(String accreditRemarkName) {
    this.accreditRemarkName = (accreditRemarkName == null ? null : accreditRemarkName.trim());
  }

  public String getAccreditStatusRemark() {
    return this.accreditStatusRemark;
  }

  public void setAccreditStatusRemark(String accreditStatusRemark) {
    this.accreditStatusRemark = (accreditStatusRemark == null ? null : accreditStatusRemark.trim());
  }

  public Integer getAccreditStatus() {
    return this.accreditStatus;
  }

  public void setAccreditStatus(Integer accreditStatus) {
    this.accreditStatus = accreditStatus;
  }

  public String getAccreditMessage() {
    return this.accreditMessage;
  }

  public void setAccreditMessage(String accreditMessage) {
    this.accreditMessage = (accreditMessage == null ? null : accreditMessage.trim());
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

  public Boolean getIsDisabled() {
    return this.isDisabled;
  }

  public void setIsDisabled(Boolean isDisabled) {
    this.isDisabled = isDisabled;
  }

  public Boolean getIsBlacklist() {
    return this.isBlacklist;
  }

  public void setIsBlacklist(Boolean isBlacklist) {
    this.isBlacklist = isBlacklist;
  }
}