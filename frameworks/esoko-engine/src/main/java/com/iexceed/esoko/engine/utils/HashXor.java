/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iexceed.esoko.engine.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

@SuppressWarnings("restriction")
public class HashXor {

	private static Sha4J sha4j = new Sha4J();
	public static Logger log = LoggerUtils.getLogger();

	public static String toSha256String(String input) throws IOException {
		return toHexString(sha256(input));
	}

	public static byte[] sha256(String input) throws IOException {
		sha4j.reset();
		return sha4j.sha256Digest(new ByteArrayInputStream(input.getBytes()));

	}

	public static String hashValue(String pimie, String pimsi, String puid,
			String puname, String pInpin, String pDate) {
		byte[] encodedBytes = null;
		String uname = puname;
		String inpin = pInpin;
		String imie = pimie;
		String imsi = pimsi;

		String inconcatstr;
		String dtstr = pDate;

		String day = dtstr.substring(0, 3);
		String hr = dtstr.substring(16, 18);
		String min = dtstr.substring(19, 21);
		String sec = dtstr.substring(22, 24);
		String yr = dtstr.substring(13, 15);
		String dd = dtstr.substring(5, 7);
		String mm = dtstr.substring(8, 10);
		if (puid.isEmpty()) {
			inconcatstr = hr + min + day + yr + dd + mm + sec + uname + imie
					+ imsi;
		} else {
			inconcatstr = hr + min + day + yr + dd + mm + sec + uname + puid;
		}
		try {

			HashXor xx = new HashXor();
			// XOR Sha-256 values of concatenated String and PIN
			String hshxor = xx.xorHex(toSha256String(inconcatstr),
					toSha256String(inpin));
			// First Byte of XOR'ed Sha-256 value of concatenated String and PIN
			String fbyte = hshxor.substring(0, 16);
			// Last Byte of XOR'ed Sha-256 value of concatenated String and PIN
			String lbyte = hshxor.substring(48);
			// XOR First And Last Bytes
			String fblbxor = xx.xorHex(fbyte, lbyte);
			// XOR Above with Pin Hash
			String hshxor2 = xx.xorHex(fblbxor, toSha256String(inpin));

			// Round 2 -> First Byte of XOR'ed Sha-256 value of concatenated
			// String and PIN
			String fbyte2 = hshxor2.substring(0, 8);
			// Round 2 -> Last Byte of XOR'ed Sha-256 value of concatenated
			// String and PIN
			String lbyte2 = hshxor2.substring(8);
			// Round 2 -> XOR First And Last Bytes
			String fblbxor2 = xx.xorHex(fbyte2, lbyte2);
			// Round 2 -> XOR Above with Pin Hash//
			String finalstr = xx.xorHex(fblbxor2, toSha256String(inpin));

			// Finally, the base64 encoded value
			// BASE64Encoder encoder = new BASE64Encoder();
			encodedBytes = Base64.encodeBase64(finalstr.getBytes());

		} catch (Exception e) {
			log.error("HashXor -> hashValue :" + Utils.getStackTrace(e));
		}
		return new String(encodedBytes);

	}

	public static String toHexString(byte[] array) {
		return DatatypeConverter.printHexBinary(array);
	}

	public static byte[] toByteArray(String s) {
		return DatatypeConverter.parseHexBinary(s);
	}

	public static byte[] hexToBytes(String hexString) {
		HexBinaryAdapter adapter = new HexBinaryAdapter();
		byte[] bytes = adapter.unmarshal(hexString);
		return bytes;
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	// XOR Truth Table from two Hex Strings
	public String xorHex(String a, String b) {
		char[] chars = new char[a.length()];
		for (int i = 0; i < chars.length; i++) {
			chars[i] = toHex(fromHex(a.charAt(i)) ^ fromHex(b.charAt(i)));
		}
		return new String(chars);
	}

	// char wise Hex to int
	private static int fromHex(char c) {
		if (c >= '0' && c <= '9') {
			return c - '0';
		}
		if (c >= 'A' && c <= 'F') {
			return c - 'A' + 10;
		}
		if (c >= 'a' && c <= 'f') {
			return c - 'a' + 10;
		}
		throw new IllegalArgumentException();
	}

	// char wise int to Hex
	private char toHex(int nybble) {
		if (nybble < 0 || nybble > 15) {
			throw new IllegalArgumentException();
		}
		return "0123456789ABCDEF".charAt(nybble);
	}
}
