package enshud.s2.parser;

public class TerminalNode implements AstNode {
    private final Token token;

    public TerminalNode(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

