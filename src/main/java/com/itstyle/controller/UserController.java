package com.itstyle.controller;

import com.itstyle.common.ResultData;
import com.itstyle.entity.User;
import com.itstyle.service.IUserService;
import com.itstyle.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({"/user"})
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;


    @PostMapping("/update")
    public ResultData updateUser(@RequestBody User user) {
        try {
            LOGGER.debug("用户修改信息");
            User result = this.userService.updateUser(user);
            return ResultData.success(result);
        } catch (Exception e) {
            LOGGER.error("用户修改信息错误： {}", e);
        }
        return ResultData.error("服务器超时");
    }



    @GetMapping({"/list"})
    public ResultData listUser(String[] ids) {
        try {
            if (StringUtils.isAllBlank(ids)) {
                LOGGER.debug("ids is empty");
                return ResultData.empty();
            }

            List<User> userList = this.userService.listUser(ids);

            return ResultData.success(userList);
        } catch (Exception e) {
            LOGGER.debug("批量查询用户信息错误：{}", e);
        }
        return ResultData.error("服务器超时");
    }


}