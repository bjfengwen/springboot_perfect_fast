package com.itstyle.dao;

import com.itstyle.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public abstract interface UserMapper
{
  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(User paramUser);

  public abstract int insertSelective(User paramUser);

  public abstract User selectByPrimaryKey(String paramString);

  public abstract int updateByPrimaryKeySelective(User paramUser);

  public abstract int updateByPrimaryKey(User paramUser);

  public abstract User getByPhoneNum(@Param("phoneNum") String paramString);

  public abstract User getById(String paramString);

  public abstract List<User> listUserByIds(String[] paramArrayOfString);

  public abstract int updateUseAmount(List<String> paramList);
}