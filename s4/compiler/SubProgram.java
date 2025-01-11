package enshud.s4.compiler;

import java.util.List;

public class SubProgram {
    private String name;
    private List<Variable> argumentList;
    private List<Variable> localVariableList;

    public SubProgram(String name) {
        this.name = name;
    }
    
    public void setArgumentList(List<Variable> argumentList) {
    	this.argumentList = argumentList;
    }

    public void setlocalVariableList(List<Variable> localVariableList) {
    	this.localVariableList = localVariableList;
    }
    
    public String getName() {
        return this.name;
    }

    public List<Variable> getArgumentList() {
        return this.argumentList;
    }

    public List<Variable> getLocVariableList() {
        return this.localVariableList;
    }
}
