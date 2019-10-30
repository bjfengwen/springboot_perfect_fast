package com.itstyle.service.impl;

import com.itstyle.common.aspect.ServiceAopLog;
import com.itstyle.common.redis.RedisTokenManager;
import com.itstyle.dao.PushChannelMapper;
import com.itstyle.dao.UserMapper;
import com.itstyle.entity.PushChannel;
import com.itstyle.entity.User;
import com.itstyle.service.IUserService;
import com.itstyle.utils.Constants;
import com.itstyle.utils.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl
        implements IUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PushChannelMapper pushChannelMapper;


    public User getPhoneNumStatus(String phoneNum) throws Exception {
        User user = this.userMapper.getByPhoneNum(phoneNum);
        if (user == null) {
            return null;
        }

        if (Constants.PHONE_NUM_DO_NOT_SEARCH == user.getFindMeByPhone()) {
            return null;
        }

        return user;
    }

    public Map<String, Object> login(String phoneNum, String pushId, String pushChannelType)
            throws Exception {
        User user = this.userMapper.getByPhoneNum(phoneNum);

        if (user == null) {
            LOGGER.debug("用户{}不存在，执行自动注册逻辑。", phoneNum);

            user = new User();
            String userid = RandomUtils.getUserid();
            user.setUserid(userid);
            user.setPhoneNum(phoneNum);
            user.setCountryCode("+86");

            Date currTime = new Date();
            user.setCreateTime(currTime);
            user.setUpdateTime(currTime);

            if (1 == this.userMapper.insertSelective(user)) {
                if ((StringUtils.isBlank(pushId)) || (StringUtils.isBlank(pushChannelType))) {
                    LOGGER.warn("用户id:{} 客户端pushId或pushChannelType为空，不做保存操作！", userid);
                } else {
                    LOGGER.info("新创建用户，保存推送渠道以及推送客户端注册id");

                    pushChannelInsertSelective(userid, pushId, pushChannelType);
                }

                return returnToken(userid, this.userMapper.getById(userid));
            }

        }

        String sendId = isSendPush(user.getUserid(), pushId, pushChannelType);
        if (StringUtils.isNotBlank(sendId)) {
            LOGGER.info("用户{}当前登录设备是新设备，注册id:{}，通知之前设备下线！下线设备注册id为：{}", new Object[]{user.getUserid(), pushId, sendId});


        }

        return returnToken(user.getUserid(), user);
    }

    private Map<String, Object> returnToken(String userid, Object model)
            throws Exception {
        String token = RedisTokenManager.setToken(userid);

        if (StringUtils.isBlank(token)) {
            LOGGER.warn("用户{} set token失败，token为空。", userid);
            throw new RuntimeException("获取用户token失败");
        }

        Map resultMap = new HashMap(Constants.HASH_MAP_DEFAULT_SIZE.intValue());
        resultMap.put("token", token);
        resultMap.put("model", model);

        return resultMap;
    }

    private String isSendPush(String userid, String pushId, String pushChannelType)
            throws Exception {
        if ((StringUtils.isBlank(pushId)) || (StringUtils.isBlank(pushChannelType))) {
            LOGGER.warn("用户{} pushId或pushChannelType为空，不执行推送！", userid);
            return null;
        }

        PushChannel pushChannel = this.pushChannelMapper.getByCondition(userid, pushChannelType);

        if (pushChannel == null) {
            LOGGER.info("用户id:{}未查询到PushChannel渠道 {}, 进行插入操作", userid, pushChannelType);
            pushChannelInsertSelective(userid, pushId, pushChannelType);

            return null;
        }

        if (pushId.equals(pushChannel.getPushId())) {
            LOGGER.debug("用户客户端注册id与上次相同，不做推送处理");
            return null;
        }

        String beforeId = pushChannel.getPushId();

        pushChannel.setPushId(pushId);
        pushChannel.setUpdateTime(new Date());
        this.pushChannelMapper.updateByPrimaryKeySelective(pushChannel);

        return beforeId;
    }

    private int pushChannelInsertSelective(String userid, String pushId, String pushChannelType) throws Exception {
        Date currTime = new Date();
        PushChannel pushChannel = new PushChannel();
        pushChannel.setId(RandomUtils.get32UUID());
        pushChannel.setPushChannelType(pushChannelType);
        pushChannel.setPushId(pushId);
        pushChannel.setUserid(userid);
        pushChannel.setCreateTime(currTime);
        pushChannel.setUpdateTime(currTime);
        return this.pushChannelMapper.insertSelective(pushChannel);
    }

    public User updateUser(User user) {
        user.setUpdateTime(new Date());
        int i = this.userMapper.updateByPrimaryKeySelective(user);

        if (i > 0) {
            return this.userMapper.getById(user.getUserid());
        }
        return null;
    }

    public User getById(String currUserId) {
        return this.userMapper.selectByPrimaryKey(currUserId);
    }

    public List<User> listUser(String[] ids) throws Exception {
        return this.userMapper.listUserByIds(ids);
    }

    @Transactional
    @ServiceAopLog
    public void test() throws Exception {
        System.out.println("测试事务");

        User user = new User();
        user.setUserid(RandomUtils.get36UUID());
        user.setCreateTime(new Date());
        this.userMapper.insertSelective(user);

        PushChannel pushChannle = new PushChannel();
        pushChannle.setId("123");
        pushChannle.setUpdateTime(new Date());
        int i = this.pushChannelMapper.updateByPrimaryKeySelective(pushChannle);

        if (i == 1)
            throw new RuntimeException();
    }

    public void logout(String userId)
            throws Exception {
        User user = this.userMapper.selectByPrimaryKey(userId);

        if (user == null) {
            LOGGER.debug("用户退出登录，当前用户信息不存在！终止操作。userId: {}", userId);
            return;
        }

        LOGGER.debug("用户退出登录，删除token");
        RedisTokenManager.delUserToken(userId);
    }

    public Map<String, Object> replaceToken(String userId)
            throws Exception {
        User user = this.userMapper.getById(userId);
        if (user == null) {
            LOGGER.debug("用户{}更新token，用户不存在", userId);
            throw new RuntimeException("用户不存在");
        }
        return returnToken(userId, user);
    }
}