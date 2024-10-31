package enshud.s3.checker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Checker {
	private List<Token> tokenList;
	private int index;
	/**
	 * サンプルmainメソッド．
	 * 単体テストの対象ではないので自由に改変しても良い．
	 */
	public static void main(final String[] args) {
		// normalの確認
		System.out.println(new Checker().run("data/ts/normal02.ts"));
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
			final AstNode rootNode = parseProgram();
			final AstChecker astChecker = new AstChecker();
			rootNode.accept(astChecker);
		} catch (final SyntaxException ex) {
			return ex.getError();
		}
		
		return "OK";
	}
	
	private Token checkTerminalSymbol(String... terminalSymbols) throws SyntaxException {
		if (this.index > this.tokenList.size() - 1) {
			throw new SyntaxException(this.tokenList.get(this.tokenList.size() - 1)); 
		}
		Token token = this.tokenList.get(this.index++);
		
		for (String symbol: terminalSymbols) {
			if (token.getTokenName().equals(symbol)) {
				return token;
			}
		}
		throw new SyntaxException(token);
	}

	private boolean equalsAny(int offset, String... terminalSymbols) throws SyntaxException {
		for (String symbol : terminalSymbols) {
			if (this.index + offset > this.tokenList.size() - 1) {
				throw new SyntaxException(this.tokenList.get(this.tokenList.size() - 1));
			}
			if (this.tokenList.get(this.index + offset).getTokenName().equals(symbol)) {
				return true;
			}
		}
		return false;
	}
	
	// プログラム名
	private AstNode parseProgram() throws SyntaxException {
		final NonTerminalNode programNode = new NonTerminalNode(NonTerminalType.PROGRAM);
		programNode.addChild(new TerminalNode(checkTerminalSymbol("SPROGRAM")));
		programNode.addChild(parseProgramName());
		programNode.addChild(new TerminalNode(checkTerminalSymbol("SSEMICOLON")));
		programNode.addChild(parseBlock());
		programNode.addChild(parseCompoundStatement());
		programNode.addChild(new TerminalNode(checkTerminalSymbol("SDOT")));
		if (this.index != this.tokenList.size()) {
			throw new SyntaxException(this.tokenList.get(this.index-1));
		}
		return programNode;
	}

	// プログラム名
	private AstNode parseProgramName() throws SyntaxException {
		return new TerminalNode(checkTerminalSymbol("SIDENTIFIER"));
	}

	// ブロック
	private AstNode parseBlock() throws SyntaxException {
		final NonTerminalNode blockNode = new NonTerminalNode(NonTerminalType.BLOCK);
		blockNode.addChild(parseVariableDeclaration());
		blockNode.addChild(parseSubprogramDeclarationSequence());
		return blockNode;
	}

	// 変数宣言
	private AstNode parseVariableDeclaration() throws SyntaxException {
		final NonTerminalNode variableDeclarationNode = new NonTerminalNode(NonTerminalType.VARIABLEDECLARATION);
		if (equalsAny(0, "SVAR")) {
			variableDeclarationNode.addChild(new TerminalNode(checkTerminalSymbol("SVAR")));
			variableDeclarationNode.addChild(parseVariableDeclarationSequence());
		} 
		return variableDeclarationNode;
	}

	// 変数宣言の並び
	private AstNode parseVariableDeclarationSequence() throws SyntaxException {
		final NonTerminalNode variableDeclarationSequenceNode = new NonTerminalNode(NonTerminalType.VARIABLEDECLARATIONSEQUENCE);
		do {
			variableDeclarationSequenceNode.addChild(parseVariableNameSequence());
			variableDeclarationSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SCOLON")));
			variableDeclarationSequenceNode.addChild(parseType());
			variableDeclarationSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SSEMICOLON")));
		} while (equalsAny(0, "SIDENTIFIER"));	
		return variableDeclarationSequenceNode;
	}

	// 変数名の並び
	private AstNode parseVariableNameSequence() throws SyntaxException {
		final NonTerminalNode variableNameSequenceNode = new NonTerminalNode(NonTerminalType.VARIABLENAMESEQUENCE);
		variableNameSequenceNode.addChild(parseVariableName());
		while (equalsAny(0, "SCOMMA")) {
			variableNameSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SCOMMA")));
			variableNameSequenceNode.addChild(parseVariableName());
		}
		return variableNameSequenceNode;
	}

	// 変数名
	private AstNode parseVariableName() throws SyntaxException { 
		return new TerminalNode(checkTerminalSymbol("SIDENTIFIER"));
	}

	// 型
	private AstNode parseType() throws SyntaxException { 	
		final NonTerminalNode typeNode = new NonTerminalNode(NonTerminalType.TYPE);
		if (equalsAny(0, "SINTEGER", "SCHAR", "SBOOLEAN")) {
			typeNode.addChild(parseStandardType());
		} else if (equalsAny(0, "SARRAY")) {
			typeNode.addChild(parseArrayType());
		} else {
			throw new SyntaxException(this.tokenList.get(this.index));
		}
		return typeNode;
	}

	// 標準型
	private AstNode parseStandardType() throws SyntaxException {
		return new TerminalNode(checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN"));
	}

	// 配列型
	private AstNode parseArrayType() throws SyntaxException {
		final NonTerminalNode arrayTypeNode = new NonTerminalNode(NonTerminalType.ARRAYTYPE);
		arrayTypeNode.addChild(new TerminalNode(checkTerminalSymbol("SARRAY")));
		arrayTypeNode.addChild(new TerminalNode(checkTerminalSymbol("SLBRACKET")));
		arrayTypeNode.addChild(parseIndexMinValue());
		arrayTypeNode.addChild(new TerminalNode(checkTerminalSymbol("SRANGE")));
		arrayTypeNode.addChild(parseIndexMaxValue());
		arrayTypeNode.addChild(new TerminalNode(checkTerminalSymbol("SRBRACKET")));
		arrayTypeNode.addChild(new TerminalNode(checkTerminalSymbol("SOF")));
		arrayTypeNode.addChild(parseStandardType());
		return arrayTypeNode;
	}

	// 添え字の最小値
	private AstNode parseIndexMinValue() throws SyntaxException {
		final NonTerminalNode indexMinValueNode = new NonTerminalNode(NonTerminalType.INDEXMINVALUE);
		indexMinValueNode.addChild(parseInteger());
		return indexMinValueNode;
	}

	// 添え字の最大値
	private AstNode parseIndexMaxValue() throws SyntaxException {
		final NonTerminalNode indexMaxValueNode = new NonTerminalNode(NonTerminalType.INDEXMAXVALUE);
		indexMaxValueNode.addChild(parseInteger());
		return indexMaxValueNode;
	}

	// 整数
	private AstNode parseInteger() throws SyntaxException { 
		final NonTerminalNode integerNode = new NonTerminalNode(NonTerminalType.INTEGER);	
		if (equalsAny(0, "SPLUS", "SMINUS")) {
			integerNode.addChild(parseSign());
		}
		integerNode.addChild(new TerminalNode(checkTerminalSymbol("SCONSTANT")));
		return integerNode;
	}

	// 符号
	private AstNode parseSign() throws SyntaxException {
		return new TerminalNode(checkTerminalSymbol("SPLUS", "SMINUS"));
	}

	// 副プログラム宣言群
	private AstNode parseSubprogramDeclarationSequence() throws SyntaxException { 
		final NonTerminalNode subprogramDeclarationSequenceNode = new NonTerminalNode(NonTerminalType.SUBPROGRAMDECLARATIONSEQUENCE);	
		while (equalsAny(0, "SPROCEDURE")) {
			subprogramDeclarationSequenceNode.addChild(parseSubprogramDeclaration());
			subprogramDeclarationSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SSEMICOLON")));
		}
		return subprogramDeclarationSequenceNode;
	}

	// 副プログラム宣言
	private AstNode parseSubprogramDeclaration() throws SyntaxException {
		final NonTerminalNode subprogramDeclarationNode = new NonTerminalNode(NonTerminalType.SUBPROGRAMDECLARATION); 	 
		subprogramDeclarationNode.addChild(parseSubprogramHead());
		subprogramDeclarationNode.addChild(parseVariableDeclaration());
		subprogramDeclarationNode.addChild(parseCompoundStatement());
		return subprogramDeclarationNode;
	}

	// 副プログラム頭部
	private AstNode parseSubprogramHead() throws SyntaxException {
		final NonTerminalNode subprogramHeadNode = new NonTerminalNode(NonTerminalType.SUBPROGRAMHEAD); 
		subprogramHeadNode.addChild(new TerminalNode(checkTerminalSymbol("SPROCEDURE")));
		subprogramHeadNode.addChild(parseProcedureName());
		subprogramHeadNode.addChild(parseFormalParameter());
		subprogramHeadNode.addChild(new TerminalNode(checkTerminalSymbol("SSEMICOLON")));
		return subprogramHeadNode;
	}

	// 手続き名
	private AstNode parseProcedureName() throws SyntaxException { 
		return new TerminalNode(checkTerminalSymbol("SIDENTIFIER"));
	}

	// 仮パラメータ
	private AstNode parseFormalParameter() throws SyntaxException { 
		final NonTerminalNode formalParameterNode = new NonTerminalNode(NonTerminalType.FORMALPARAMETER);
		if (equalsAny(0, "SLPAREN")) {
			formalParameterNode.addChild(new TerminalNode(checkTerminalSymbol("SLPAREN")));
			formalParameterNode.addChild(parseFormalParameterSequence());
			formalParameterNode.addChild(new TerminalNode(checkTerminalSymbol("SRPAREN")));
		}
		return formalParameterNode;
	}

	// 仮パラメータの並び
	private AstNode parseFormalParameterSequence() throws SyntaxException {
		final NonTerminalNode formalParameterSequenceNode = new NonTerminalNode(NonTerminalType.FORMALPARAMETERSEQUENCE);	 
		formalParameterSequenceNode.addChild(parseFormalParameterNameSequence());
		formalParameterSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SCOLON")));
		formalParameterSequenceNode.addChild(parseStandardType());
		while (equalsAny(0, "SCOMMA")) {
			formalParameterSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SCOMMA")));
			formalParameterSequenceNode.addChild(parseFormalParameterNameSequence());
			formalParameterSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SCOLON")));
			formalParameterSequenceNode.addChild(parseStandardType());
		}
		return formalParameterSequenceNode;
	}

	// 仮パラメータ名の並び
	private AstNode parseFormalParameterNameSequence() throws SyntaxException {
		final NonTerminalNode formalParameterNameSequenceNode = new NonTerminalNode(NonTerminalType.FORMALPARAMETERNAMESEQUENCE);	
		formalParameterNameSequenceNode.addChild(parseFormalParameterName());
		while (equalsAny(0, "SCOMMA")) {
			formalParameterNameSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SCOMMA")));
			formalParameterNameSequenceNode.addChild(parseFormalParameterName());
		} 
		return formalParameterNameSequenceNode;
	}

	// 仮パラメータ名
	private AstNode parseFormalParameterName() throws SyntaxException {
		return new TerminalNode(checkTerminalSymbol("SIDENTIFIER"));
	}

	// 複合文
	private AstNode parseCompoundStatement() throws SyntaxException {
		final NonTerminalNode compoundStatementNode = new NonTerminalNode(NonTerminalType.COMPOUNDSTATEMENT); 	
		compoundStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SBEGIN")));
		compoundStatementNode.addChild(parseStatementSequence());
		compoundStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SEND"))); 
		return compoundStatementNode;
	}

	// 文の並び
	private AstNode parseStatementSequence() throws SyntaxException {
		final NonTerminalNode statementSequenceNode = new NonTerminalNode(NonTerminalType.STATEMENTSEQUENCE); 
		do {
			statementSequenceNode.addChild(parseStatement());
			statementSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SSEMICOLON")));
		} while (equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN", "SIF", "SWHILE"));
		return statementSequenceNode;
	}

	// 文
	private AstNode parseStatement() throws SyntaxException {
		final NonTerminalNode statementNode = new NonTerminalNode(NonTerminalType.STATEMENT);
		if (equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN")) {
			statementNode.addChild(parseBasicStatement());
		} else if (equalsAny(0, "SIF")) {
			statementNode.addChild(new TerminalNode(checkTerminalSymbol("SIF")));
			statementNode.addChild(parseExpression());
			statementNode.addChild(new TerminalNode(checkTerminalSymbol("STHEN")));
			statementNode.addChild(parseCompoundStatement());
			if (equalsAny(0, "SELSE")) {
				statementNode.addChild(new TerminalNode(checkTerminalSymbol("SELSE")));
				statementNode.addChild(parseCompoundStatement());
			}
		} else if (equalsAny(0, "SWHILE")) {
			statementNode.addChild(new TerminalNode(checkTerminalSymbol("SWHILE")));
			statementNode.addChild(parseExpression());
			statementNode.addChild(new TerminalNode(checkTerminalSymbol("SDO")));
			statementNode.addChild(parseCompoundStatement());
		} else {
			throw new SyntaxException(this.tokenList.get(this.index));
		}
		return statementNode;
	}

	// 基本文
	private AstNode parseBasicStatement() throws SyntaxException { 	
		final NonTerminalNode basicStatementNode = new NonTerminalNode(NonTerminalType.BASICSTATEMENT); 
		if (equalsAny(0, "SIDENTIFIER")) {
			if (equalsAny(1, "SASSIGN", "SLBRACKET")) {
				basicStatementNode.addChild(parseAssignmentStatement());
			} else {
				basicStatementNode.addChild(parseProcedureCallStatement());
			}
		} else if (equalsAny(0, "SREADLN", "SWRITELN")) {
			basicStatementNode.addChild(parseInputOutputStatement());
		} else if (equalsAny(0, "SBEGIN")) {
			basicStatementNode.addChild(parseCompoundStatement());
		} else {
			throw new SyntaxException(this.tokenList.get(this.index));
		}
		return basicStatementNode;
	}

	// 代入文
	private AstNode parseAssignmentStatement() throws SyntaxException {
		final NonTerminalNode assignmentStatementNode = new NonTerminalNode(NonTerminalType.ASSIGNMENTSTATEMENT);
		assignmentStatementNode.addChild(parseLeftHandSide());
		assignmentStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SASSIGN")));
		assignmentStatementNode.addChild(parseExpression());
		return assignmentStatementNode;
	}

	// 左辺
	private AstNode parseLeftHandSide() throws SyntaxException { 	
		final NonTerminalNode leftHandSideNode = new NonTerminalNode(NonTerminalType.LEFTHANDSIDE); 
		leftHandSideNode.addChild(parseVariable());
		return leftHandSideNode;
	}

	// 変数
	private AstNode parseVariable() throws SyntaxException {
		final NonTerminalNode variableNode = new NonTerminalNode(NonTerminalType.VARIABLE); 	
		if (equalsAny(0, "SIDENTIFIER")) {
			if (equalsAny(1, "SLBRACKET")) {
				variableNode.addChild(parseIndexedVariable());
			} else {
				variableNode.addChild(parsePureVariable());
			}
		} else {
			throw new SyntaxException(this.tokenList.get(this.index));
		}
		return variableNode;
	}

	// 純変数
	private AstNode parsePureVariable() throws SyntaxException { 
		final NonTerminalNode pureVariableNode = new NonTerminalNode(NonTerminalType.PUREVARIABLE);	
		pureVariableNode.addChild(parseVariableName());
		return pureVariableNode; 
	}

	// 添え字付き変数
	private AstNode parseIndexedVariable() throws SyntaxException {
		final NonTerminalNode indexedVariableNode = new NonTerminalNode(NonTerminalType.INDEXEDVARIABLE); 
		indexedVariableNode.addChild(parseVariableName());
		indexedVariableNode.addChild(new TerminalNode(checkTerminalSymbol("SLBRACKET")));
		indexedVariableNode.addChild(parseIndex());
		indexedVariableNode.addChild(new TerminalNode(checkTerminalSymbol("SRBRACKET")));	
		return indexedVariableNode; 
	}

	// 添え字
	private AstNode parseIndex() throws SyntaxException {
		final NonTerminalNode indexNode = new NonTerminalNode(NonTerminalType.INDEX); 
		indexNode.addChild(parseExpression());	 
		return indexNode;
	}

	// 手続き呼出し文
	private AstNode parseProcedureCallStatement() throws SyntaxException {
		final NonTerminalNode procedureCallStatementNode = new NonTerminalNode(NonTerminalType.PROCEDURECALLSTATEMENT); 
		procedureCallStatementNode.addChild(parseProcedureName()); 
		if (equalsAny(0, "SLPAREN")) {
			procedureCallStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SLPAREN")));
			procedureCallStatementNode.addChild(parseExpressionSequence());
			procedureCallStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SRPAREN")));
		}
		return procedureCallStatementNode;
	}

	// 式の並び
	private AstNode parseExpressionSequence() throws SyntaxException {
		final NonTerminalNode expressionSequenceNode = new NonTerminalNode(NonTerminalType.EXPRESSIONSEQUENCE);
		expressionSequenceNode.addChild(parseExpression());
		while (equalsAny(0, "SCOMMA")) {
			expressionSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SCOMMA")));
			expressionSequenceNode.addChild(parseExpression());
		}
		return expressionSequenceNode;
	}

	// 式
	private AstNode parseExpression() throws SyntaxException { 
		final NonTerminalNode expressionNode = new NonTerminalNode(NonTerminalType.EXPRESSION);
		expressionNode.addChild(parseSimpleExpression());
		if (equalsAny(0, "SEQUAL", "SNOTEQUAL", "SLESS", "SLESSEQUAL", "SGREAT", "SGREATEQUAL")) {
			expressionNode.addChild(parseRelationalOperator());
			expressionNode.addChild(parseSimpleExpression());
		}
		return expressionNode;
	}

	// 単純式
	private AstNode parseSimpleExpression() throws SyntaxException {
		final NonTerminalNode simpleExpressionNode = new NonTerminalNode(NonTerminalType.SIMPLEEXPRESSION);
		if (equalsAny(0, "SPLUS", "SMINUS")) {
			simpleExpressionNode.addChild(parseSign());
		}
		simpleExpressionNode.addChild(parseTerm());
		while (equalsAny(0, "SPLUS", "SMINUS", "SOR")) {
			simpleExpressionNode.addChild(parseAdditiveOperator());
			simpleExpressionNode.addChild(parseTerm());
		}
		return simpleExpressionNode;
	}

	// 項
	private AstNode parseTerm() throws SyntaxException { 
		final NonTerminalNode termNode = new NonTerminalNode(NonTerminalType.TERM);
		termNode.addChild(parseFactor());
		while (equalsAny(0, "SSTAR", "SDIVD", "SMOD", "SAND")) {
			termNode.addChild(parseMultiplicativeOperator());
			termNode.addChild(parseFactor());
		}
		return termNode;
	}

	// 因子
	private AstNode parseFactor() throws SyntaxException {
		final NonTerminalNode factorNode = new NonTerminalNode(NonTerminalType.FACTOR); 	 
		if (equalsAny(0, "SIDENTIFIER")) {
			factorNode.addChild(parseVariable());
		} else if (equalsAny(0, "SCONSTANT", "SSTRING", "STRUE", "SFALSE")) {
			factorNode.addChild(parseConstant());
		} else if (equalsAny(0, "SLPAREN")) {
			factorNode.addChild(new TerminalNode(checkTerminalSymbol("SLPAREN")));
			factorNode.addChild(parseExpression());
			factorNode.addChild(new TerminalNode(checkTerminalSymbol("SRPAREN")));
		} else if (equalsAny(0, "SNOT")) {
			factorNode.addChild(new TerminalNode(checkTerminalSymbol("SNOT")));
			factorNode.addChild(parseFactor());
		} else {
			throw new SyntaxException(this.tokenList.get(this.index));
		}
		return factorNode;
	}

	// 関係演算子
	private AstNode parseRelationalOperator() throws SyntaxException {
		return new TerminalNode(checkTerminalSymbol("SEQUAL", "SNOTEQUAL", "SLESS", "SLESSEQUAL", "SGREAT", "SGREATEQUAL"));
	}

	// 加法演算子
	private AstNode parseAdditiveOperator() throws SyntaxException {
		return new TerminalNode(checkTerminalSymbol("SPLUS", "SMINUS", "SOR"));
	}

	// 乗法演算子
	private AstNode parseMultiplicativeOperator() throws SyntaxException {
		return new TerminalNode(checkTerminalSymbol("SSTAR", "SDIVD", "SMOD", "SAND"));
	}

	// 入出力文
	private AstNode parseInputOutputStatement() throws SyntaxException {
		final NonTerminalNode inputOutputStatementNode = new NonTerminalNode(NonTerminalType.INPUTOUTPUTSTATEMENT);	 
		if (equalsAny(0, "SREADLN")) {
			inputOutputStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SREADLN")));
			if (equalsAny(0, "SLPAREN")) {
				inputOutputStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SLPAREN")));
				inputOutputStatementNode.addChild(parseVariableSequence());
				inputOutputStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SRPAREN")));
			}
		} else if (equalsAny(0, "SWRITELN")) {
			inputOutputStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SWRITELN")));
			if (equalsAny(0, "SLPAREN")) {
				inputOutputStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SLPAREN")));
				inputOutputStatementNode.addChild(parseExpressionSequence());
				inputOutputStatementNode.addChild(new TerminalNode(checkTerminalSymbol("SRPAREN")));
			}
		} else {
			throw new SyntaxException(this.tokenList.get(this.index));
		}
		return inputOutputStatementNode;
	}

	// 変数の並び
	private AstNode parseVariableSequence() throws SyntaxException {
		final NonTerminalNode variableSequenceNode = new NonTerminalNode(NonTerminalType.VARIABLESEQUENCE);
		variableSequenceNode.addChild(parseVariable());
		while (equalsAny(0, "SCOMMA")) {
			variableSequenceNode.addChild(new TerminalNode(checkTerminalSymbol("SCOMMA")));
			variableSequenceNode.addChild(parseVariable());
		}	 
		return variableSequenceNode;
	}

	// 定数
	private AstNode parseConstant() throws SyntaxException { 
		return new TerminalNode(checkTerminalSymbol("SCONSTANT", "SSTRING", "STRUE", "SFALSE"));
	}

}
