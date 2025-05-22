package com.fq.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	
	public static String getJson(String path) throws Exception {
		String json = "";
		File file = null;
		InputStream inputStream = null;
		try {
			file = new File(path);
			inputStream = new FileInputStream(file);
			
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes);
			inputStream.close();
			
			json = new String(bytes, "UTF-8");
			
			System.out.println("我再develop添加了一行代码");
			System.out.println("这是我第二个分支了，非常棒");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return json;
	}
	
	public static byte[] getImage(String path) {
		byte[] content = null;
		File file = null;
		InputStream inputStream = null;
		try {
			file = new File(path);
			inputStream = new FileInputStream(file);
			
			content = new byte[inputStream.available()];
			inputStream.read(content);
			inputStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
		}
		return content;
	}
	
	public static String sqlListToStr(List<String> idList) {
		if (idList == null || idList.size() <= 0) {
			return "";
		}
		String join = "";
		for (int i = 0; i < idList.size(); i++) {
			String tmpId = "'" + idList.get(i) + "'";
			join += "," + tmpId;
		}
		join = join.substring(1);
		return join;
	}
	
	public static void main(String[] args) {
		try {
			// FileUtils.getJson("src/json/FsiPaymentFacade.json");
			List<String> idList = new ArrayList();
			idList.add("xx");
			// idList.add("cc");
			// idList.add("bb");
			System.out.println(FileUtils.sqlListToStr(idList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
