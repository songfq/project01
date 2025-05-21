package com.fq.license;

import com.fq.util.MyDateUtil;
import com.fq.util.MyDigestUtil;

import java.util.Date;

public class LicService {
	public static boolean check(String module) {
		String privateCode = "83c939579f86666d6c8f88917f5e8d10";
		String publicCode = "18b81c9643287038f6427ab7a7a252c0";
		String ip = "192.168.1.43";
		String beginDateStr = "2023-07-01";
		String endDateStr = "2023-09-30";
		
		String serviceCodeMA = privateCode + "_" + ip + "_" + module + "_" + beginDateStr + "_" + endDateStr;
		String serviceCode = MyDigestUtil.encryptHMACSHA512String(privateCode, serviceCodeMA);
		if (!publicCode.equals(serviceCode)) {
			throw new RuntimeException("许可校验失败");
		}
		
		Date beginDate = MyDateUtil.stringToShortDate(beginDateStr);
		Date endDate = MyDateUtil.stringToShortDate(endDateStr);
		Date currDate = MyDateUtil.dateToShortDate(new Date());
		currDate = MyDateUtil.stringToShortDate("2023-10-01");
		
		boolean between = MyDateUtil.between(currDate, beginDate, endDate);
		if (!between) {
			throw new RuntimeException("许可已过期");
		}
		return true;
	}
	
	/**
	 * 生成许可
	 **/
	public static void generLicense(String ip, String module, String beginDate, String endDate, String project, String hint) {
		long time = System.currentTimeMillis();
		String privateCodeMA = "moya_fq_" + ip + module + beginDate + endDate;
		String privateCode = MyDigestUtil.encryptSHA512String(privateCodeMA);
		System.out.println(project + "-私钥[" + beginDate + ":" + endDate + "]：" + privateCode);
		
		String publicCodeMA = privateCode + "_" + ip + "_" + module + "_" + beginDate + "_" + endDate;
		String publicCode = MyDigestUtil.encryptHMACSHA512String(privateCode, publicCodeMA);
		
		System.out.println(project + "-公钥[" + beginDate + ":" + endDate + "]：" + publicCode);
		
		StringBuilder table_insert_sql = new StringBuilder();
		table_insert_sql.append("insert into T_MOY_License(FPrivate_code,FPublic_code,FModule,FIP,FBeginDate,FEndDate,FProject,FHint) ");
		table_insert_sql.append(" values('" + privateCode + "','" + publicCode + "','" + module + "','" + ip + "',{ts '" + beginDate + "'},{ts '" + endDate + "'},'" + project + "','" + hint + "')");
		System.out.println(project + "-许可[" + beginDate + ":" + endDate + "]：" + table_insert_sql);
		
		String json = "{privateCode:'" + privateCode + "',publicCode:'" + publicCode + "',module:'" + module + "',ip:'" + ip + "',beginDate:'" + beginDate + "',endDate:'" + endDate + "',project:'" + project + "',hint:'" + hint + "'}";
		System.out.println(project + "-JSON[" + beginDate + ":" + endDate + "]：" + json);
	}
	
	public static void generLicenseDate(String ip, String module, String beginDateStr, int month, String project, String hint) {
		Date beginDate = MyDateUtil.stringToShortDate(beginDateStr);
		beginDateStr = MyDateUtil.getMonthStartShortStr(beginDate);
		
		Date endDate = MyDateUtil.offsetMonth(beginDate, month);
		String endDateStr = MyDateUtil.getMonthEndShortStr(endDate);
		
		System.out.println("时间：" + beginDateStr + "~" + endDateStr);
		
		LicService.generLicense(ip, module, beginDateStr, endDateStr, project, hint);
	}
	
	public static void main(String[] args) {
		//MyLicense.generLicense("192.168.3.88", "myshr", "2023-07-01", "2023-10-30");
		//MyLicense.generLicense("192.168.3.88", "myshr", "2023-07-01", "2093-10-31");
		//MyLicense.check("myshr");
		//LicService.generLicenseDate("192.168.92.3", "myshr", "2023-08-12", 12, "九三集团");
		//LicService.generLicenseDate("192.168.2.188", "myshr", "2023-08-12", 12, "本机", "XK0000");
		LicService.generLicenseDate("172.18.171.228", "myshr", "2023-08-12", 120, "研发一部-演示环境", "XK0000");
		//LicService.generLicenseDate("192.168.2.131", "myshr", "2023-08-12", 120, "研发一部-皮志敏", "XK0000");
		//LicService.generLicenseDate("10.11.239.14", "myshr", "2023-08-12", 24, "中交一公局-测试环境", "XK0000");
		//LicService.generLicenseDate("10.11.239.15", "myshr", "2023-08-12", 3, "中交一公局-正式环境", "XK0000");
		//LicService.generLicenseDate("172.18.243.77", "myshr", "2023-09-01", 12, "任职资格-金蝶深圳分公司", "XK0000");
	}
}
