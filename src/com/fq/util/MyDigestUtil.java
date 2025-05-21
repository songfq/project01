package com.fq.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MyDigestUtil {
	public static final String algorithm_name_md5 = "MD5";
	public static final String algorithm_name_sha1 = "SHA-1";
	public static final String algorithm_name_sha256 = "SHA-256";
	public static final String algorithm_name_sha384 = "SHA-384";
	public static final String algorithm_name_sha512 = "SHA-512";
	public static final String algorithm_name_hmacmd5 = "HmacMD5";
	public static final String algorithm_name_hmacsha256 = "HmacSHA256";
	public static final String algorithm_name_hmacsha384 = "HmacSHA384";
	public static final String algorithm_name_hmacsha512 = "HmacSHA512";

	private static final char[] HEX_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * org.apache.commons.codec.binary.Hex
	 * org.springframework.util.DigestUtils
	 **/
	private static char[] encodeHex(byte[] bytes) {
		char[] chars = new char[32];
		for (int i = 0; i < chars.length; i = i + 2) {
			byte b = bytes[i / 2];
			chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
			chars[i + 1] = HEX_CHARS[b & 0xf];
		}
		return chars;
	}

	private static MessageDigest getDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("Could not find MessageDigest with algorithm \"" + algorithm + "\"", ex);
		}
	}

	private static byte[] toBytes(String content) {
		byte[] bytes = null;
		try {
			bytes = content.getBytes("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public static byte[] encryptBase64(byte[] content) {
		byte[] result = Base64.encodeBase64(content);
		return result;
	}

	public static String encryptBase64String(String content) {
		byte[] bytes = toBytes(content);

		String result = "";
		try {
			result = new String(Base64.encodeBase64(bytes), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static byte[] encrypt(String algorithm, byte[] bytes) {
		byte[] encode = encryptBase64(bytes);
		return getDigest(algorithm).digest(encode);
	}

	public static String encryptString(String algorithm, String content) {
		byte[] encryptBytes = encrypt(algorithm, toBytes(content));
		String newEncrypt = new String(encodeHex(encryptBytes));
		return newEncrypt;
	}

	/**
	 * MD5����
	 *
	 * @param content
	 * @return
	 */
	public static String encryptMD5String(String content) {
		return encryptString(algorithm_name_md5, content);
	}

	/**
	 * SHA����
	 *
	 * @param content
	 * @return
	 */
	public static String encryptSHAString(String content) {
		return encryptString(algorithm_name_sha1, content);
	}

	/**
	 * SHA256����
	 *
	 * @param content
	 * @return
	 */
	public static String encryptSHA256String(String content) {
		return encryptString(algorithm_name_sha256, content);
	}

	/**
	 * SHA384����
	 *
	 * @param content
	 * @return
	 */
	public static String encryptSHA384String(String content) {
		return encryptString(algorithm_name_sha384, content);
	}

	/**
	 * SHA512����
	 *
	 * @param content
	 * @return
	 */
	public static String encryptSHA512String(String content) {
		return encryptString(algorithm_name_sha512, content);
	}

	/**
	 * ��ʼ��HMAC��Կ
	 *
	 * @return
	 */
	public static String getHMACKey() {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm_name_hmacmd5);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] encoded = secretKey.getEncoded();
			return encryptBase64String(new String(encoded));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * HMAC ����, ɢ����Ϣ������
	 *
	 * @param key
	 * @param content
	 * @return
	 */
	public static String encryptHMACString(String algorithm, String key, String content) {
		SecretKey secretKey = new SecretKeySpec(toBytes(key), algorithm);
		try {
			Mac mac = Mac.getInstance(secretKey.getAlgorithm());
			mac.init(secretKey);
			byte[] encryptBytes = mac.doFinal(content.getBytes());
			String newEncrypt = new String(encodeHex(encryptBytes));
			return newEncrypt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * HMAC ����-md5
	 *
	 * @param key
	 * @param content
	 * @return
	 */
	public static String encryptHMACMD5String(String key, String content) {
		return encryptHMACString(algorithm_name_hmacmd5, key, content);
	}

	/**
	 * HMAC ����-sha256
	 *
	 * @param key
	 * @param content
	 * @return
	 */
	public static String encryptHMACSHA256String(String key, String content) {
		return encryptHMACString(algorithm_name_hmacsha256, key, content);
	}

	/**
	 * HMAC ����-sha384
	 *
	 * @param key
	 * @param content
	 * @return
	 */
	public static String encryptHMACSHA384String(String key, String content) {
		return encryptHMACString(algorithm_name_hmacsha384, key, content);
	}

	/**
	 * HMAC ����-sha512
	 *
	 * @param key
	 * @param content
	 * @return
	 */
	public static String encryptHMACSHA512String(String key, String content) {
		return encryptHMACString(algorithm_name_hmacsha512, key, content);
	}
}
