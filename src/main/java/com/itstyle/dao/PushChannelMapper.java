package com.itstyle.dao;

import com.itstyle.entity.PushChannel;
import org.apache.ibatis.annotations.Param;

public abstract interface PushChannelMapper
{
  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(PushChannel paramPushChannel);

  public abstract int insertSelective(PushChannel paramPushChannel);

  public abstract PushChannel selectByPrimaryKey(String paramString);

  public abstract int updateByPrimaryKeySelective(PushChannel paramPushChannel);

  public abstract int updateByPrimaryKey(PushChannel paramPushChannel);

  public abstract PushChannel getByCondition(@Param("userid") String paramString1, @Param("pushChannelType") String paramString2);

  public abstract PushChannel getLastPushChannel(String paramString);
}