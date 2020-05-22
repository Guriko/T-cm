package tcm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class Input {
	
	private static final String R = "R";
	
	public static final String NEW_LINE = "\r\n";
	public static final String COMMA = ",";
	public static final String SLASH = "\\";
	public static final String QUOTATION = "\"";
	public static final String P = "P";
	public static final String UNDERBAR = "_";
	public static final String PDF = ".pdf";
	public static final String CSV = ".csv";
	public static final String IND = ".ind";
	public static final String SERIAL = "000";
	public static final String SOLID1 = "テスト";
	public static final String SOLID2 = "分類1";
	public static final String SOLID3 = "登録";
	
	public static void main(String[] args) throws IOException {

		//引数なしの時引数指定を促し処理を終了
		if (args.length == 0){
			System.out.println("第一引数にフォルダを指定してください。処理を終了します。");
			return;
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
		String pdfChengeName = "";
		String proPdf = properties.getProperty("pdffile");
		String proUniqueNumber = properties.getProperty("unique");
		int uniqueNumber = Integer.parseInt(proUniqueNumber);

		//日付取得
		Date d = new Date(); 
		SimpleDateFormat d1 = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat d2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String c1 = d1.format(d); 
		String c2 = d2.format(d); 


		//100~999のランダムな数字を取得
		Random random = new Random();
		int randomValue = random.nextInt(900) + 100;
		String id = Integer.toString(randomValue);


		//サブディレクトリ作成
		subDirName = SLASH + R + UNDERBAR + c1 + UNDERBAR + P + id + UNDERBAR + SERIAL;
		args[0] += subDirName;
		File newdir = new File(args[0]);
		if(!newdir.mkdir()) {
			System.out.println("サブフォルダを作成できませんでした");
			return;
		}


		//作成したサブフォルダにpdfファイルを作成
		pdfChengeName = SLASH + c1 + UNDERBAR + P + id + PDF;
		File propertiPdf = new File(proPdf);
		File newPdfFile = new File(args[0] + pdfChengeName);
		propertiPdf.renameTo(newPdfFile);
		newPdfFile.createNewFile();


		//作成したサブフォルダにcsvファイルを作成
		csvFileName = SLASH + c1 + UNDERBAR + P + id + CSV;
		File csvFile = new File(args[0] + csvFileName);
		csvFile.createNewFile();

		//作成したサブフォルダにindファイルを作成
		indFileName = SLASH + c1 + UNDERBAR + P + id + IND;
		File indFile = new File(args[0] + indFileName);
		indFile.createNewFile();

		//作成したcsvファイルに内容を入れていく
		try(FileWriter fileWriter = new FileWriter(csvFile)){

			fileWriter.append(QUOTATION + P + id + QUOTATION)
					  .append(COMMA)
					  .append(QUOTATION + c2 + QUOTATION)
					  .append(COMMA)
					  .append(QUOTATION + SOLID1 + QUOTATION)
					  .append(COMMA)
					  .append(QUOTATION + SOLID2 + QUOTATION)
					  .append(COMMA)
					  .append(QUOTATION + SOLID3 + c1 + QUOTATION)
					  .append(COMMA)
					  .append(QUOTATION + c1 + UNDERBAR + P + id + PDF + QUOTATION)
					  .append(COMMA)
					  .append(QUOTATION + String.format("%05d", uniqueNumber) + QUOTATION)
					  .append(NEW_LINE);

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
