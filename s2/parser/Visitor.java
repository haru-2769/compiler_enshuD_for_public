package enshud.s2.parser;

public interface Visitor {
    void visit(TerminalNode terminalNode);
    void visit(NonTerminalNode nonTerminalNode);
}