package enshud.s3.checker;

public class IntegerNode extends AstNode {
    private SignNode signNode;
    private Token token;

    public IntegerNode() throws SyntaxException {
        this.signNode = null;
        this.token = null;
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SPLUS", "SMINUS")) {
            this.signNode = new SignNode();
            this.signNode.parse(context);
        }
    	this.token = context.checkTerminalSymbol("SCONSTANT");
    }

    public SignNode getSignNode() {
        return this.signNode;
    }

    public Token getToken() {
        return this.token;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}