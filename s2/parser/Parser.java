package enshud.s2.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Parser {
	private List<Token> tokenList;
	private int index;
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
		// TODO
		final List<String> buffer;
		try {
			buffer = Files.readAllLines(Paths.get(inputFileName));
		} catch (IOException ex) {
			return "File not found"; 
		}
		
		this.tokenList = new ArrayList<>();
		for(String line: buffer) {
			tokenList.add(new Token(line));
		}
		
		try {
			parseProgram();
		} catch (final SyntaxException ex) {
			return ex.getError();
		}
		
		return "OK";
	}
	
	private void checkTerminalSymbol(String... terminalSymbols) throws SyntaxException {
		if (this.index > this.tokenList.size() - 1) {
			throw new SyntaxException(this.tokenList.get(this.tokenList.size() - 1)); 
		}
		Token token = this.tokenList.get(this.index++);
		
		for (String symbol: terminalSymbols) {
			if (token.getTokenName().equals(symbol)) {
				return;
			}
		}
		throw new SyntaxException(token);
	}
	
	// private Token first(int offset) throws SyntaxException {
	// 	if (this.index > this.tokenList.size() - 1) {
	// 		throw new SyntaxException(this.tokenList.get(this.tokenList.size() - 1)); 
	// 	}
	// 	return this.tokenList.get(this.index + offset);
	// }

	private boolean equalsAny(String... terminalSymbols) throws SyntaxException {
		for (String symbol : terminalSymbols) {
			if (this.index > this.tokenList.size() - 1) {
				throw new SyntaxException(this.tokenList.get(this.tokenList.size() - 1));
			}
			if (this.tokenList.get(this.index).getTokenName().equals(symbol)) {
				return true;
			}
		}
		return false;
	}
	
	// プログラム名
	private void parseProgram() throws SyntaxException {
		checkTerminalSymbol("SPROGRAM");
		parseProgramName();
		checkTerminalSymbol("SSEMICOLON");
		parseBlock();
		parseCompoundStatement();
		checkTerminalSymbol("SDOT");
		if (this.index != this.tokenList.size()) {
			throw new SyntaxException(this.tokenList.get(this.index-1));
		}
	}

	// プログラム名
	private void parseProgramName() throws SyntaxException {
		checkTerminalSymbol("SIDENTIFIER");
	}

	// ブロック
	private void parseBlock() throws SyntaxException { 	 
		parseVariableDeclaration();
		parseSubprogramDeclarationSequence();
	}

	// 変数宣言
	private void parseVariableDeclaration() throws SyntaxException {
		if (equalsAny("SVAR")) {
			checkTerminalSymbol("SVAR");
			parseVariableDeclarationSequence();
		} 
	}

	// 変数宣言の並び
	private void parseVariableDeclarationSequence() throws SyntaxException { 	  
		//TODO
	}

	// 変数名の並び
	private void parseVariableNameSequence() throws SyntaxException {
		//TODO
	}

	// 変数名
	private void parseVariableName() throws SyntaxException { 	 
		checkTerminalSymbol("SIDENTIFIER");
	}

	// 型
	private void parseType() throws SyntaxException { 	 
		//TODO
	}

	// 標準型
	private void parseStandardType() throws SyntaxException { 	
		checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN"); 
	}

	// 配列型
	private void parseArrayType() throws SyntaxException { 	 
		checkTerminalSymbol("SARRAY");
		checkTerminalSymbol("SLBRACKET");
		parseIndexMinValue();
		checkTerminalSymbol("SRANGE");
		parseIndexMaxValue();
		checkTerminalSymbol("SRBRACKET");
		checkTerminalSymbol("SOF");
		parseStandardType();
	}

	// 添え字の最小値
	private void parseIndexMinValue() throws SyntaxException {
		parseInteger();
	}

	// 添え字の最大値
	private void parseIndexMaxValue() throws SyntaxException {
		parseInteger();
	}

	// 整数
	private void parseInteger() throws SyntaxException { 	
		if (equalsAny("SPLUS", "SMINUS")) {
			parseSign();
		}
		checkTerminalSymbol("SCONSTANT");
	}

	// 符号
	private void parseSign() throws SyntaxException {
		checkTerminalSymbol("SPLUS", "SMINUS");
	}

	// 副プログラム宣言群
	private void parseSubprogramDeclarationSequence() throws SyntaxException { 	
		//TODO
	}

	// 副プログラム宣言
	private void parseSubprogramDeclaration() throws SyntaxException { 	 
		parseSubprogramHead();
		parseVariableDeclaration();
		parseCompoundStatement();
	}

	// 副プログラム頭部
	private void parseSubprogramHead() throws SyntaxException { 
		checkTerminalSymbol("SPROCEDURE");
		parseProcedureName();
		parseFormalParameter();
		checkTerminalSymbol("SSEMICOLON");
	}

	// 手続き名
	private void parseProcedureName() throws SyntaxException { 	 
		checkTerminalSymbol("SIDENTIFIER");
	}

	// 仮パラメータ
	private void parseFormalParameter() throws SyntaxException { 
		if (equalsAny("SLPAREN")) {
			checkTerminalSymbol("SLPAREN");
			parseFormalParameterSequence();
			checkTerminalSymbol("SRPAREN");
		}
	}

	// 仮パラメータの並び
	private void parseFormalParameterSequence() throws SyntaxException { 	 
		//TODO
	}

	// 仮パラメータ名の並び
	private void parseFormalParameterNameSequence() throws SyntaxException { 	
		//TODO 
	}

	// 仮パラメータ名
	private void parseFormalParameterName() throws SyntaxException { 	
		checkTerminalSymbol("SIDENTIFIER"); 
	}

	// 複合文
	private void parseCompoundStatement() throws SyntaxException { 	
		checkTerminalSymbol("SBEGIN");
		parseStatementSequence();
		checkTerminalSymbol("SEND"); 
	}

	// 文の並び
	private void parseStatementSequence() throws SyntaxException { 	 
		//TODO
	}

	// 文
	private void parseStatement() throws SyntaxException { 	
		//TODO 
	}

	// 基本文
	private void parseBasicStatement() throws SyntaxException { 	 
		//TODO 
	}

	// 代入文
	private void parseAssignmentStatement() throws SyntaxException {
		parseLeftHandSide();
		checkTerminalSymbol("SASSIGN");
		parseExpression();
	}

	// 左辺
	private void parseLeftHandSide() throws SyntaxException { 	 
		parseVariable();
	}

	// 変数
	private void parseVariable() throws SyntaxException { 	
		//TODO 
	}

	// 純変数
	private void parsePureVariable() throws SyntaxException { 	
		parseVariableName(); 
	}

	// 添え字付き変数
	private void parseIndexedVariable() throws SyntaxException { 
		parseVariableName();
		checkTerminalSymbol("SLBRACKET");
		parseIndex();
		checkTerminalSymbol("SRBRACKET");	 
	}

	// 添え字
	private void parseIndex() throws SyntaxException { 
		parseExpression();	 
	}

	// 手続き呼出し文
	private void parseProcedureCallStatement() throws SyntaxException { 
		parseProcedureName(); 
		if (equalsAny("SLPAREN")) {
			checkTerminalSymbol("SLPAREN");
			parseExpressionSequence();
			checkTerminalSymbol("SRPAREN");
		}
	}

	// 式の並び
	private void parseExpressionSequence() throws SyntaxException { 
		//TODO 
	}

	// 式
	private void parseExpression() throws SyntaxException { 
		//TODO	 
	}

	// 単純式
	private void parseSimpleExpression() throws SyntaxException {
		if (equalsAny("SPLUS", "SMINUS")) {
			parseSign();
		}
		parseTerm();
		while (equalsAny("SPLUS", "SMINUS", "SOR")) {
			parseAdditiveOperator();
			parseTerm();
		}
	}

	// 項
	private void parseTerm() throws SyntaxException { 
		parseFactor();
		while (equalsAny("SSTAR", "SDIVD", "SMOD", "SAND")) {
			parseMultiplicativeOperator();
			parseFactor();
		}	 
	}

	// 因子
	private void parseFactor() throws SyntaxException { 	 
		//TODO
	}

	// 関係演算子
	private void parseRelationalOperator() throws SyntaxException {
		checkTerminalSymbol("SEQUAL", "SNOTEQUAL", "SLESS", "SLESSEQUAL", "SGREAT", "SGREATEQUAL"); 	 
	}

	// 加法演算子
	private void parseAdditiveOperator() throws SyntaxException {
		checkTerminalSymbol("SPLUS", "SMINUS", "SOR"); 
	}

	// 乗法演算子
	private void parseMultiplicativeOperator() throws SyntaxException {
		checkTerminalSymbol("SSTAR", "SDIVD", "SMOD", "SAND"); 	 
	}

	// 入出力文
	private void parseInputOutputStatement() throws SyntaxException { 	 
		//TODO
	}

	// 変数の並び
	private void parseVariableSequence() throws SyntaxException { 
		parseVariable();
		while (equalsAny("SCOMMA")) {
			checkTerminalSymbol("SCOMMA");
			parseVariable();
		}	 
	}

	// 定数
	private void parseConstant() throws SyntaxException { 	
		//TODO 
	}

}
