package enshud.s1.lexer;

import java.util.HashMap;

public class Tokenmap {
    private HashMap<String, String> map;

    public Tokenmap() {
        this.map = new HashMap<>();
        this.map.put("and", "\tSAND\t0\t");
        this.map.put("array", "\tSARRAY\t1\t");
        this.map.put("begin", "\tSBEGIN\t2\t");
        this.map.put("boolean", "\tSBOOLEAN\t3\t");
        this.map.put("char", "\tSCHAR\t4\t");
        this.map.put("div", "\tSDIVD\t5\t");
        this.map.put("/", "\tSDIVD\t5\t");
        this.map.put("do", "\tSDO\t6\t");
        this.map.put("else", "\tSELSE\t7\t");
        this.map.put("end", "\tSEND\t8\t");
        this.map.put("false", "\tSFALSE\t9\t");
        this.map.put("if", "\tSIF\t10\t");
        this.map.put("integer", "\tSINTEGER\t11\t");
        this.map.put("mod", "\tSMOD\t12\t");
        this.map.put("not", "\tSNOT\t13\t");
        this.map.put("of", "\tSOF\t14\t");
        this.map.put("or", "\tSOR\t15\t");
        this.map.put("procedure", "\tSPROCEDURE\t16\t");
        this.map.put("program", "\tSPROGRAM\t17\t");
        this.map.put("readln", "\tREADLN\t18\t");
        this.map.put("then", "\tSTHEN\t19\t");
        this.map.put("true", "\tSTRUE\t20\t");
        this.map.put("var", "\tSVAR\t21\t");
        this.map.put("while", "\tSWHILE\t22\t");
        this.map.put("writeln", "\tSWRITELN\t23\t");
        this.map.put("=", "\tSEQUAL\t24\t");
        this.map.put("<>", "\tSNOTEQUAL\t25\t");
        this.map.put("<", "\tSLESS\t26\t");
        this.map.put("<=", "\tSLESSEQUAL\t27\t");
        this.map.put(">=", "\tSGREATEQUAL\t28\t");
        this.map.put(">", "\tSGREAT\t29\t");
        this.map.put("+", "\tSPLUS\t30\t");
        this.map.put("-", "\tSMINUS\t31\t");
        this.map.put("*", "\tSSTAR\t32\t");
        this.map.put("(", "\tSLPAREN\t33\t");
        this.map.put(")", "\tSRPAREN\t34\t");
        this.map.put("[", "\tSLBRACKET\t35\t");
        this.map.put("]", "\tSRBRACKET\t36\t");
        this.map.put(";", "\tSSEMICOLON\t37\t");
        this.map.put(":", "\tSCOLON\t38\t");
        this.map.put("..", "\tSRANGE\t39\t");
        this.map.put(":=", "\tSASSIGN\t40\t");
        this.map.put(",", "\tSCOMMA\t41\t");
        this.map.put(".", "\tSDOT\t42\t");
//        this.map.put("and", new ArrayList<>(Arrays.asList("SAND", "0")));
//        this.map.put("array", new ArrayList<>(Arrays.asList("SARRAY", "1")));
//        this.map.put("begin", new ArrayList<>(Arrays.asList("SBEGIN", "2")));
//        this.map.put("boolean", new ArrayList<>(Arrays.asList("SBOOLEAN", "3")));
//        this.map.put("char", new ArrayList<>(Arrays.asList("SCHAR", "4")));
//        this.map.put("div", new ArrayList<>(Arrays.asList("SDIVD", "5")));
//        this.map.put("/", new ArrayList<>(Arrays.asList("SDIVD", "5")));
//        this.map.put("do", new ArrayList<>(Arrays.asList("SDO", "6")));
//        this.map.put("else", new ArrayList<>(Arrays.asList("SELSE", "7")));
//        this.map.put("end", new ArrayList<>(Arrays.asList("SEND", "8")));
//        this.map.put("false", new ArrayList<>(Arrays.asList("SFALSE", "9")));
//        this.map.put("if", new ArrayList<>(Arrays.asList("SIF", "10")));
//        this.map.put("integer", new ArrayList<>(Arrays.asList("SINTEGER", "11")));
//        this.map.put("mod", new ArrayList<>(Arrays.asList("SMOD", "12")));
//        this.map.put("not", new ArrayList<>(Arrays.asList("SNOT", "13")));
//        this.map.put("of", new ArrayList<>(Arrays.asList("SOF", "14")));
//        this.map.put("or", new ArrayList<>(Arrays.asList("SOR", "15")));
//        this.map.put("procedure", new ArrayList<>(Arrays.asList("SPROCEDURE", "16")));
//        this.map.put("program", new ArrayList<>(Arrays.asList("SPROGRAM", "17")));
//        this.map.put("readln", new ArrayList<>(Arrays.asList("READLN", "18")));
//        this.map.put("then", new ArrayList<>(Arrays.asList("STHEN", "19")));
//        this.map.put("true", new ArrayList<>(Arrays.asList("STRUE", "20")));
//        this.map.put("var", new ArrayList<>(Arrays.asList("SVAR", "21")));
//        this.map.put("while", new ArrayList<>(Arrays.asList("SWHILE", "22")));
//        this.map.put("writeln", new ArrayList<>(Arrays.asList("SWRITELN", "23")));
//        this.map.put("=", new ArrayList<>(Arrays.asList("SEQUAL", "24")));
//        this.map.put("<>", new ArrayList<>(Arrays.asList("SNOTEQUAL", "25")));
//        this.map.put("<", new ArrayList<>(Arrays.asList("SLESS", 26)));
//        this.map.put("<=", new ArrayList<>(Arrays.asList("SLESSEQUAL", "27")));
//        this.map.put(">=", new ArrayList<>(Arrays.asList("SGREATEQUAL", "28")));
//        this.map.put(">", new ArrayList<>(Arrays.asList("SGREAT", "29")));
//        this.map.put("+", new ArrayList<>(Arrays.asList("SPLUS", "30")));
//        this.map.put("-", new ArrayList<>(Arrays.asList("SMINUS", "31")));
//        this.map.put("*", new ArrayList<>(Arrays.asList("SSTAR", "32")));
//        this.map.put("(", new ArrayList<>(Arrays.asList("SLPAREN", "33")));
//        this.map.put(")", new ArrayList<>(Arrays.asList("SRPAREN", "34")));
//        this.map.put("[", new ArrayList<>(Arrays.asList("SLBRACKET", "35")));
//        this.map.put("]", new ArrayList<>(Arrays.asList("SRBRACKET", "36")));
//        this.map.put(";", new ArrayList<>(Arrays.asList("SSEMICOLON", "37")));
//        this.map.put(":", new ArrayList<>(Arrays.asList("SCOLON", "38")));
//        this.map.put("..", new ArrayList<>(Arrays.asList("SRANGE", "39")));
//        this.map.put(":=", new ArrayList<>(Arrays.asList("SASSIGN", "40")));
//        this.map.put(",", new ArrayList<>(Arrays.asList("SCOMMA", "41")));
//        this.map.put(".", new ArrayList<>(Arrays.asList("SDOT", "42")));
    }
    
    public String getToken(String s) {
        return this.map.get(s);
    }
}
