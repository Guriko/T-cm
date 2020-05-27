package tcm;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import lombok.SneakyThrows;
import lombok.val;

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

		main(new String[] {tmpdir, "P023", "11111"});


		System.setOut(so);
		System.out.println(baos.toString());
	}
}