package enshud.s4.compiler;

public abstract class StmtNode extends AstNode{
    private String line;

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return this.line;
    }
}
