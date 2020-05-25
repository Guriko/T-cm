package tcm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import tcm.commons.Const;
import tcm.commons.Utils;

public class Update extends Input{

	private static final String U = "U";

	public static void main(String[] args) throws IOException{
		int number = 0;
		//引数なしの時引数指定を促し処理を終了
		if (args.length < 4){
			System.out.println("第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字、第四引数文字列を指定してください。処理を終了します。");
			return;
		}
		try {
			number = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			System.err.println("引数指定の誤り：第1引数は整数値を指定します。");
			return;
		}
		if(number < 0 || number > 99999) {
			System.err.println("第三引数は0~99999の数字です");
			return;
		}

		Utils util = new Utils();
		
		String subDirName = "";
		String csvFileName = "";
		String indFileName = "";

		//日付取得
		Date d = new Date(); 
		SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMddHHmmss");
		String c1 = d1.format(d); 


		//100~999のランダムな数字を取得
		Random random = new Random();
		int randomValue = random.nextInt(900) + 100;
		String id = Integer.toString(randomValue);


		//サブディレクトリ作成
		subDirName = args[0] + Const.SLASH + U + Const.UNDERBAR + c1 + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL;
		if(!util.MkSubDir(subDirName)) {
			System.out.println("サブフォルダを作成できませんでした");
			return;
		}

		//作成したサブフォルダにcsvファイルを作成
		csvFileName = Const.SLASH + U + Const.UNDERBAR + c1 + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL + Const.CSV;
		File csvFile = new File(args[0] + csvFileName);
		csvFile.createNewFile();


		//作成したサブフォルダにindファイルを作成
		indFileName =args[0] +  Const.SLASH + c1 + Const.UNDERBAR + Const.P + id + Const.IND;
		if(!util.MkIndFile(indFileName)) {
			return;
		}


		//作成したcsvファイルに内容を入れていく
		try(FileWriter fileWriter = new FileWriter(csvFile)){

			fileWriter.append(Const.QUOTATION + args[1] + Const.QUOTATION)
					  .append(Const.COMMA)
					  .append(Const.QUOTATION + String.format("%05d", number) + Const.QUOTATION)
					  .append(Const.COMMA)
					  .append(Const.QUOTATION + U + Const.QUOTATION)
					  .append(Const.COMMA)
					  .append(Const.COMMA)
					  .append(Const.COMMA)
					  .append(args[3])
					  .append(Const.NEW_LINE);

			fileWriter.flush();
		}
	}
}
