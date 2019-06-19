package com.github.xjs.bloomfilter.guava;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Test;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author xujs@inspur.com
 *
 * @date 2019年6月19日 上午10:50:28<br/>
 */
public class GuavaTest {
	
	@Test
	public void create() {
		BloomFilter<String> bf = BloomFilter.create(
				Funnels.stringFunnel(Charset.defaultCharset()), 10000, 0.01);
		bf.put("hello");
		bf.put("world");
		bf.put("java");
		boolean ret = bf.mightContain("java");
		assertTrue(ret);
		boolean ret2 = bf.mightContain("php");
		assertFalse(ret2);
	}
}
