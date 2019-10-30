package com.itstyle.common.properties.entity;

public class JPushPro
{
  private String masterSecret;
  private String appKey;

  public String getMasterSecret()
  {
    return this.masterSecret;
  }
  public void setMasterSecret(String masterSecret) {
    this.masterSecret = masterSecret;
  }
  public String getAppKey() {
    return this.appKey;
  }
  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }
}