package enshud.s4.compiler;

public class ConstantNode extends AstNode {
    private String label;
    private Token token;
    
    public ConstantNode() throws SyntaxException {
        this.token = null;
        this.label = null;
    }
    
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SCONSTANT", "SSTRING", "STRUE", "SFALSE");
    }

    public Token getToken() {
        return this.token;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}