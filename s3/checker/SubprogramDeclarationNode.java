package enshud.s3.checker;

public class SubprogramDeclarationNode extends NonTerminalNode {
    public SubprogramDeclarationNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new SubprogramHeadNode(context));
        addChild(new VariableDeclarationNode(context));
        addChild(new CompoundStatementNode(context));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}