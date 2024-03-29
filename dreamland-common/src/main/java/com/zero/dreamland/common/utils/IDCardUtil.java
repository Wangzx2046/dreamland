package com.zero.dreamland.common.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 	 身份证验证的工具（支持5位或18位省份证）
 * 	 身份证号码结构： 17位数字和1位校验码：6位地址码数字，8位生日数字，3位出生时间顺序号，1位校验码。
 * 	地址码（前6位）：表示对象常住户口所在县（市、镇、区）的行政区划代码，按GB/T2260的规定执行。 出生日期码，（第七位
 *	 至十四位）：表示编码对象出生年、月、日，按GB按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
 * 	顺序码（第十五位至十七位）：表示在同一地址码所标示的区域范围内，对同年、同月、同日出生的人编订的顺序号， 顺序码的奇数分配给男性，偶数分配给女性。
 * 	校验码（第十八位数）： 十七位数字本体码加权求和公式 s = sum(Ai*Wi), i = 0,,16，先对前17位数字的权求和；
 *  Ai:表示第i位置上的身份证号码数字值.Wi:表示第i位置上的加权因.Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2；
 * 	计算模 Y = mod(S, 11) 通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
 *
 */
public class IDCardUtil {
	/**
	 * 省份枚举
	 */
	final static Map<Integer, String> ZONE_NUM = new HashMap<Integer, String>();
	static {
		ZONE_NUM.put(11, "北京");
		ZONE_NUM.put(12, "天津");
		ZONE_NUM.put(13, "河北");
		ZONE_NUM.put(14, "山西");
		ZONE_NUM.put(15, "内蒙古");
		ZONE_NUM.put(21, "辽宁");
		ZONE_NUM.put(22, "吉林");
		ZONE_NUM.put(23, "黑龙江");
		ZONE_NUM.put(31, "上海");
		ZONE_NUM.put(32, "江苏");
		ZONE_NUM.put(33, "浙江");
		ZONE_NUM.put(34, "安徽");
		ZONE_NUM.put(35, "福建");
		ZONE_NUM.put(36, "江西");
		ZONE_NUM.put(37, "山东");
		ZONE_NUM.put(41, "河南");
		ZONE_NUM.put(42, "湖北");
		ZONE_NUM.put(43, "湖南");
		ZONE_NUM.put(44, "广东");
		ZONE_NUM.put(45, "广西");
		ZONE_NUM.put(46, "海南");
		ZONE_NUM.put(50, "重庆");
		ZONE_NUM.put(51, "四川");
		ZONE_NUM.put(52, "贵州");
		ZONE_NUM.put(53, "云南");
		ZONE_NUM.put(54, "西藏");
		ZONE_NUM.put(61, "陕西");
		ZONE_NUM.put(62, "甘肃");
		ZONE_NUM.put(63, "青海");
		ZONE_NUM.put(64, "宁夏");
		ZONE_NUM.put(65, "新疆");
		ZONE_NUM.put(71, "台湾");
		ZONE_NUM.put(81, "香港");
		ZONE_NUM.put(82, "澳门");
		ZONE_NUM.put(91, "外国");
	}

