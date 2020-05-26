package tcm.commons;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class UtilsTest {

	
	@Test
	void Utilsクラス() throws IOException {
		Utils util = new Utils();
		assertEquals(true, util.MkSubDir("C:\\location" +Const.SLASH + Const.UNDERBAR +  Const.UNDERBAR + Const.P + Const.UNDERBAR + Const.SERIAL));
		
		assertEquals(false, util.MkSubDir("C:\\location"));
		
		assertEquals(false, util.MkIndFile("C:\\location"));
		
		assertEquals(true, util.MkIndFile("C:\\location" + "\\abc.ind"));
	}

}
