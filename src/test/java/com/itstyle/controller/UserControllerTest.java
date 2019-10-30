/**
 * 
 */
package com.itstyle.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import com.itstyle.BaseTest;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

public class UserControllerTest extends BaseTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserControllerTest.class);
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void updateTest() throws Exception {
		
		String result = mockMvc.perform(
                post("/user/update")
                		.contentType(MediaType.APPLICATION_JSON)
                		.header("userId", userId)
                        .header("token", token)
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        LOGGER.debug("refreshUser 返回结果：{}", result);
		
	}
	
	

	
	

}
