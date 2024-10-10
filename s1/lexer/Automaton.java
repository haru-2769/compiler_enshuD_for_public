package enshud.s1.lexer;

import java.util.ArrayList;
import java.util.List;

public class Automaton {
	private List<String> results = new ArrayList<>();
	
    public List<String> getResult(String s, int currentState) {
    	results.clear();
    	Tokenmap tokenMap = new Tokenmap(); 
    	StringBuilder tokenBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
	    	switch (currentState) {
	    		case 0:
	    			if (Character.isAlphabetic(c)) {
	    				tokenBuilder.append(c);
	    				currentState = 1;
	    			} else if (Character.isDigit(c)) {
	    				tokenBuilder.append(c);
	    				currentState = 2;
	    			} else if (c == '{') {
	    				currentState = 3;
	    			} else if (c == '\'') {
	    				tokenBuilder.append(c);
	    				currentState = 4;
	    			} else if (String.valueOf(c).matches("[=<>\\+\\-\\*()\\[\\];:.,/]")) {
	    				tokenBuilder.append(c);
	    				i--;
	    				currentState = 5;
	    			}
	    			break;
	    		case 1:
	    			if (Character.isAlphabetic(c) || Character.isDigit(c)) {
	    				tokenBuilder.append(c);
	    				currentState = 1;
	    			} else {
	    				if (tokenMap.getToken(tokenBuilder.toString()) == null) {
	    					results.add(tokenBuilder.toString() + "\tSIDENTIFIER\t43\t");
	    				} else {
	    					results.add(tokenBuilder.toString() + tokenMap.getToken(tokenBuilder.toString()));
	    				}
                        tokenBuilder.setLength(0);
                        if (c == '{') {
    	    				currentState = 3;
    	    			} else if (c == '\'') {
    	    				tokenBuilder.append(c);
    	    				currentState = 4;
    	    			} else if (String.valueOf(c).matches("[=<>\\+\\-\\*()\\[\\];:.,/]")) {
    	    				tokenBuilder.append(c);
    	    				i--;
    	    				currentState = 5;
    	    			} else {
    	    				currentState = 0;
    	    			}
	    			}
	    			break;
	    		case 2:
	    			if (Character.isDigit(c)) {
	    				tokenBuilder.append(c);
	    				currentState = 2;
	    			} else {
	    				results.add(tokenBuilder.toString() + "\tSCONSTANT\t44\t");
	    				tokenBuilder.setLength(0);
                        if (c == '{') {
    	    				currentState = 3;
    	    			} else if (c == '\'') {
    	    				tokenBuilder.append(c);
    	    				currentState = 4;
    	    			} else if (String.valueOf(c).matches("[=<>\\+\\-\\*()\\[\\];:.,/]")) {
    	    				tokenBuilder.append(c);
    	    				i--;
    	    				currentState = 5;
    	    			} else {
    	    				currentState = 0;
    	    			}
	    			}
	    			break;
	    		case 3:
	    			if (c == '}') {
	    				currentState = 0;
	    			} else {
	    				currentState = 3;
	    			}
	    			break;
	    		case 4:
	    			if (c == '\'') {
	    				tokenBuilder.append(c);
	    				results.add(tokenBuilder.toString() + "\tSSTRING\t45\t");
	    				tokenBuilder.setLength(0);
	    				currentState = 0;
	    			} else {
	    				tokenBuilder.append(c);
	    				currentState = 4;
	    			}
	    			break;
	    		case 5:
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
	    			results.add(tokenBuilder.toString() + tokenMap.getToken(tokenBuilder.toString()));
	    			tokenBuilder.setLength(0);
    				currentState = 0;
	    			break;
	    		default:
	    			break;
	    	}
	    	if (c == '\\') {
	    		System.out.println(currentState);
	    	}
        }
        return results;
    }
}
