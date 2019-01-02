package com.carsuper.coahr.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	private MD5() {

	}
	
	public final static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 加密字符串
	 * @param content
	 * @return
	 */
	public static String Md5String(String content) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(content.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("NoSuchAlgorithmException",e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncodingException", e);
		}

		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10){
				hex.append("0");
			}
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString();
	}

	/**
	 * 加密文件
	 * @param file
	 * @return
	 */
	public static String md5ForFile(File file){
		int buffersize = 1024;
		FileInputStream fis = null;
		DigestInputStream dis = null;

		try {
			//创建MD5转换器和文件流
			MessageDigest messageDigest =MessageDigest.getInstance("MD5");
			fis = new FileInputStream(file);
			dis = new DigestInputStream(fis,messageDigest);

			byte[] buffer = new byte[buffersize];
			//DigestInputStream实际上在流处理文件时就在内部就进行了一定的处理
			while (dis.read(buffer) > 0);

			//通过DigestInputStream对象得到一个最终的MessageDigest对象。
			messageDigest = dis.getMessageDigest();

			// 通过messageDigest拿到结果，也是字节数组，包含16个元素
			byte[] array = messageDigest.digest();
			// 同样，把字节数组转换成字符串
			StringBuilder hex = new StringBuilder(array.length * 2);
			for (byte b : array) {
				if ((b & 0xFF) < 0x10){
					hex.append("0");
				}
				hex.append(Integer.toHexString(b & 0xFF));
			}
			return hex.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
