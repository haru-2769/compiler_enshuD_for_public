package enshud.s1.lexer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lexer {

	/**
	 * サンプルmainメソッド．
	 * 単体テストの対象ではないので自由に改変しても良い．
	 */
	public static void main(final String[] args) {
		// normalの確認
		System.out.println(new Lexer().run("data/pas/normal01.pas", "tmp/out.ts"));
	}

	/**
	 * TODO
	 * 
	 * 開発対象となるLexer実行メソッド．
	 * 以下の仕様を満たすこと．
	 * 
	 * 仕様:
	 * 第一引数で指定されたpasファイルを読み込み，トークン列に分割する．
	 * トークン列は第二引数で指定されたtsファイルに書き出すこと．
	 * 正常に処理が終了した場合は"OK"を，入力ファイルが見つからない場合は"File not found"を返す．
	 * 
	 * @param inputFileName 入力pasファイル名
	 * @param outputFileName 出力tsファイル名
	 */
	public String run(final String inputFileName, final String outputFileName) {
		
		final List<String> buffer;
		try {
			buffer = Files.readAllLines(Paths.get(inputFileName));
		} catch (IOException ex) {
			return "File not found"; 
		}
		
		Automaton autoMaton = new Automaton();
		for (String line : buffer) {
			if (!autoMaton.transitionState((line+"\n"))) {
				return "NG";
			}
		}
		final List<String> results = autoMaton.getResult();
		try {
			System.out.println(results);
			Files.write(Paths.get(outputFileName), results);
		} catch (IOException ex) {
			return "File not found";
		}
		return "OK";
		
	}
	
	
}
