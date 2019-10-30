package com.itstyle.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User
{
  private String userid;
  private String nickname;
  private String username;
  private String password;
  private String salt;
  private String avatarPath;
  private String phoneNum;
  private String countryCode;
  private String userMail;
  private Integer userStatus;
  private Integer findMeByPhone;
  private Date createTime;
  private Date updateTime;
  private Integer maxAmount;
  private Integer useAmount;
  private Integer maxDeviceAmount;
  private Integer useDeviceAmount;

  public String getUserid()
  {
    return this.userid;
  }

  public void setUserid(String userid) {
    this.userid = (userid == null ? null : userid.trim());
  }

  public String getNickname() {
    return this.nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = (nickname == null ? null : nickname.trim());
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = (username == null ? null : username.trim());
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = (password == null ? null : password.trim());
  }

  public String getSalt() {
    return this.salt;
  }

  public void setSalt(String salt) {
    this.salt = (salt == null ? null : salt.trim());
  }

  public String getAvatarPath() {
    return this.avatarPath;
  }

  public void setAvatarPath(String avatarPath) {
    this.avatarPath = (avatarPath == null ? null : avatarPath.trim());
  }

  public String getPhoneNum() {
    return this.phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = (phoneNum == null ? null : phoneNum.trim());
  }

  public String getCountryCode() {
    return this.countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = (countryCode == null ? null : countryCode.trim());
  }

  public String getUserMail() {
    return this.userMail;
  }

  public void setUserMail(String userMail) {
    this.userMail = (userMail == null ? null : userMail.trim());
  }

  public Integer getUserStatus() {
    return this.userStatus;
  }

  public void setUserStatus(Integer userStatus) {
    this.userStatus = userStatus;
  }

  public Integer getFindMeByPhone() {
    return this.findMeByPhone;
  }

  public void setFindMeByPhone(Integer findMeByPhone) {
    this.findMeByPhone = findMeByPhone;
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

  public Integer getMaxAmount() {
    return this.maxAmount;
  }

  public void setMaxAmount(Integer maxAmount) {
    this.maxAmount = maxAmount;
  }

  public Integer getUseAmount() {
    return this.useAmount;
  }

  public void setUseAmount(Integer useAmount) {
    this.useAmount = useAmount;
  }

  public Integer getMaxDeviceAmount() {
    return this.maxDeviceAmount;
  }

  public void setMaxDeviceAmount(Integer maxDeviceAmount) {
    this.maxDeviceAmount = maxDeviceAmount;
  }

  public Integer getUseDeviceAmount() {
    return this.useDeviceAmount;
  }

  public void setUseDeviceAmount(Integer useDeviceAmount) {
    this.useDeviceAmount = useDeviceAmount;
  }
}