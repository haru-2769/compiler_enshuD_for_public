package enshud.s1.lexer;

import java.util.ArrayList;
import java.util.List;

public class Mealy {
	private enum State {
		START,
		IDENTIFIER,
		CONSTANT,
		ANNOTATION,
		STRING,
		SIGN;
	}
	
	private State state;
	private int lineCount;
	private StringBuilder tokenBuilder;
	private List<String> results;
	private TokenMap tokenMap;
	private char c;
	
	Mealy() {
		this.state = State.START;
		this.lineCount = 1;
		this.tokenBuilder = new StringBuilder();
		this.results = new ArrayList<>();
		this.tokenMap = new TokenMap();
	}
	
    public boolean transitionState(String s) {
        for (int i = 0; i < s.length(); i++) {
            this.c = s.charAt(i);
	    	switch (this.state) {
	    		case State.START:
	    			if (Character.isAlphabetic(this.c)) {
	    				this.tokenBuilder.append(this.c);
	    				this.state = State.IDENTIFIER;
	    			} else if (Character.isDigit(c)) {
	    				this.tokenBuilder.append(this.c);
	    				this.state = State.CONSTANT;
	    			} else if (this.c == '{') {
	    				this.state = State.ANNOTATION;
	    			} else if (this.c == '\'') {
	    				this.tokenBuilder.append(this.c);
	    				this.state = State.STRING;
	    			} else if (String.valueOf(this.c).matches("[=<>\\+\\-\\*()\\[\\];:.,/]")) {
	    				this.tokenBuilder.append(this.c);
	    				i--;
	    				this.state = State.SIGN;
	    			} else if (this.c == '\s' || this.c == '\t' ||  this.c == '\n') {
	    				this.state = State.START;
	    			} else {
	    				return false;
	    			}
	    			break;
	    		case State.IDENTIFIER:
	    			if (Character.isAlphabetic(this.c) || Character.isDigit(this.c)) {
	    				this.tokenBuilder.append(this.c);
	    				this.state = State.IDENTIFIER;
	    			} else {
	    				if (this.tokenMap.getToken(this.tokenBuilder.toString()) == null) {
	    					this.results.add(this.tokenBuilder.toString() + "\tSIDENTIFIER\t43\t" + Integer.toString(this.lineCount));
	    				} else {
	    					this.results.add(this.tokenBuilder.toString() + "\t" + this.tokenMap.getToken(this.tokenBuilder.toString()).get(0) +"\t"+ this.tokenMap.getToken(this.tokenBuilder.toString()).get(1) + "\t" + Integer.toString(this.lineCount));
	    				}
                        this.tokenBuilder.setLength(0);
                        if (this.c == '{') {
                        	this.state = State.ANNOTATION;
    	    			} else if (this.c == '\'') {
    	    				this.tokenBuilder.append(this.c);
    	    				this.state = State.STRING;
    	    			} else if (String.valueOf(this.c).matches("[=<>\\+\\-\\*()\\[\\];:.,/]")) {
    	    				this.tokenBuilder.append(this.c);
    	    				i--;
    	    				this.state = State.SIGN;
    	    			} else if (this.c == '\s' || this.c == '\t' ||  this.c == '\n') {
    	    				this.state = State.START;
    	    			} else {
    	    				return false;
    	    			}
	    			}
	    			break;
	    		case State.CONSTANT:
	    			if (Character.isDigit(this.c)) {
	    				this.tokenBuilder.append(this.c);
	    				this.state = State.CONSTANT;
	    			} else {
	    				this.results.add(this.tokenBuilder.toString() + "\tSCONSTANT\t44\t" + Integer.toString(this.lineCount));
	    				this.tokenBuilder.setLength(0);
	    				if (Character.isAlphabetic(this.c)) {
                        	return false;
                        } else if (this.c == '{') {
                        	this.state = State.ANNOTATION;
    	    			} else if (this.c == '\'') {
    	    				this.tokenBuilder.append(this.c);
    	    				this.state = State.STRING;
    	    			} else if (String.valueOf(this.c).matches("[=<>\\+\\-\\*()\\[\\];:.,/]")) {
    	    				this.tokenBuilder.append(this.c);
    	    				i--;
    	    				this.state = State.SIGN;
    	    			} else if (this.c == '\s' || this.c == '\t' ||  this.c == '\n') {
    	    				this.state = State.START;
    	    			} else {
    	    				return false;
    	    			}
	    			}
	    			break;
	    		case State.ANNOTATION:
	    			if (this.c == '}') {
	    				this.state = State.START;
	    			} else {
	    				this.state = State.ANNOTATION;
	    			}
	    			break;
	    		case State.STRING:
	    			if (this.c == '\'') {
	    				this.tokenBuilder.append(this.c);
	    				this.results.add(this.tokenBuilder.toString() + "\tSSTRING\t45\t" + Integer.toString(this.lineCount));
	    				this.tokenBuilder.setLength(0);
	    				this.state = State.START;
	    			} else if (this.c == '\n') {
	    				return false;
	    			}else {
	    				this.tokenBuilder.append(this.c);
	    				this.state = State.STRING;
	    			}
	    			break;
	    		case State.SIGN:
	    			if (i < s.length() - 1) {
		    			if (this.c == '<') {
		    				if (s.charAt(i + 1) == '>' || s.charAt(i + 1) == '=') {
		    					this.tokenBuilder.append(s.charAt(i + 1));
		    					i++;
		    				}
		    			} else if (this.c == '>') {
		    				if (s.charAt(i + 1) == '=') {
		    					this.tokenBuilder.append(s.charAt(i + 1));
		    					i++;
		    				}
		    			} else if (this.c == '.') {
		    				if (s.charAt(i + 1) == '.') {
		    					this.tokenBuilder.append(s.charAt(i + 1));
		    					i++;
		    				}
		    			} else if (this.c == ':') {
		    				if (s.charAt(i + 1) == '=') {
		    					this.tokenBuilder.append(s.charAt(i + 1));
		    					i++;
		    				}
		    			}
	    			}
    				this.results.add(this.tokenBuilder.toString() + "\t" + this.tokenMap.getToken(this.tokenBuilder.toString()).get(0) +"\t"+ this.tokenMap.getToken(this.tokenBuilder.toString()).get(1) + "\t" + Integer.toString(this.lineCount));
    				this.tokenBuilder.setLength(0);
    				this.state = State.START;
	    			break;
	    		default:
	    			break;
	    	 }
        }
        this.lineCount++;
        return true;
    }
    
    public List<String> getResult() {
    	return this.results;
    }
}
