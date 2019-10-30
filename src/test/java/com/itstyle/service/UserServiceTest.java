/**
 * 
 */
package com.itstyle.service;

import com.alibaba.fastjson.JSON;
import com.itstyle.BaseTest;
import com.itstyle.entity.User;
import com.itstyle.service.impl.UserServiceImpl;
import com.qiniu.util.Json;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserServiceTest extends BaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);
	
	@Autowired
	private IUserService userService;

	@Test
	public void updateTest() throws Exception {

		User user = userService.getById("USER0754071052341104");
		LOGGER.debug("refreshUser 返回结果：{}", JSON.toJSON(user));
		
	}
	
	

	
	

}
