package com.ljx.tutor_platform.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.util.ClassUtils;

public class Test {

	public static void main(String[] args) throws IOException {
		File directory = new File("");// 参数为空

		String path = directory.getCanonicalPath();;
		System.out.println(path);
	}
}
