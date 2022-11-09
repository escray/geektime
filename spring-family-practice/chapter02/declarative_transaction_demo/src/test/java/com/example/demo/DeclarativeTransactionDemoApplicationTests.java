package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

// ErrorCodeDemoApplication
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeclarativeTransactionDemoApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test(expected = CustomDuplicatedKeyException.class)
	public void testThrowingCustomException() {
		jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'aaa')");
		jdbcTemplate.execute("INSERT INTO FOO (ID, BAR) VALUES (1, 'bbb')");
	}

}
