package com.github.xjs.springboot4;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class Springboot4ApplicationTests {

	public static void main(String[] args) {
		MediaType mediaType = MediaType.parseMediaType("application/json");
		System.out.println(mediaType);
	}
}
