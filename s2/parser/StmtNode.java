package enshud.s2.parser;

public abstract class StmtNode extends AstNode{
    private String line;

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return this.line;
    }
}
