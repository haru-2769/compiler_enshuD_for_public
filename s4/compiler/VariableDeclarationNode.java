package enshud.s4.compiler;

public class VariableDeclarationNode extends AstNode {
    private VariableDeclarationSequenceNode variableDeclarationSequenceNode;

    public VariableDeclarationNode() throws SyntaxException {
        this.variableDeclarationSequenceNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
    	if (context.equalsAny(0, "SVAR")) {
    		context.checkTerminalSymbol("SVAR");
            this.variableDeclarationSequenceNode = new VariableDeclarationSequenceNode();
            this.variableDeclarationSequenceNode.parse(context);
    	} 
    }
    

    public VariableDeclarationSequenceNode getVariableDeclarationSequenceNode() {
        return this.variableDeclarationSequenceNode;
    }

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}