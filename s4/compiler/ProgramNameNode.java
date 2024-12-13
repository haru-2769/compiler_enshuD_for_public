package enshud.s4.compiler;

public class ProgramNameNode extends AstNode {
    private Token token;
    
    public ProgramNameNode() throws SyntaxException {
        this.token = null;
    }
    
    public void parse(Context context) throws SyntaxException {
    	this.token = context.checkTerminalSymbol("SIDENTIFIER");
    }

    public Token getToken() {
        return this.token;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
