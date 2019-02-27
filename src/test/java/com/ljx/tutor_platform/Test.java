package com.ljx.tutor_platform;

import org.apache.shiro.crypto.hash.Md5Hash;


public class Test {

	public static void main(String[] args) {
		String salt = "da927e73c5a6f233cfb5723baf4fdc13";
		String password = new Md5Hash("111111",salt,3).toString();
		System.out.println(password);
	}
}
