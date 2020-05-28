package tcm;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import lombok.SneakyThrows;
import lombok.val;
import tcm.commons.Const;

class DeleteTest extends Delete {

	@Test @SneakyThrows
	public void 引数テスト(@TempDir Path directory) {
		System.out.println(directory);
		String tmpdir = directory.toString();
		val so = System.out;

		val baos = new ByteArrayOutputStream();
		val ps = new PrintStream(baos);
		System.setOut(ps); 
		System.setErr(ps);

		main(new String[0]);
		assertTrue(baos.toString().startsWith(
				"第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字を指定してください。処理を終了します。"));
		baos.reset();

		main(new String[] {"test"});
		assertTrue(baos.toString().startsWith(
				"第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字を指定してください。処理を終了します。"));
		baos.reset();

		main(new String[] {"test", "xxx"});
		assertTrue(baos.toString().startsWith(
				"第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字を指定してください。処理を終了します。"));
		baos.reset();

		main(new String[] {"test", "test", "test"});
		assertTrue(baos.toString().startsWith(
				"引数指定の誤り：第三引数は整数値を指定します。"));
		baos.reset();

		main(new String[] {"C:\\location", "P023", "111111"});
		assertTrue(baos.toString().startsWith(
				"第三引数は0~99999の数字です"));
		baos.reset();

		main(new String[] {"C:\\location", "P023", "-2"});
		assertTrue(baos.toString().startsWith(
				"第三引数は0~99999の数字です"));
		baos.reset();

		main(new String[] {"test", "test", "123"});
		assertTrue(baos.toString().startsWith(
				"サブフォルダを作成できませんでした"));
		baos.reset();



		
		
		// 任意の日付文字列
		String inpDateStr = "20160606000000";

		// 取り扱う日付の形にフォーマット設定
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMddHHmmss");

		// Date型に変換( DateFromatクラスのperse() )
		Date date = sdformat.parse(inpDateStr);
		Delete.date = date;

		main(new String[] {tmpdir, "P023", "11111"});
		
		String folder  = D + Const.UNDERBAR + "20160606000000" + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL;
		File dir = new File(tmpdir);
		File[] list = dir.listFiles();
		for(int i=0; i<list.length; i++) {
			if(list[i].isDirectory()) {
				assertEquals(folder,list[i].getName().toString());
			}
		}
		
		String csvFile = D + Const.UNDERBAR + "20160606000000" + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL + Const.CSV;
		String indFile = D + Const.UNDERBAR + "20160606000000" + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL + Const.IND;
		
		File dir2 = new File(tmpdir + "\\" +list[0].getName());

		File[] list2 = dir2.listFiles();
		for(int j=0; j<list2.length; j++) {
			if(list2[j].getName().contains(".csv")) {
				assertEquals(csvFile,list2[j].getName().toString());
			}else if(list2[j].getName().contains(".ind")) {
				assertEquals(indFile,list2[j].getName().toString());
			}
		}

		System.setOut(so);
		System.out.println(baos.toString());
	}
}