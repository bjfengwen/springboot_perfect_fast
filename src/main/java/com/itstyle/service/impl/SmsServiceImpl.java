package com.itstyle.service.impl;

import com.itstyle.common.redis.RedisSmsManager;
import com.itstyle.common.sms.SmsUtils;
import com.itstyle.dto.SmsInfo;
import com.itstyle.service.ISmsService;
import com.itstyle.utils.Constants;
import com.itstyle.utils.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("smsService")
public class SmsServiceImpl
  implements ISmsService
{
  private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

  @Autowired
  private SmsUtils smsUtils;

  public boolean verifyRedisCode(String phoneNum, String verificationNum)
  {
    String rCode = RedisSmsManager.getVerCodeLogin(phoneNum);

    if (StringUtils.isBlank(rCode)) {
      return false;
    }
    if (!verificationNum.equals(rCode)) {
      return false;
    }

    return true;
  }

  public boolean sendSms(String phoneNum)
  {
    SmsInfo smsInfo = new SmsInfo();

    String verificationCode = RandomUtils.getVerCode(Constants.VERIFICATION_NUMBER_LENGTH.intValue());
    smsInfo.setCode(verificationCode);
    smsInfo.setPhone(phoneNum);
    smsInfo.setSignName("大牛手机定位");

//    SendSmsResponse sendSmsResponse = this.smsUtils.sendSms(smsInfo);
//    LOGGER.info("短信接口返回的数据 start----------------");
//
//    LOGGER.info("Code=" + sendSmsResponse.getCode());
//
//    LOGGER.info("Message=" + sendSmsResponse.getMessage());
//
//    LOGGER.info("RequestId=" + sendSmsResponse.getRequestId());
//
//    LOGGER.info("BizId=" + sendSmsResponse.getBizId());
//    LOGGER.info("短信接口返回的数据 end----------------");

//    if ((sendSmsResponse.getCode() != null) && (sendSmsResponse.getCode().equals("OK")))
//    {
//      String result = RedisSmsManager.setLoginVerCode(phoneNum, verificationCode);
//
//      if ((result != null) && ("OK".equalsIgnoreCase(result))) {
//        return true;
//      }
//
//    }
    String result = RedisSmsManager.setLoginVerCode(phoneNum, verificationCode);

    if ((result != null) && ("OK".equalsIgnoreCase(result))) {
      return true;
    }
    return false;
  }
}