package enshud.s2.parser;
 
public abstract class Visitor {
    public abstract void visit(TerminalNode terminalNode);
    public abstract void visit(NonTerminalNode nonTerminalNode);
}