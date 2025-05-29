package enshud.s2.parser;

import java.util.List;

public class Context {
	private List<Token> tokenList;
	private int index;
	
	public Context(List<Token> tokenList) {
		this.tokenList = tokenList;
		this.index = 0;
	}

	public int getIndex() {
		return this.index;
	}

	public List<Token> getTokenList() {
		return this.tokenList;
	}
	
	public Token checkTerminalSymbol(String... terminalSymbols) throws SyntaxException {
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

	public boolean equalsAny(int offset, String... terminalSymbols) throws SyntaxException {
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

	public String getLineCount() {
		return this.tokenList.get(this.index).getLineCount();
	}
}
