package enshud.s4.compiler;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class SubProgram {
    private String label;
    private LinkedHashMap<String, Variable> argumentList;
    private HashMap<String, Variable> variableList;

    public SubProgram(int count) {
        this.label = "PROC" + count;
    }
    
    public void setArgumentList(LinkedHashMap<String, Variable> argumentList) {
    	this.argumentList = new LinkedHashMap<>(argumentList);
    }

    public void setVariableList(HashMap<String, Variable> variableList) {
    	this.variableList = new HashMap<>(variableList);
    }
    
    public String getLabel() {
        return this.label;
    }

    public LinkedHashMap<String, Variable> getArgumentList() {
        return this.argumentList;
    }

    public HashMap<String, Variable> getVariableList() {
        return this.variableList;
    }
}
