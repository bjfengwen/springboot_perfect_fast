/**
 * 
 */
package com.itstyle;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest {
	public static final String userId = "5c88ca56b742a025300d18ba";
	public static final String token = "c37647cf-2136-4c97-a9a1-d5a5f59ed773";
}
