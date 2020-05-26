package tcm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tcm.commons.Const;
import tcm.commons.Utils;

public class Delete extends Input{

	private static final String D = "D";

	public static void main(String[] args) throws IOException {

		int number = 0;
		//引数なしの時引数指定を促し処理を終了
		if (args.length < 3){
			System.out.println("第一引数にフォルダを指定、第二引数「Pxxx」、第三引数「0~99999」の数字を指定してください。処理を終了します。");
			return;
		}
		try {
			number = Integer.parseInt(args[2]);
		} catch (NumberFormatException e) {
			System.err.println("引数指定の誤り：第三引数は整数値を指定します。");
			return;
		}
		if(number < 0 || number > 99999) {
			System.err.println("第三引数は0~99999の数字です");
			return;
		}


		String subDirName = "";
		String csvFileName = "";
		String indFileName = "";

		//日付取得
		//		Date d = new Date(); 
		//		SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMddHHmmss");
		//		String c1 = d1.format(d); 

		String c1 = Utils.getDate();


		//100~999のランダムな数字を取得
		//		Random random = new Random();
		//		int randomValue = random.nextInt(900) + 100;
		//		String id = Integer.toString(randomValue);
		String id = Utils.getRandom();

		//サブディレクトリ作成
		subDirName = args[0] + Const.SLASH + D + Const.UNDERBAR + c1 + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL;
		if(!Utils.mkSubDir(subDirName)){
			System.out.println("サブフォルダを作成できませんでした");
			return;
		}


		//作成したサブフォルダにcsvファイルを作成
		csvFileName = subDirName + Const.SLASH + D + Const.UNDERBAR + c1 + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL + Const.CSV;
		if(!Utils.mkFile(csvFileName)) {
			return;
		}


		//作成したサブフォルダにindファイルを作成
		indFileName =subDirName +  Const.SLASH + D + Const.UNDERBAR + c1 + Const.UNDERBAR + Const.P + id + Const.IND;
		if(!Utils.mkFile(indFileName)) {
			return;
		}


		//作成したcsvファイルに内容を入れていく
		File csvFile = new File(csvFileName);
		try(FileWriter fileWriter = new FileWriter(csvFile)){

			fileWriter.append(Const.QUOTATION + args[1] + Const.QUOTATION)
			.append(Const.COMMA)
			.append(Const.QUOTATION + String.format("%05d", number) + Const.QUOTATION)
			.append(Const.COMMA)
			.append(Const.QUOTATION + D + Const.QUOTATION)
			.append(Const.COMMA)
			.append(Const.COMMA)
			.append(Const.COMMA)
			.append(Const.COMMA)
			.append(Const.COMMA)
			.append(Const.COMMA)
			.append(Const.NEW_LINE);

			fileWriter.flush();
		}
	}
}
