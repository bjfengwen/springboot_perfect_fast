package com.itstyle.controller;

import com.itstyle.common.ResultData;
import com.itstyle.common.check.CheckParams;
import com.itstyle.service.ISmsService;
import com.itstyle.service.IUserService;
import com.itstyle.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginAndRegisterController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAndRegisterController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private ISmsService smsService;

    @GetMapping({"/sendSms"})
    public ResultData sendSms(String phoneNum) {
        if (StringUtils.isBlank(phoneNum)) {
            LOGGER.debug("sendSms: phoneNum is empty");
            return ResultData.empty();
        }

        if (!CheckParams.isPhone(phoneNum)) {
            LOGGER.debug("sendSms: phoneNum format error");
            return ResultData.format("手机号码格式不正确");
        }

        if (!this.smsService.sendSms(phoneNum)) {
            LOGGER.warn("sendSms: 发送验证码失败！");
            return ResultData.fail("验证码发送失败");
        }

        return ResultData.success();
    }

    @GetMapping({"/login"})
    public ResultData login(String phoneNum, String verificationNum, String pushId, String pushChannelType) {
        try {
            LOGGER.debug("******用户登录开始********");

            if (StringUtils.isBlank(phoneNum)) {
                LOGGER.debug("phoneNum is empty");
                return ResultData.empty();
            }

            if (StringUtils.isBlank(verificationNum)) {
                LOGGER.debug("verificationNum is empty");
                return ResultData.empty();
            }

            if (!CheckParams.isPhone(phoneNum)) {
                LOGGER.debug("phoneNum format error");
                return ResultData.format("手机号错误");
            }

            if (!CheckParams.isVerificationNum(verificationNum, Constants.VERIFICATION_NUMBER_LENGTH)) {
                LOGGER.debug("verificationNum format error");
                return ResultData.format("验证码错误");
            }

            if (!this.smsService.verifyRedisCode(phoneNum, verificationNum)) {
                LOGGER.debug("phoneNum or verificationNum does not exist in redis: {}, {}", phoneNum, verificationNum);
                return ResultData.fail("验证码错误或超时");
            }

            Map map = this.userService.login(phoneNum, pushId, pushChannelType);

            LOGGER.debug("******用户登录结束********");

            return ResultData.success(map);
        } catch (Exception e) {
            LOGGER.error("用户登录错误： {}", e.getMessage());
        }
        return ResultData.error("服务器请求超时");
    }

    @PostMapping({"/replace-token"})
    public ResultData replaceToken(@RequestHeader("userid") String userId) {
        try {
            Map map = this.userService.replaceToken(userId);
            return ResultData.success(map);
        } catch (Exception e) {
            LOGGER.error("用户自动更换token错误：{}", e);
        }
        return ResultData.error("服务器超时");
    }

    @GetMapping({"/logout"})
    public ResultData logout(@RequestHeader("userid") String userId) {
        try {
            LOGGER.debug("用户退出登录");
            this.userService.logout(userId);

            return ResultData.success();
        } catch (Exception e) {
            LOGGER.error("用户退出登录错误：{}");
        }
        return ResultData.error("服务器超时");
    }
}