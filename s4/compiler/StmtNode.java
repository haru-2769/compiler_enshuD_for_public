package enshud.s4.compiler;

public abstract class StmtNode extends AstNode{
    private String line;

    public abstract void parse(Context context) throws SyntaxException;
    public abstract void accept(Visitor visitor) throws SemanticException;

    public void setLine(String line) {
        this.line = line;
    }

    public String getLine() {
        return this.line;
    }
}
