package tcm.commons;

import java.io.File;
import java.io.IOException;

public class Utils {
	
	public boolean MkSubDir(String subDirName) {
		//subDirName = SLASH + R + UNDERBAR + c1 + UNDERBAR + P + id + UNDERBAR + SERIAL;
		//args[0] += subDirName;
		File newdir = new File(subDirName);
		if(!newdir.mkdir()) {
			return false;
		}
		return true;
	}
	
	public boolean MkIndFile(String indFile) throws IOException {
		File mkindFile = new File(indFile);
		if(!mkindFile.createNewFile()){
			return false;
		}
		return true;
	}

}
