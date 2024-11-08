package enshud.s3.checker;

public class StandardTypeNode extends AstNode {
    private Token token;

    public StandardTypeNode() throws SyntaxException {
        this.token = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN");
    }

    public Token getToken() {
        return this.token;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}