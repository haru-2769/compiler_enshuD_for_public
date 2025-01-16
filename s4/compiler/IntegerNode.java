package enshud.s4.compiler;

public class IntegerNode extends AstNode {
    private SignNode signNode;

    public IntegerNode() throws SyntaxException {
        this.signNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SPLUS", "SMINUS")) {
            this.signNode = new SignNode();
            this.signNode.parse(context);
        }
    	this.token = context.checkTerminalSymbol("SCONSTANT");
    }

    public SignNode getSignNode() {
        return this.signNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}