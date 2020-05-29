package tcm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;
import tcm.commons.Const;
import tcm.commons.Utils;
@Slf4j
public class Input {

	public static final String R = "R";
	public static Date date = new Date();
	public static String id = "";

	public static void main(String[] args) throws IOException {

		//引数なしの時引数指定を促し処理を終了
		if (args.length == 0){
			System.out.println("第一引数にフォルダを指定してください。処理を終了します。");
			return;
		}
		
		Path demopath = Paths.get(".\\foo.pdf");
		if(!Files.exists(demopath)) {
			Files.createFile(Paths.get(".\\foo.pdf"));
		}
		
		//application.propertiesファイルを呼び出しておく
		Properties properties = new Properties();
		String confpath = ".\\application.properties";
		try(InputStream istream = new FileInputStream(confpath)){
			properties.load(istream);			
		}

		String subDirName = "";
		String csvFileName = "";
		String indFileName = "";
		Path pdfFile = Paths.get(".\\foo.pdf");
		String proUniqueNumber = properties.getProperty("unique");
		int uniqueNumber = Integer.parseInt(proUniqueNumber);

		//日付取得
		String c1 = Utils.getDate(date);
		String c2 = Utils.getDate2(date);

		//100~999のランダムな数字を取得
		id = Utils.getRandom();

		//サブディレクトリ作成
		subDirName = args[0] + Const.SLASH + R + Const.UNDERBAR + c1 + Const.UNDERBAR + Const.P + id + Const.UNDERBAR + Const.SERIAL;
		if(!Utils.mkSubDir(subDirName)) {
			System.out.println("サブフォルダを作成できませんでした");
			return;
		}


		//作成したサブフォルダにpdfファイルを作成
		Path destinationPath = Paths.get(subDirName + Const.SLASH + c1 + Const.UNDERBAR + Const.P + id + Const.PDF);
		try {
			Files.copy(pdfFile,destinationPath);
		} catch (IOException e) {
			e.printStackTrace();
		}


		//作成したサブフォルダにcsvファイルを作成
		csvFileName = subDirName + Const.SLASH + c1 + Const.UNDERBAR + Const.P + id + Const.CSV;
		if(!Utils.mkFile(csvFileName)) {
			return;
		}


		//作成したサブフォルダにindファイルを作成
		indFileName =subDirName +  Const.SLASH + c1 + Const.UNDERBAR + Const.P + id + Const.IND;
		if(!Utils.mkFile(indFileName)) {
			return;
		}


		//作成したcsvファイルに内容を入れていく
		File csvFile = new File(csvFileName);
		try(FileWriter fileWriter = new FileWriter(csvFile)){

			fileWriter.append(Const.QUOTATION + Const.P + id + Const.QUOTATION)
			.append(Const.COMMA)
			.append(Const.QUOTATION + c2 + Const.QUOTATION)
			.append(Const.COMMA)
			.append(Const.QUOTATION + Const.SOLID1 + Const.QUOTATION)
			.append(Const.COMMA)
			.append(Const.QUOTATION + Const.SOLID2 + Const.QUOTATION)
			.append(Const.COMMA)
			.append(Const.QUOTATION + Const.SOLID3 + c1 + Const.QUOTATION)
			.append(Const.COMMA)
			.append(Const.QUOTATION + c1 + Const.UNDERBAR + Const.P + id + Const.PDF + Const.QUOTATION)
			.append(Const.COMMA)
			.append(Const.QUOTATION + String.format("%05d", uniqueNumber) + Const.QUOTATION)
			.append(Const.NEW_LINE);

			fileWriter.flush();
		}


		//ユニークインデックスが99999なら0にする
		if(uniqueNumber == 99999) {
			properties.setProperty("unique", "0");
		}else {
			uniqueNumber += 1;
			proUniqueNumber = Integer.toString(uniqueNumber);
			properties.setProperty("unique", proUniqueNumber);
		}
		//ユニークインデックスを書き換える
		try(FileOutputStream out = new FileOutputStream("application.properties")){
			properties.store(out , "");
		}


		//作成したCSVファイルを読み込む
		try(BufferedReader reader  = new BufferedReader(new FileReader(csvFile))) {
			log.info(reader.readLine());
		}
		//		String line = "";
		//		int row = 0; 
		// 「,」で分けたりする場合 
		//		while ((line = reader.readLine()) != null) {
		//			// 1行をデータの要素に分割
		//			StringTokenizer st = new StringTokenizer(line, ",");
		//			while (st.hasMoreTokens()) {
		//				// 1行の各要素
		//				if(row == 0) {
		//					log.info(st.nextToken());
		//				}else {
		//					return;
		//				}
		//			}
		//			row++;
		//		}
	}
}
