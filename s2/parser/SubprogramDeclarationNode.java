package enshud.s2.parser;

public class SubprogramDeclarationNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        SubprogramHeadNode subprogramHeadNode = new SubprogramHeadNode();
        addChild(subprogramHeadNode);
        subprogramHeadNode.parse(context);
        VariableDeclarationNode variableDeclarationNode = new VariableDeclarationNode();
        addChild(variableDeclarationNode);
        variableDeclarationNode.parse(context);
        CompoundStatementNode compoundStatementNode = new CompoundStatementNode();
        addChild(compoundStatementNode);
        compoundStatementNode.parse(context);
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}