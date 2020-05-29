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

class UpdateTest extends Update{

	public static int csv = 0;
	public static int ind = 0;
	
	@Test @SneakyThrows
	void 引数テスト(@TempDir Path directory) {
		System.out.println(directory);
		String tmpdir = directory.toString();
		val so = System.out;
		
		val baos = new ByteArrayOutputStream();
		val ps = new PrintStream(baos);
		System.setOut(ps); 
		System.setErr(ps);
		
		main(new String[0]);
		
		assertTrue(baos.toString().startsWith(
				"第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字、第四引数文字列を指定してください。処理を終了します。"));
		baos.reset();
		
		main(new String[] {"test"});
		
		assertTrue(baos.toString().startsWith(
				"第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字、第四引数文字列を指定してください。処理を終了します。"));
		baos.reset();
		
		main(new String[] {"test", "test"});
		
		assertTrue(baos.toString().startsWith(
				"第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字、第四引数文字列を指定してください。処理を終了します。"));
		baos.reset();
		
		main(new String[] {"test", "test", "test"});
		
		assertTrue(baos.toString().startsWith(
				"第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字、第四引数文字列を指定してください。処理を終了します。"));
		baos.reset();
		
		main(new String[] {"test", "test", "test", "test"});
		
		assertTrue(baos.toString().startsWith(
				"引数指定の誤り：第1引数は整数値を指定します。"));
		baos.reset();
		
		main(new String[] {"test", "test", "999990", "test"});
		
		assertTrue(baos.toString().startsWith(
				"第三引数は0~99999の数字です"));
		baos.reset();
		
		main(new String[] {"test", "test", "-99", "test"});
		
		assertTrue(baos.toString().startsWith(
				"第三引数は0~99999の数字です"));
		baos.reset();
		
		main(new String[] {"フォルダ", "test", "99", "test"});
		
		assertTrue(baos.toString().startsWith(
				"サブフォルダを作成できませんでした"));
		baos.reset();
		
		
		// 任意の日付文字列
		String inpDateStr = "20160606000000";

		// 取り扱う日付の形にフォーマット設定
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMddHHmmss");

		// Date型に変換( DateFromatクラスのperse() )
		Date date = sdformat.parse(inpDateStr);
		Update.date = date;

		main(new String[] {tmpdir, "test", "99", "test"});
		
		String folder  = U + Const.UNDERBAR + "20160606000000" + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL;
		Path subDir = Paths.get(tmpdir);
		Path path = Paths.get(directory + "\\" + U + Const.UNDERBAR + "20160606000000" + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL);
		
		try(val stream = Files.list(subDir)){
			stream.filter(Files::isDirectory).map(name -> name.getFileName().toString()).forEach(name ->{
				assertEquals(folder,name);
			});
		}
		
		String csvFile = U + Const.UNDERBAR + "20160606000000" + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL + Const.CSV;
		String indFile = U + Const.UNDERBAR + "20160606000000" + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL + Const.IND;
	
		try(val stream1 = Files.list(path)){
			stream1.filter(Files::isRegularFile).map(name -> name.getFileName().toString()).forEach(name ->{
				if(name.contains(".csv")) {
					assertEquals(csvFile,name.toString());
					UpdateTest.csv++;
				}else if(name.contains(".ind")) {
					assertEquals(indFile,name.toString());
					UpdateTest.ind++;
				}
			});
		}
		
		assertEquals(1,UpdateTest.csv);
		assertEquals(1,UpdateTest.ind);

		
		System.setOut(so);
		System.out.println(baos.toString());
	}

}
