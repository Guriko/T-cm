package tcm;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import lombok.val;

class InputTest extends Input{

	@Test @SneakyThrows
	void 引数テスト() {
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
		
		main(new String[] {"C:\\location"});
		
		System.setOut(so);
		System.out.println(baos.toString());
	}

}
