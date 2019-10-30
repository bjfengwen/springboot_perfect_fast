package com.itstyle.service;

import com.itstyle.entity.User;

import java.util.List;
import java.util.Map;

public abstract interface IUserService
{
  public abstract Map<String, Object> login(String paramString1, String paramString2, String paramString3)
    throws Exception;

  public abstract User updateUser(User paramUser);

  public abstract User getPhoneNumStatus(String paramString)
    throws Exception;

  public abstract User getById(String paramString);

  public abstract List<User> listUser(String[] paramArrayOfString)
    throws Exception;

  public abstract void test()
    throws Exception;

  public abstract void logout(String paramString)
    throws Exception;

  public abstract Map<String, Object> replaceToken(String paramString)
    throws Exception;
}