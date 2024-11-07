package enshud.s3.checker;

public class TerminalNode extends AstNode {
    private final Token token;

    public TerminalNode(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return this.token;
    }

    protected void parse(Context context) {
        // Do nothing
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}

