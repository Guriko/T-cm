package tcm;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Test {
	
	private static Random random = new Random();
	private static Date d = new Date();
	
	public static void main(String...args) throws IOException {
		log.info("Hello World");
		
		System.out.println(Test.getRandom(random.nextInt(900) + 100));
		System.out.println(Test.getDate(d));
	}
	//100~999のランダム数値獲得
	
	
	public static String getRandom(int random) {
		//Random random = new Random();
		//int randomValue = random.nextInt(900) + 100;
		String id = Integer.toString(random);
		return id;
	}
	
	//日付獲得
	
	public static String getDate(Date date) {
		SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String c1 = d1.format(date);
		return c1;
	}
}
