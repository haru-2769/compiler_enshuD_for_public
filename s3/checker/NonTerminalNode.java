package enshud.s3.checker;

import java.util.ArrayList;
import java.util.List;

enum NonTerminalType {
	PROGRAM, BLOCK, VARIABLEDECLARATION, VARIABLEDECLARATIONSEQUENCE, VARIABLENAMESEQUENCE, TYPE, ARRAYTYPE, INDEXMINVALUE, INDEXMAXVALUE, INTEGER,
    SUBPROGRAMDECLARATIONSEQUENCE, SUBPROGRAMDECLARATION, SUBPROGRAMHEAD, FORMALPARAMETER, FORMALPARAMETERSEQUENCE, FORMALPARAMETERNAMESEQUENCE,
    COMPOUNDSTATEMENT, STATEMENTSEQUENCE, STATEMENT, BASICSTATEMENT, ASSIGNMENTSTATEMENT, LEFTHANDSIDE, VARIABLE, PUREVARIABLE, INDEXEDVARIABLE,
    INDEX, PROCEDURECALLSTATEMENT, EXPRESSIONSEQUENCE, EXPRESSION, SIMPLEEXPRESSION, TERM, FACTOR, INPUTOUTPUTSTATEMENT, VARIABLESEQUENCE
}

public class NonTerminalNode implements AstNode {
 private final List<AstNode> children;
 private NonTerminalType type;
 
 public NonTerminalNode(NonTerminalType type) {
	 this.children =  new ArrayList<>();
     this.type = type;
 }

 public void addChild(AstNode child) {
     this.children.add(child);
 }

 public List<AstNode> getChildren() {
     return this.children;
 }
 
 public NonTerminalType getType() {
	 return this.type;
 }

 @Override
 public void accept(Visitor visitor) {
     visitor.visit(this);
 }
}



