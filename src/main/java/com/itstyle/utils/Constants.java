package com.itstyle.utils;

public abstract interface Constants
{
  public static final String DEFAULT_PHONE_I18N_CODE = "+86";
  public static final Integer PHONE_NUM_DO_SEARCH = Integer.valueOf(1);

  public static final Integer PHONE_NUM_DO_NOT_SEARCH = Integer.valueOf(0);

  public static final Integer HASH_MAP_DEFAULT_SIZE = Integer.valueOf(16);

  public static final Integer PAGE_SIZE_DEFAULT = Integer.valueOf(15);

  public static final Integer VERIFICATION_NUMBER_LENGTH = Integer.valueOf(4);
  public static final int SMS_EXPIRATION_TIME = 600;
  public static final String LOCATE_SUCCESS_STATUS = "OK";
  public static final int DEFAULT_INSERT_RESULT_COLUMN = 1;
  public static final int DEFAULT_USR_ACCREDIT_AMOUNT = 0;
  public static final int JEDIS_POOL_MAXTOTAL = 3000;
  public static final int JEDIS_POOL_MAXIDLE = 1000;
  public static final long JEDIS_POOL_MAXWAIT = 1500L;
  public static final boolean JEDIS_POOL_TESTOOBORROW = true;
  public static final boolean JEDIS_POOL_TESTONRETURN = true;
  public static final int REDIS_TIME_OUT = 400;
}