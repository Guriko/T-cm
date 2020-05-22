package tcm;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import lombok.val;

class DeleteTest extends Delete {

	@Test @SneakyThrows
	public void 引数なしのテスト() {
		val baos = new ByteArrayOutputStream();
		val ps = new PrintStream(baos);
		System.setOut(ps); 
		
		main(new String[0]);
		
		assertTrue(baos.toString().startsWith(
			"第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字を指定してください。処理を終了します。")
		);
	}

}
