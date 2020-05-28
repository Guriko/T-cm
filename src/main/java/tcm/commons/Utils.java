package tcm.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils {
	
	//サブフォルダ作成
	public static boolean mkSubDir(String subDirName) {
		File newdir = new File(subDirName);
		if(!newdir.mkdir()) {
			return false;
		}
		return true;
	}
	
	
	//ファイル作成
	public static boolean mkFile(String createFile) throws IOException {
		File mkFile = new File(createFile);
		if(!mkFile.createNewFile()){
			return false;
		}
		return true;
	}
	
	//日付獲得
	public static String getDate(Date date) {
		SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String c1 = d1.format(date);
		return c1;
	}
	
	public static String getDate2(Date date) {
		SimpleDateFormat d1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String c1 = d1.format(date);
		return c1;
	}
	
	
	//100~999のランダム数値獲得	
	public static Random random = new Random();
	
	public static String getRandom() {
		//Random random = new Random();
		int randomValue = random.nextInt(900) + 100;
		String id = Integer.toString(randomValue);
		return id;
	}
}
