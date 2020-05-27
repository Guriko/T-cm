package tcm;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import lombok.SneakyThrows;
import lombok.val;

class InputTest extends Input{

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
				"第一引数にフォルダを指定してください。処理を終了します。"));
		baos.reset();
		
		main(new String[] {"test"});
		
		assertTrue(baos.toString().startsWith(
				"サブフォルダを作成できませんでした"));
		baos.reset();
		
		
		main(new String[] {tmpdir});
		
		System.setOut(so);
		System.out.println(baos.toString());
	}
}
