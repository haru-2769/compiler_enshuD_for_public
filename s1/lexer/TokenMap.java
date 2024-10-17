package enshud.s1.lexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TokenMap {
    private HashMap<String, ArrayList<String>> map;

    public TokenMap() {
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
    
    public ArrayList<String> getToken(String s) {
        return this.map.get(s);
    }
}
