package enshud.s3.checker;

public class VariableDeclarationNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
    	if (context.equalsAny(0, "SVAR")) {
    		addChild(new TerminalNode(context.checkTerminalSymbol("SVAR")));
            VariableDeclarationSequenceNode variableDeclarationSequenceNode = new VariableDeclarationSequenceNode();
    		addChild(variableDeclarationSequenceNode);
            variableDeclarationSequenceNode.parse(context);
    	} 
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}