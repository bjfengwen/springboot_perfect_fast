package com.itstyle.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLocation
{
  private String locationId;
  private String userid;
  private Double longitude;
  private Double latitude;
  private Date createTime;
  private Date updateTime;

  public String getLocationId()
  {
    return this.locationId;
  }

  public void setLocationId(String locationId) {
    this.locationId = (locationId == null ? null : locationId.trim());
  }

  public String getUserid() {
    return this.userid;
  }

  public void setUserid(String userid) {
    this.userid = (userid == null ? null : userid.trim());
  }

  public Double getLongitude() {
    return this.longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return this.latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
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