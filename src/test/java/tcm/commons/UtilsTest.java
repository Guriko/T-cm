package tcm.commons;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class UtilsTest {

	
	@Test
	void Utilsクラス() throws IOException {
		assertEquals(true, Utils.mkSubDir("C:\\location" +Const.SLASH + Const.UNDERBAR +  Const.UNDERBAR + Const.P + Const.UNDERBAR + Const.SERIAL));
		
		assertEquals(false, Utils.mkSubDir("C:\\location"));
		
		assertEquals(false, Utils.mkFile("C:\\location"));
		
		assertEquals(true, Utils.mkFile("C:\\location" + "\\abc.ind"));
	}

}
