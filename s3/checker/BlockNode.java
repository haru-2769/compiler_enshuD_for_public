package enshud.s3.checker;

public class BlockNode extends NonTerminalNode {
    public BlockNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableDeclarationNode(context));
        addChild(new SubprogramDeclarationSequenceNode(context));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
