package tcm;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Test {
	public static void main(String...args) throws IOException {
//		Properties settings = new Properties();
//		settings.setProperty("country", "USA");
//		settings.setProperty("lang", "English");
//
//		FileOutputStream out = null;
//
//		out = new FileOutputStream("application.properties");
//		settings.store(out, "Foo Properties");
        System.out.println(String.format("%06d", 1));
		System.out.println(String.format("%06d", 12));
		System.out.println(String.format("%06d", 123));
		System.out.println(String.format("%06d", 0));
		System.out.println(String.format("%06d", 123456));

		log.info("Hello World");
	}
}
