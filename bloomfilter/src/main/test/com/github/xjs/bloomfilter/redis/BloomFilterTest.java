package com.github.xjs.bloomfilter.redis;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年6月19日 上午9:53:51<br/>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BloomFilterTest {

	@Autowired
	RedisService redisService;
	
	@Before
	public void before() {
		boolean ret = redisService.delete("books");
		assertTrue(ret);
	}
	
	@After
	public void after() {
		boolean ret = redisService.delete("books");
		assertTrue(ret);
	}
	
	
	@Test
	public void testBfCreate() {
		boolean ret = redisService.bfCreate("books", 1000, 0.001);
		assertTrue(ret);
	}
	
	@Test
	public void testBfAdd() {
		redisService.bfAdd("books", "c++");
		boolean exists = redisService.bfExists("books", "c++");
		assertTrue(exists);
	}
}
