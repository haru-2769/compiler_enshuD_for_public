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
	
	Mealy() {
		this.state = State.START;
		this.lineCount = 1;
		this.tokenBuilder = new StringBuilder();
		this.results = new ArrayList<>();
	}
	
    public boolean transitionState(String s) {
    	TokenMap tokenMap = new TokenMap();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
	    	switch (this.state) {
	    		case State.START:
	    			if (Character.isAlphabetic(c)) {
	    				tokenBuilder.append(c);
	    				state = State.IDENTIFIER;
	    			} else if (Character.isDigit(c)) {
	    				tokenBuilder.append(c);
	    				state = State.CONSTANT;
	    			} else if (c == '{') {
	    				state = State.ANNOTATION;
	    			} else if (c == '\'') {
	    				tokenBuilder.append(c);
	    				state = State.STRING;
	    			} else if (String.valueOf(c).matches("[=<>\\+\\-\\*()\\[\\];:.,/]")) {
	    				tokenBuilder.append(c);
	    				i--;
	    				state = State.SIGN;
	    			} else if (c == '\s' || c == '\t' ||  c == '\n') {
	    				state = State.START;
	    			} else {
	    				return false;
	    			}
	    			break;
	    		case State.IDENTIFIER:
	    			if (Character.isAlphabetic(c) || Character.isDigit(c)) {
	    				tokenBuilder.append(c);
	    				state = State.IDENTIFIER;
	    			} else {
	    				if (tokenMap.getToken(tokenBuilder.toString()) == null) {
	    					results.add(tokenBuilder.toString() + "\tSIDENTIFIER\t43\t" + Integer.toString(lineCount));
	    				} else {
	    					results.add(tokenBuilder.toString() + "\t" + tokenMap.getToken(tokenBuilder.toString()).get(0) +"\t"+ tokenMap.getToken(tokenBuilder.toString()).get(1) + "\t" + Integer.toString(lineCount));
	    				}
                        tokenBuilder.setLength(0);
                        if (c == '{') {
    	    				state = State.ANNOTATION;
    	    			} else if (c == '\'') {
    	    				tokenBuilder.append(c);
    	    				state = State.STRING;
    	    			} else if (String.valueOf(c).matches("[=<>\\+\\-\\*()\\[\\];:.,/]")) {
    	    				tokenBuilder.append(c);
    	    				i--;
    	    				state = State.SIGN;
    	    			} else if (c == '\s' || c == '\t' ||  c == '\n') {
    	    				state = State.START;
    	    			} else {
    	    				return false;
    	    			}
	    			}
	    			break;
	    		case State.CONSTANT:
	    			if (Character.isDigit(c)) {
	    				tokenBuilder.append(c);
	    				state = State.CONSTANT;
	    			} else {
	    				results.add(tokenBuilder.toString() + "\tSCONSTANT\t44\t" + Integer.toString(lineCount));
	    				tokenBuilder.setLength(0);
	    				if (Character.isAlphabetic(c)) {
                        	return false;
                        } else if (c == '{') {
    	    				state = State.ANNOTATION;
    	    			} else if (c == '\'') {
    	    				tokenBuilder.append(c);
    	    				state = State.STRING;
    	    			} else if (String.valueOf(c).matches("[=<>\\+\\-\\*()\\[\\];:.,/]")) {
    	    				tokenBuilder.append(c);
    	    				i--;
    	    				state = State.SIGN;
    	    			} else if (c == '\s' || c == '\t' ||  c == '\n') {
    	    				state = State.START;
    	    			} else {
    	    				return false;
    	    			}
	    			}
	    			break;
	    		case State.ANNOTATION:
	    			if (c == '}') {
	    				state = State.START;
	    			} else {
	    				state = State.ANNOTATION;
	    			}
	    			break;
	    		case State.STRING:

    				System.out.println(c);
	    			if (c == '\'') {
	    				tokenBuilder.append(c);
	    				results.add(tokenBuilder.toString() + "\tSSTRING\t45\t" + Integer.toString(lineCount));
	    				tokenBuilder.setLength(0);
	    				state = State.START;
	    			} else if (c == '\n') {
	    				return false;
	    			}else {
	    				tokenBuilder.append(c);
	    				state = State.STRING;
	    			}
	    			break;
	    		case State.SIGN:
	    			if (i < s.length() - 1) {
		    			if (c == '<') {
		    				if (s.charAt(i + 1) == '>' || s.charAt(i + 1) == '=') {
		    					tokenBuilder.append(s.charAt(i + 1));
		    					i++;
		    				}
		    			} else if (c == '>') {
		    				if (s.charAt(i + 1) == '=') {
		    					tokenBuilder.append(s.charAt(i + 1));
		    					i++;
		    				}
		    			} else if (c == '.') {
		    				if (s.charAt(i + 1) == '.') {
		    					tokenBuilder.append(s.charAt(i + 1));
		    					i++;
		    				}
		    			} else if (c == ':') {
		    				if (s.charAt(i + 1) == '=') {
		    					tokenBuilder.append(s.charAt(i + 1));
		    					i++;
		    				}
		    			}
	    			}
    				results.add(tokenBuilder.toString() + "\t" + tokenMap.getToken(tokenBuilder.toString()).get(0) +"\t"+ tokenMap.getToken(tokenBuilder.toString()).get(1) + "\t" + Integer.toString(lineCount));
    				tokenBuilder.setLength(0);
    				state = State.START;
	    			break;
	    		default:
	    			break;
	    	 }
        }
        this.lineCount++;
        return true;
    }
    
    public List<String> getResult() {
    	return results;
    }
}