	final static int[] PARITYBIT = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
	final static int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };


	

	/**
	 * 身份证验证
	 * 
	 * @param s 号码内容
	 * @return 是否有效 null和"" 都是false
	 */
	public static boolean isIDCard(String certNo) {
		if (certNo == null || (certNo.length() != 15 && certNo.length() != 18)){
			return false;}
		final char[] cs = certNo.toUpperCase().toCharArray();
		// 校验位数
		int power = 0;
		for (int i = 0; i < cs.length; i++) {
			if (i == cs.length - 1 && cs[i] == 'X') {
				break;// 最后一位可以 是X或x
			}
			if (cs[i] < '0' || cs[i] > '9') {
				return false;
			}
			if (i < cs.length - 1) {
				power += (cs[i] - '0') * POWER_LIST[i];
			}
		}

		// 校验区位码
		if (!ZONE_NUM.containsKey(Integer.valueOf(certNo.substring(0, 2)))) {
			return false;
		}

		// 校验年份
		String year = certNo.length() == 15 ? getIdcardCalendar() + certNo.substring(6, 8) : certNo.substring(6, 10);

		final int iyear = Integer.parseInt(year);
		if (iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR)) {
			return false;// 1900年的PASS，超过今年的PASS
		}

		// 校验月份
		String month = certNo.length() == 15 ? certNo.substring(8, 10) : certNo.substring(10, 12);
		final int imonth = Integer.parseInt(month);
		if (imonth < 1 || imonth > 12) {
			return false;
		}

		// 校验天数
		String day = certNo.length() == 15 ? certNo.substring(10, 12) : certNo.substring(12, 14);
		final int iday = Integer.parseInt(day);
		if (iday < 1 || iday > 31) {
			return false;
		}

		// 校验"校验码"
		if (certNo.length() == 15) {
			return true;
		}
		return cs[cs.length - 1] == PARITYBIT[power % 11];
	}

	private static int getIdcardCalendar() {
		GregorianCalendar curDay = new GregorianCalendar();
		int curYear = curDay.get(Calendar.YEAR);
		int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));
		return year2bit;
	}
	public static String getBrithdayByCnIdCard(String idCardNum) {
        String year = null;
        String month = null;
        String day = null;
         //正则匹配身份证号是否是正确的，15位或者17位数字+数字/x/X
        if (idCardNum.matches("(^\\d{15})|(^\\d{17}[\\dxX]$)")) {
            year = idCardNum.substring(6, 10);
            month = idCardNum.substring(10,12);
            day = idCardNum.substring(12,14);
        }else {
            System.out.println("身份证号码不匹配！");
            return null;
        }
        return year + "-" + month + "-" + day;
    }
	/**
	 * 通过身份证号码获取出生日期、性别、年龄
	 * 
	 * @param certificateNo
	 * @return 返回的出生日期格式：1990-01-01 性别格式：F-女，M-男
	 */
	public static Map<String, String> getBirAgeSex(String certificateNo) {
		String birthday = "";//阳历生日
		String age = "";
		String sexCode = "";

		int year = Calendar.getInstance().get(Calendar.YEAR);
		char[] number = certificateNo.toCharArray();
		boolean flag = true;
		if (number.length == 15) {
			for (int x = 0; x < number.length; x++) {
				if (!flag) {
					return new HashMap<String, String>();
				}
				flag = Character.isDigit(number[x]);
			}
		} else if (number.length == 18) {
			for (int x = 0; x < number.length - 1; x++) {
				if (!flag) {
					return new HashMap<String, String>();
				}
				flag = Character.isDigit(number[x]);
			}
		}
		if (flag && certificateNo.length() == 15) {
			birthday = "19" + certificateNo.substring(6, 8) + "-" + certificateNo.substring(8, 10) + "-"
					+ certificateNo.substring(10, 12);
			sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 3, certificateNo.length()))
					% 2 == 0 ? "F" : "M";
			age = (year - Integer.parseInt("19" + certificateNo.substring(6, 8))) + "";
		} else if (flag && certificateNo.length() == 18) {
			birthday = certificateNo.substring(6, 10) + "-" + certificateNo.substring(10, 12) + "-"
					+ certificateNo.substring(12, 14);
			sexCode = Integer.parseInt(certificateNo.substring(certificateNo.length() - 4, certificateNo.length() - 1))
					% 2 == 0 ? "F" : "M";
			age = (year - Integer.parseInt(certificateNo.substring(6, 10))) + "";
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("birthday", birthday);
		map.put("age", age);
		map.put("sexCode", sexCode);
		return map;
	}
	public static void main(String[] args) {
		boolean mark = isIDCard("370212199112041550");
		System.out.println(mark);
	}

}
