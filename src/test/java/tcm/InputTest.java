package tcm;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import lombok.SneakyThrows;
import lombok.val;
import tcm.commons.Const;

class InputTest extends Input{

	public static int csv = 0;
	public static int ind = 0;
	public static int pdf = 0;

	@Test @SneakyThrows
	void 引数テスト(@TempDir Path directory) {
		//System.out.println(directory);
		String tmpdir = directory.toString();
		
		Path demopath = Paths.get(".\\foo.pdf");
		if(!Files.exists(demopath)) {
			Files.createFile(Paths.get(".\\foo.pdf"));
		}
		
		val so = System.out;

		val baos = new ByteArrayOutputStream();
		val ps = new PrintStream(baos);
		System.setOut(ps);
		System.setErr(ps);

		main(new String[0]);

		assertTrue(baos.toString().startsWith(
				"第一引数にフォルダを指定してください。処理を終了します。"));
		baos.reset();

		main(new String[] {"test"});

		assertTrue(baos.toString().startsWith(
				"サブフォルダを作成できませんでした"));
		baos.reset();


		// 任意の日付文字列
		String inpDateStr = "20160606000000";

		// 取り扱う日付の形にフォーマット設定
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMddHHmmss");

		// Date型に変換( DateFromatクラスのperse() )
		Date date = sdformat.parse(inpDateStr);
		Input.date = date;


		main(new String[] {tmpdir});
		String folder  = R + Const.UNDERBAR + "20160606000000" + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL;
		Path subDir = Paths.get(tmpdir);
		Path path = Paths.get(directory + "\\" +R + Const.UNDERBAR + "20160606000000" + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL);

		try(val stream1 = Files.list(subDir)){
			stream1.filter(Files::isDirectory).map(name -> name.getFileName().toString()).forEach(name ->{
				assertEquals(folder,name);
			});
		}


		String csvFile = "20160606000000" + Const.UNDERBAR + Const.P + id + Const.CSV;
		String indFile = "20160606000000" + Const.UNDERBAR + Const.P + id + Const.IND;
		String pdfFile = "20160606000000" + Const.UNDERBAR + Const.P + id + Const.PDF;
		
		try(val stream = Files.list(path)) {

			stream.filter(Files::isRegularFile).map(name -> name.getFileName().toString()).forEach(name ->{
				if(name.contains(".csv")) {
					assertEquals(csvFile,name.toString());
					InputTest.csv++;
				}else if(name.contains(".ind")) {
					assertEquals(indFile,name.toString());
					InputTest.ind++;
				}else if(name.contains(".pdf")) {
					assertEquals(pdfFile,name.toString());
					InputTest.pdf++;
				}
			});
		}
		assertEquals(1,InputTest.csv);
		assertEquals(1,InputTest.ind);
		assertEquals(1,InputTest.pdf);
		
		System.setOut(so);
		System.out.println(baos.toString());
		
		if(Files.exists(demopath)) {
			Files.delete(Paths.get(".\\foo.pdf"));
		}
	}
}
