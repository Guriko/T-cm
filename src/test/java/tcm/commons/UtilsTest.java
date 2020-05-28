package tcm.commons;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class UtilsTest {

	
	@Test
	void Utilsクラス(@TempDir Path directory) throws IOException {
		System.out.println(directory);
		String tmpdir = directory.toString();
		assertEquals(true, Utils.mkSubDir(tmpdir +Const.SLASH + Const.UNDERBAR +  Const.UNDERBAR + Const.P + Const.UNDERBAR + Const.SERIAL));
		
		assertEquals(false, Utils.mkSubDir(tmpdir));
		
		assertEquals(false, Utils.mkFile(tmpdir));
		
		assertEquals(true, Utils.mkFile(tmpdir + "\\abc.ind"));
		
		Date d = new Date();
		SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat d2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String c1 = d1.format(d);
		String c2 = d2.format(d);
		
		Date d3 = new Date();
		SimpleDateFormat d4 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat d5 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String c5 = d4.format(d3);
		String c6 = d5.format(d3);

		assertEquals(c1, Utils.getDate(d));
		assertEquals(c5, Utils.getDate(d3));
		assertEquals(c2, Utils.getDate2(d));
		assertEquals(c6, Utils.getDate2(d3));
		
		boolean result = true;
		for(int i=0;i<1000;i++) {
			int x = Integer.parseInt(Utils.getRandom());
			if(x < 100 || x > 999 ) {
				result = false;
				break;
			}
		}
		assertEquals(true,result);
	}

}
