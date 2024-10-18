package enshud.s1.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	private List<String> mapResult;
	private HashMap<String, ArrayList<String>> map;
	private char c;
	
	Mealy() {
		this.state = State.START;
		this.lineCount = 1;
		this.tokenBuilder = new StringBuilder();
		this.results = new ArrayList<>();
        this.map = new HashMap<String, ArrayList<String>>();
        this.map.put("and", new ArrayList<String>(Arrays.asList("SAND", "0")));
        this.map.put("array", new ArrayList<String>(Arrays.asList("SARRAY", "1")));
        this.map.put("begin", new ArrayList<String>(Arrays.asList("SBEGIN", "2")));
        this.map.put("boolean", new ArrayList<String>(Arrays.asList("SBOOLEAN", "3")));
        this.map.put("char", new ArrayList<String>(Arrays.asList("SCHAR", "4")));
        this.map.put("div", new ArrayList<String>(Arrays.asList("SDIVD", "5")));
        this.map.put("/", new ArrayList<String>(Arrays.asList("SDIVD", "5")));
        this.map.put("do", new ArrayList<String>(Arrays.asList("SDO", "6")));
        this.map.put("else", new ArrayList<String>(Arrays.asList("SELSE", "7")));
        this.map.put("end", new ArrayList<String>(Arrays.asList("SEND", "8")));
        this.map.put("false", new ArrayList<String>(Arrays.asList("SFALSE", "9")));
        this.map.put("if", new ArrayList<String>(Arrays.asList("SIF", "10")));
        this.map.put("integer", new ArrayList<String>(Arrays.asList("SINTEGER", "11")));
        this.map.put("mod", new ArrayList<String>(Arrays.asList("SMOD", "12")));
        this.map.put("not", new ArrayList<String>(Arrays.asList("SNOT", "13")));
        this.map.put("of", new ArrayList<String>(Arrays.asList("SOF", "14")));
        this.map.put("or", new ArrayList<String>(Arrays.asList("SOR", "15")));
        this.map.put("procedure", new ArrayList<String>(Arrays.asList("SPROCEDURE", "16")));
        this.map.put("program", new ArrayList<String>(Arrays.asList("SPROGRAM", "17")));
        this.map.put("readln", new ArrayList<String>(Arrays.asList("READLN", "18")));
        this.map.put("then", new ArrayList<String>(Arrays.asList("STHEN", "19")));
        this.map.put("true", new ArrayList<String>(Arrays.asList("STRUE", "20")));
        this.map.put("var", new ArrayList<String>(Arrays.asList("SVAR", "21")));
        this.map.put("while", new ArrayList<String>(Arrays.asList("SWHILE", "22")));
        this.map.put("writeln", new ArrayList<String>(Arrays.asList("SWRITELN", "23")));
        this.map.put("=", new ArrayList<String>(Arrays.asList("SEQUAL", "24")));
        this.map.put("<>", new ArrayList<String>(Arrays.asList("SNOTEQUAL", "25")));
        this.map.put("<", new ArrayList<String>(Arrays.asList("SLESS", "26")));
        this.map.put("<=", new ArrayList<String>(Arrays.asList("SLESSEQUAL", "27")));
        this.map.put(">=", new ArrayList<String>(Arrays.asList("SGREATEQUAL", "28")));
        this.map.put(">", new ArrayList<String>(Arrays.asList("SGREAT", "29")));
        this.map.put("+", new ArrayList<String>(Arrays.asList("SPLUS", "30")));
        this.map.put("-", new ArrayList<String>(Arrays.asList("SMINUS", "31")));
        this.map.put("*", new ArrayList<String>(Arrays.asList("SSTAR", "32")));
        this.map.put("(", new ArrayList<String>(Arrays.asList("SLPAREN", "33")));
        this.map.put(")", new ArrayList<String>(Arrays.asList("SRPAREN", "34")));
        this.map.put("[", new ArrayList<String>(Arrays.asList("SLBRACKET", "35")));
        this.map.put("]", new ArrayList<String>(Arrays.asList("SRBRACKET", "36")));
        this.map.put(";", new ArrayList<String>(Arrays.asList("SSEMICOLON", "37")));
        this.map.put(":", new ArrayList<String>(Arrays.asList("SCOLON", "38")));
        this.map.put("..", new ArrayList<String>(Arrays.asList("SRANGE", "39")));
        this.map.put(":=", new ArrayList<String>(Arrays.asList("SASSIGN", "40")));
        this.map.put(",", new ArrayList<String>(Arrays.asList("SCOMMA", "41")));
        this.map.put(".", new ArrayList<String>(Arrays.asList("SDOT", "42")));
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
	    				if ((mapResult = this.map.get(this.tokenBuilder.toString())) == null) {
	    					this.results.add(this.tokenBuilder.toString() + "\tSIDENTIFIER\t43\t" + Integer.toString(this.lineCount));
	    				} else {
	    					this.results.add(this.tokenBuilder.toString() + "\t" + mapResult.get(0) +"\t"+ mapResult.get(1) + "\t" + Integer.toString(this.lineCount));
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
	    			if ((mapResult = this.map.get(this.tokenBuilder.toString())) == null) {
	    				return false;
	    			}
    				this.results.add(this.tokenBuilder.toString() + "\t" + mapResult.get(0) +"\t"+ mapResult.get(1) + "\t" + Integer.toString(this.lineCount));
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
