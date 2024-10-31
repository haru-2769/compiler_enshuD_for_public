package enshud.s3.checker;

public interface Visitor {
    void visit(TerminalNode terminalNode);
    void visit(NonTerminalNode nonTerminalNode);
}