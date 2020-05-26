package tcm;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Test {
	public static void main(String...args) throws IOException {
		Path sourcePath = Paths.get(".\\foo.pdf");
		Path destinationPath = Paths.get(".\\logs\\bar.pdf");

		try {
			Files.copy(sourcePath,destinationPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
		log.info("Hello World");
		//		String pOld = ".\\cbd.txt";
		//		Path path = Paths.get(pOld);
		//		Path sPath = Paths.get(".\\");
		//		
		//		//Files.copy(path, sPath);
		//		
		//		
		//		File fOld = new File(".\\abc.txt");
		//		File fNew = new File(".\\logs\\cbd.txt");
		//		Files.move(fOld,fNew);
	}
}
