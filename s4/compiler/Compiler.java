package enshud.s4.compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import enshud.casl.CaslSimulator;
import enshud.s1.lexer.Lexer;

public class Compiler {
	
	/**
	 * サンプルmainメソッド．
	 * 単体テストの対象ではないので自由に改変しても良い．
	 */
	public static void main(final String[] args) {
		// Compilerを実行してcasを生成する
		System.out.println(new Lexer().run("data/pas/normal11.pas", "lib/out.ts"));
		System.out.println(new Compiler().run("lib/out.ts", "lib/out.cas"));

		// 上記casを，CASLアセンブラ & COMETシミュレータで実行する
//		CaslSimulator.run("tmp/out.cas", "tmp/out.ans");
	}

	/**
	 * TODO
	 * 
	 * 開発対象となるCompiler実行メソッド．
	 * 以下の仕様を満たすこと．
	 * 
	 * 仕様:
	 * 第一引数で指定されたtsファイルを読み込み，CASL IIプログラムにコンパイルする．
	 * コンパイル結果のCASL IIプログラムは第二引数で指定されたcasファイルに書き出し"OK"という文字列を返すこと．
	 * 構文的もしくは意味的なエラーを発見した場合はエラーメッセージを返すこと．
	 * （エラーメッセージの内容はChecker.run()の出力に準ずる．）
	 * 入力ファイルが見つからない場合は"File not found"を返すこと．
	 * 
	 * @param inputFileName 入力tsファイル名
	 * @param outputFileName 出力casファイル名
	 */
	public String run(final String inputFileName, final String outputFileName) {
		List<Token> tokenList;
		try {
			tokenList = new ArrayList<>();
			for(String line: Files.readAllLines(Paths.get(inputFileName))) {
				tokenList.add(new Token(line));
			}
			ProgramNode programNode = new ProgramNode();
			programNode.parse(new Context(tokenList));
			AstChecker astChecker = new AstChecker();
			astChecker.start(programNode);
			AstCompiler astCompiler = new AstCompiler();
			astCompiler.start(programNode);
			Files.write(Paths.get(outputFileName), astCompiler.getCaslCode());
			CaslSimulator.appendLibcas(outputFileName);
		} catch (IOException ex) {
			return "File not found"; 
		} catch (final SyntaxException ex) {
			//ex.printStackTrace();
			return ex.getMessage();
		} catch (final SemanticException ex) {
			return ex.getMessage();
		}
		return "OK";

	}
}
