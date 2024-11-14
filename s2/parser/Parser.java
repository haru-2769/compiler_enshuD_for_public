package enshud.s2.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Parser {
	private List<Token> tokenList;
	/**
	 * サンプルmainメソッド．
	 * 単体テストの対象ではないので自由に改変しても良い．
	 */
	public static void main(final String[] args) {
		// normalの確認
		System.out.println(new Parser().run("data/ts/normal01.ts"));
	}

	/**
	 * TODO
	 * 
	 * 開発対象となるParser実行メソッド．
	 * 以下の仕様を満たすこと．
	 * 
	 * 仕様:
	 * 第一引数で指定されたtsファイルを読み込み，構文解析を行う．
	 * 構文が正しい場合は"OK"を，正しくない場合は"Syntax error: line"という文字列とともに，
	 * 最初のエラーを見つけた行の番号を返すこと （例: "Syntax error: line 1"）．
	 * 入力ファイル内に複数のエラーが含まれる場合は，最初に見つけたエラーのみを返すこと．
	 * 入力ファイルが見つからない場合は"File not found"を返すこと．
	 * 
	 * @param inputFileName 入力tsファイル名
	 */
	public String run(final String inputFileName) {
		try {
			final List<String> buffer;
			buffer = Files.readAllLines(Paths.get(inputFileName));
			this.tokenList = new ArrayList<>();
			for(String line: buffer) {
				tokenList.add(new Token(line));
			}
			ProgramNode programNode = new ProgramNode();
			programNode.parse(new Context(tokenList));
		} catch (IOException ex) {
			return "File not found"; 
		} catch (SyntaxException ex) {
			return ex.getMessage();
		}
		return "OK";
	}
}