package enshud.s3.checker;

public class AdditiveOperatorNode extends AstNode {
    private Token token;
    public AdditiveOperatorNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        token = context.checkTerminalSymbol("SPLUS", "SMINUS", "SOR");
    }

    public Token getToken() {
        return token;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
