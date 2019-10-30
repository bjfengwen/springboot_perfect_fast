package com.itstyle.common.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.itstyle.common.properties.AppProperties;
import com.itstyle.dto.SmsInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SmsUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsUtils.class);

    @Autowired
    private AppProperties appProperties;

    public SendSmsResponse sendSms(SmsInfo smsInfo) {
        try {
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            String product = this.appProperties.getAli().getProduct();
            String domain = this.appProperties.getAli().getDomain();
            String accessKeyId = this.appProperties.getAli().getAccessKeyId();
            String accessKeySecret = this.appProperties.getAli().getAccessKeySecret();

            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);

            IAcsClient acsClient = new DefaultAcsClient(profile);

            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setPhoneNumbers(smsInfo.getPhone());

            request.setSignName(smsInfo.getSignName());
            request.setTemplateCode(this.appProperties.getAli().getTemplateCode());
            request.setTemplateParam("{\"code\":\"" + smsInfo.getCode() + "\"}");

            return (SendSmsResponse) acsClient.getAcsResponse(request);
        } catch (Exception e) {
            LOGGER.error("发送短信错误：{}", e);
        }
        return null;
    }
}