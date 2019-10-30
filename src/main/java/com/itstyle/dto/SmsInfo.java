package com.itstyle.dto;

import java.io.Serializable;
import java.util.Date;

public class SmsInfo
  implements Serializable
{
  private static final long serialVersionUID = 3441641905316282619L;
  private String phone;
  private String code;
  private String signName;
  private Date createTime;
  private boolean isDelete;
  private Date expiroTime;
  private Date useTime;

  public String getPhone()
  {
    return this.phone;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public String getCode() {
    return this.code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getSignName() {
    return this.signName;
  }
  public void setSignName(String signName) {
    this.signName = signName;
  }
  public Date getCreateTime() {
    return this.createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public boolean isDelete() {
    return this.isDelete;
  }
  public void setDelete(boolean isDelete) {
    this.isDelete = isDelete;
  }
  public Date getExpiroTime() {
    return this.expiroTime;
  }
  public void setExpiroTime(Date expiroTime) {
    this.expiroTime = expiroTime;
  }
  public Date getUseTime() {
    return this.useTime;
  }
  public void setUseTime(Date useTime) {
    this.useTime = useTime;
  }
}