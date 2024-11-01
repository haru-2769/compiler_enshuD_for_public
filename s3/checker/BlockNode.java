package enshud.s3.checker;

public class BlockNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
    	VariableDeclarationNode variableDeclarationNode = new VariableDeclarationNode();
        addChild(variableDeclarationNode);
        variableDeclarationNode.parse(context);
        SubprogramDeclarationSequenceNode subprogramDeclarationSequenceNode = new SubprogramDeclarationSequenceNode();
        addChild(subprogramDeclarationSequenceNode);
        subprogramDeclarationSequenceNode.parse(context);
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
