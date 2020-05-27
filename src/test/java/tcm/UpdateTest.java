package tcm;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import lombok.SneakyThrows;
import lombok.val;

class UpdateTest extends Update{

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
		
		
		main(new String[] {tmpdir, "test", "99", "test"});
		
		System.setOut(so);
		System.out.println(baos.toString());
	}

}
