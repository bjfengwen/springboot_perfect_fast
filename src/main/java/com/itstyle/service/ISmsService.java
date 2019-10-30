package com.itstyle.service;

public abstract interface ISmsService
{
  public abstract boolean verifyRedisCode(String paramString1, String paramString2);

  public abstract boolean sendSms(String paramString);
}