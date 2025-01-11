package enshud.s4.compiler;

import java.util.List;

public class SubProgram {
    private String name;
    private List<TypeEnum> argumentList;
    private List<Variable> variableList;

    public SubProgram(String name) {
        this.name = name;
    }
    
    public void setArgumentType(List<TypeEnum> argumentList) {
    	this.argumentList = argumentList;
    }

    public void setVariableList(List<Variable> variableList) {
    	this.variableList = variableList;
    }
    
    public String getName() {
        return this.name;
    }

    public List<TypeEnum> getArgumentList() {
        return this.argumentList;
    }

    public List<Variable> getVariableList() {
        return this.variableList;
    }
}
