package com.iexceed.esoko.engine.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.web.context.WebApplicationContext;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public class Utils {

	private static final char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May",
			"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	private static Map<String, String> countriesISDCode;
	private static Map<String, String> countryCodes;
	private static Map<String, String> isoCodes;
	private static Logger log = LoggerUtils.getLogger();
	public static String lSystemDateFormat;
	public static String lServerURL;
	public static String lUserID;
	public static String lAdminAppId;
	public static String lEsokoAppId;
	public static String lLoginInterfaceId;
	public static String lGetUserInterfaceId;
	public static String lCreateUserInterfaceId;
	public static String lDeleteUserInterfaceId;
	public static String lHashedPin;
	public static String smsCenter;
	public static String senderId;
	public static String OTPMessage;
	public static String lServerToken;
	public static int OTPExpiryMins;
	public static int connectionTimeOut;
	public static int readTimeOut;
	public static String ESOKO_NETWORK = "esoko";
	public static String HLR_URL;
	public static SecureRandom secRand = new SecureRandom();
	public static WebApplicationContext springContext;
	public static Scheduler scheduler;

	public static XMLGregorianCalendar convertDateToXMLGregorian(String dateStr) {
		try {
			String dateFormat = "yyyy-MM-dd";
			DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.clear();
			Calendar parsedCalendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			Date rawDate = sdf.parse(dateStr);
			parsedCalendar.setTime(rawDate);
			calendar.set(parsedCalendar.get(Calendar.YEAR),
					parsedCalendar.get(Calendar.MONTH),
					parsedCalendar.get(Calendar.DATE));
			XMLGregorianCalendar xmlCalendar = datatypeFactory
					.newXMLGregorianCalendar(calendar);
			return xmlCalendar;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	public static byte[] encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64(imageByteArray);
	}

	public static byte[] decodeImage(byte[] imageByteArray) {
		return Base64.decodeBase64(imageByteArray);
	}

	public static String convertToBase64(byte[] imageByteArray) {
		return Base64.encodeBase64String(imageByteArray);
	}

	public static String getStackTrace(final Exception e) {
		String lStackTrace = null;
		StringWriter lSw = null;
		PrintWriter lPw = null;
		lSw = new StringWriter();
		lPw = new PrintWriter(lSw);
		e.printStackTrace(lPw);
		lStackTrace = lSw.toString();
		return lStackTrace;
	}

	public static Date getFormatedDate(String string) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
			date = df.parse(string);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static Date getFormatedDate1(String string) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("dd MMM yy");
			date = df.parse(string);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static String getFormatedDate1(Date date) {
		DateFormat df = new SimpleDateFormat("d MMM yy");
		return df.format(date);
	}

	public static Date getFormatedDate2(String string) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("dd MMM yyyy");
			date = df.parse(string);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static Date getFormatedDate3(String string) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("dd-MMM-yy");
			date = df.parse(string);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static Date getFormatedDate4(String string) {
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// parse the date string into Date object
		try {
			date = dateFormat.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDtFromMMDDYYYY(String string) {
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

		// parse the date string into Date object
		try {
			date = dateFormat.parse(string);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getCurrentDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Float roundDoubleVal(Double val, int precision) {
		Float returnVal;
		BigDecimal bd = new BigDecimal(val).setScale(precision,
				RoundingMode.HALF_EVEN);
		val = bd.doubleValue();
		returnVal = Float.parseFloat(val.toString());
		return returnVal;
	}

	public static String getDDMMMYYFormat(String str, int flag) {
		String returnStr = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {

			Date date = formatter.parse(str);
			String monthString;
			String[] foramtedDate = formatter.format(date).split("-");
			monthString = monthArray[Integer.parseInt(foramtedDate[1]) - 1];
			if (flag == 0) {
				returnStr = foramtedDate[2]
						+ " "
						+ monthString
						+ " "
						+ foramtedDate[0]
								.substring(2, foramtedDate[0].length());
			} else {
				returnStr = foramtedDate[2] + " " + monthString + " "
						+ foramtedDate[0];
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnStr;
	}

	public static String getDateFormater(String str, int flag) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DateFormat outputDate = new SimpleDateFormat("MMM dd,yyyy- hh:mm aa",
				Locale.getDefault());
		String output = null;
		try {
			log.debug("Am here");
			Date date = formatter.parse(str);
			output = outputDate.format(date);
			log.debug("output: " + output);
			Date date1 = formatter.parse(output);
			log.debug("date1: " + date1);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return output;
	}

	public static String getDateFormaterinbox(String str, int flag) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DateFormat outputDate = new SimpleDateFormat("dd MMM yyyy",
				Locale.getDefault());
		String output = null;
		try {
			log.debug("Am here");
			Date date = formatter.parse(str);
			output = outputDate.format(date);
			log.debug("output: " + output);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return output;
	}

	public static String getDateFormater1(String str, int flag) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		DateFormat outputDate = new SimpleDateFormat("EEE MMM dd,yyyy",
				Locale.getDefault());
		String output = null;
		try {
			log.debug("Am here");
			Date date = formatter.parse(str);
			output = outputDate.format(date);
			log.debug("output: " + output);
			Date date1 = formatter.parse(output);
			log.debug("date1: " + date1);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return output;
	}

	public static String getMonth(int month) {
		return monthArray[month - 1];
	}

	public static Date getFormatedDateTimeStamp(String timeStamp) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy h:mm a");
			date = df.parse(timeStamp);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static Date getFormatedDateTimeStamp1(String timeStamp) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy h:mm a");
			date = df.parse(timeStamp);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static Date getFormatedDateTimeStamp4(String timeStamp) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
			date = df.parse(timeStamp);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static Date getFormatedDateTimeStamp5(String timeStamp) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"MM/dd/yyyy hh:mm:ss a");
		Date date = null;
		try {
			date = formatter.parse(timeStamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static int getMonthFromDate(Date date) {
		int month = 0;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			// int year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			// int day = cal.get(Calendar.DAY_OF_MONTH);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return month;
	}

	public static String getCountryCode(String countryName) {
		StringBuffer countryCode = new StringBuffer();
		PhoneNumber phoneNumber = Utils.getNumberInstance("0000000000",
				countryName);
		if (phoneNumber != null) {
			countryCode.append(phoneNumber.getCountryCode());
		}
		return countryCode.toString();
	}

	public static boolean isPhoneNumberValid(String number, String countryName) {
		boolean isValid = false;
		PhoneNumber phoneNumber = Utils.getNumberInstance(
				number.replace("+", ""), countryName);
		if (phoneNumber != null) {
			PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
			isValid = phoneUtil.isValidNumber(phoneNumber);
		}
		return isValid;
	}

	public static PhoneNumber getNumberInstance(String number,
			String countryName) {
		if (countryName.equals("Ivory Coast")) {
			countryName = "Côte d'Ivoire";
		}
		PhoneNumber phoneNumber = null;
		if (countriesISDCode == null) {
			countriesISDCode = new HashMap<String, String>();
			for (String iso : Locale.getISOCountries()) {
				Locale l = new Locale("", iso);
				countriesISDCode.put(l.getDisplayCountry(), iso);
			}
		}

		String code = countriesISDCode.get(countryName);
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		try {
			if (code != null) {
				phoneNumber = phoneUtil.parse(number, code);
			}
		} catch (NumberParseException e) {
			e.printStackTrace();
		}
		return phoneNumber;
	}

	public static String hashSHA256(String ptext, String psalt) {
		String pTextSalt = ptext + psalt;
		String pHashedText = "";
		byte[] ptextSaltbyte = new byte[200];
		byte[] hashbyte = new byte[200];
		try {
			MessageDigest msgdigest = MessageDigest.getInstance("SHA-256");
			try {
				ptextSaltbyte = pTextSalt.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error("Utils -> hashSHA256: Unsupported character set");
			}
			msgdigest.reset();
			msgdigest.update(ptextSaltbyte);
			hashbyte = msgdigest.digest();
			pHashedText = toHexString(hashbyte);

		} catch (NoSuchAlgorithmException n) {
			log.error("Utils -> hashSHA256: " + getStackTrace(n));
		}
		return pHashedText;
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			int c = ((b[i]) >>> 4) & 0xf;
			sb.append(hex[c]);
			c = (b[i] & 0xf);
			sb.append(hex[c]);
		}
		return sb.toString();
	}

	public static byte[] getRandomByte(final int len) {
		byte[] randomByte = null;
		randomByte = new byte[len];
		secRand.nextBytes(randomByte);
		return randomByte;
	}

	public static String getRandomString(final int len) {
		String randomString = "";
		final byte[] randomByte = getRandomByte(len);
		randomString = Base64.encodeBase64String(randomByte);
		return randomString;
	}

	public static int getJulianDt() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int JGREG = 15 + 31 * (10 + 12 * 1582);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.MONTH);
		int julianYear = year;
		if (year < 0)
			julianYear++;
		int julianMonth = month;
		if (month > 2) {
			julianMonth++;
		} else {
			julianYear--;
			julianMonth += 13;
		}
		double julian = (java.lang.Math.floor(365.25 * julianYear)
				+ java.lang.Math.floor(30.6001 * julianMonth) + day + 1720995.0);
		if (day + 31 * (month + 12 * year) >= JGREG) {
			int ja = (int) (0.01 * julianYear);
			julian += 2 - ja + (0.25 * ja);
		}
		Double julian_dob = new Double(java.lang.Math.floor(julian));
		return julian_dob.intValue();
	}

	public static Date getFmtDate(String string) {
		Date date = null;
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			date = df.parse(string);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date;
	}

	public static Date getFmtDtDDMMYYYY(String date) {
		Date dt = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		// parse the date string into Date object
		try {
			dt = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}

	public static String getFmtddmmyyyybackslash(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEE	MMM DD hh:mm:ss zzz yyyy");
		DateFormat outputDate = new SimpleDateFormat("dd/MM/yyyy",
				Locale.getDefault());
		String output = null;
		try {
			log.debug("Am here");
			Date date = formatter.parse(str);
			output = outputDate.format(date);
			log.debug("output: " + output);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return output;

	}

	public static Date getDateWithoutTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.clear(10);
		cal.clear(11);
		cal.clear(9);
		cal.clear(12);
		cal.clear(13);
		cal.clear(14);
		return cal.getTime();
	}

	public static String stripNonDigits(final CharSequence input) {
		final StringBuilder sb = new StringBuilder(input.length());

		for (int i = 0; i < input.length(); i++) {
			final char c = input.charAt(i);
			if (c > 47 && c < 58) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static File convertByteToFile(byte[] invoiceFile) {
		File tempFile = null;
		try {
			tempFile = File.createTempFile("filepath", ".tmp", null);
			FileOutputStream fos = null;
			fos = new FileOutputStream(tempFile);
			fos.write(invoiceFile);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;
	}

	public static String getYearAndMonth(Date date) {
		StringBuffer buffer = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		buffer.append(cal.get(Calendar.YEAR) + "-");
		buffer.append(cal.get(Calendar.MONTH) + 1);
		return buffer.toString();
	}

	public static String getBidsAlrtTmplt(String bidsAlrtTmplt,
			Map<String, String> values, String negotiableFlag) {
		String temp = null;
		if (negotiableFlag.equalsIgnoreCase("N")) {
			temp = bidsAlrtTmplt
					.replace("<Quantity>", values.get("Quantity"))
					.replace("<Measure>", values.get("Measure"))
					.replace("<Commodity Name>", values.get("Commodity Name"))
					.replace("<Currency>", values.get("Currency"))
					.replace("<Price>", values.get("Price"))
					.replace("<Offer Belongs to>",
							values.get("Offer Belongs to"))
					.replace("<Phone No.>", values.get("Phone No"))
					.replace("<Expiry Date>", values.get("Expiry Date"))
					.replace("<Call Centre Message-Y/N>",
							values.get("Call Centre Message-Y/N"))
					.replace("<Country Call Centre>",
							values.get("Country Call Centre"));
		} else {
			temp = bidsAlrtTmplt
					.replace("<Quantity>", values.get("Quantity"))
					.replace("<Measure>", values.get("Measure"))
					.replace("<Commodity Name>", values.get("Commodity Name"))
					.replace("<Currency> <Price> Each", "Negotiable")
					.replace("<Offer Belongs to>",
							values.get("Offer Belongs to"))
					.replace("<Phone No.>", values.get("Phone No"))
					.replace("<Expiry Date>", values.get("Expiry Date"))
					.replace("<Call Centre Message-Y/N>",
							values.get("Call Centre Message-Y/N"))
					.replace("<Country Call Centre>",
							values.get("Country Call Centre"));
		}
		return temp;
	}

	public static String getOffersAlrtTmplt(String offrsAlrtTmplt,
			Map<String, String> values, String negotiableFlag) {
		String temp = null;
		if (negotiableFlag.equalsIgnoreCase("N")) {
			temp = offrsAlrtTmplt
					.replace("<Quantity>", values.get("Quantity"))
					.replace("<Measure>", values.get("Measure"))
					.replace("<Commodity Name>", values.get("Commodity Name"))
					.replace("<Currency>", values.get("Currency"))
					.replace("<Price>", values.get("Price"))
					.replace("<Offer Belongs to>",
							values.get("Offer Belongs to"))
					.replace("<Phone No.>", values.get("Phone No"))
					.replace("<Expiry Date>", values.get("Expiry Date"))
					.replace("<Call Centre Message-Y/N>",
							values.get("Call Centre Message-Y/N"))
					.replace("<Country Call Centre>",
							values.get("Country Call Centre"));
		} else {
			temp = offrsAlrtTmplt
					.replace("<Quantity>", values.get("Quantity"))
					.replace("<Measure>", values.get("Measure"))
					.replace("<Commodity Name>", values.get("Commodity Name"))
					.replace(" <Currency> <Price> Each", "Negotiable")
					.replace("<Offer Belongs to>",
							values.get("Offer Belongs to"))
					.replace("<Phone No.>", values.get("Phone No"))
					.replace("<Expiry Date>", values.get("Expiry Date"))
					.replace("<Call Centre Message-Y/N>",
							values.get("Call Centre Message-Y/N"))
					.replace("<Country Call Centre>",
							values.get("Country Call Centre"));
		}
		return temp;
	}

	public static String getStringDate(Date date) {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return df.format(date);
	}

	public static Date getDateAfterTwoMins(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.add(Calendar.MINUTE, 2);
		return calendar.getTime();
	}

	public static String getCountry(String phoneNumber) {
		log.info("Inside Utils -> getCountry");
		log.debug("PhoneNumber: " + phoneNumber);
		String result = null;
		try {
			if (countryCodes == null) {
				countryCodes = new HashMap<String, String>();
				isoCodes = new HashMap<String, String>();
				for (String iso : Locale.getISOCountries()) {
					Locale l = new Locale("", iso);
					PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
					if (!iso.equals("AN") && !iso.equals("AQ")
							&& !iso.equals("BV") && !iso.equals("GS")
							&& !iso.equals("HM") && !iso.equals("PN")
							&& !iso.equals("TF") && !iso.equals("UM")) {
						PhoneNumber lPhoneNumber = phoneUtil.parse(
								"0000000000", iso);
						if (countryCodes.containsKey(lPhoneNumber
								.getCountryCode() + "")) {
							String temp = countryCodes.get(lPhoneNumber
									.getCountryCode() + "");
							countryCodes.put(
									lPhoneNumber.getCountryCode() + "", temp
											+ "," + l.getDisplayCountry());
						} else {
							countryCodes.put(
									lPhoneNumber.getCountryCode() + "",
									l.getDisplayCountry());
						}
						isoCodes.put(l.getDisplayCountry(), iso);
					}
				}
			}
			String finalKey = null;
			Set<String> keys = countryCodes.keySet();
			for (String key : keys) {
				if (phoneNumber.startsWith(key)) {
					finalKey = key;
					break;
				}
			}
			String lCountry = countryCodes.get(finalKey);
			String[] lCntrs = lCountry.split(",");
			for (String country : lCntrs) {
				String isoCode = isoCodes.get(country);
				PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
				PhoneNumber phone = phoneUtil.parse(phoneNumber, isoCode);
				if (phoneUtil.isValidNumber(phone)) {
					result = country;
					break;
				}
			}
		} catch (NumberParseException e) {
			log.error(Utils.getStackTrace(e));
		}
		return result;
	}

	public static int noOfMessages(String messsage) {
		String staticchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890' ";
		String specialchars = "@!\"#¤%&()*+,-./:;<=>?¡^{}\\[~]|€";
		String customchars = "^{}\\[~]|€";
		int stcharslen = staticchars.length();
		int spcharslen = specialchars.length();
		int custcharslen = customchars.length();
		boolean isMsgCustom = false;
		int charsLen = 0, noOfMsgs = 0;
		int remainder = 0;
		for (int i = 0; i < messsage.length(); i++) {
			boolean flag = true;
			int j = 0;
			while (j < stcharslen) {
				if (String.valueOf(messsage.charAt(i)).equals(
						String.valueOf(staticchars.charAt(j)))) {
					++charsLen;
					flag = false;
				}
				j++;
			}
			j = 0;
			while (j < spcharslen) {
				if (String.valueOf(messsage.charAt(i)).equals(
						String.valueOf(specialchars.charAt(j)))) {
					flag = false;
					charsLen = charsLen + 2;
					int k = 0;
					while (k < custcharslen) {
						if (String.valueOf(messsage.charAt(i)).equals(
								String.valueOf(customchars.charAt(k)))) {
							isMsgCustom = true;
						}
						++k;
					}
				}
				++j;
			}

			if (flag) {
				isMsgCustom = true;
			}

		}

		if (isMsgCustom) {
			if ((charsLen % 80) != 0) {
				remainder = 1;
			}
			noOfMsgs = charsLen / 80 + remainder;
			remainder = 0;
			if (noOfMsgs > 1) {
				if ((charsLen % 73) != 0) {
					remainder = 1;
				}
				noOfMsgs = (charsLen / 73) + remainder;
			}
		} else {
			if ((charsLen % 160) != 0) {
				remainder = 1;
			}
			noOfMsgs = (charsLen / 160) + remainder;
			remainder = 0;

			if (noOfMsgs > 1) {
				if ((charsLen % 153) != 0) {
					remainder = 1;
				}
				noOfMsgs = (charsLen / 153) + remainder;
			}
		}
		return noOfMsgs;
	}

}
