package enshud.s2.parser;

public class SubprogramDeclarationNode extends NonTerminalNode {
    public SubprogramDeclarationNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new SubprogramHeadNode(context));
        addChild(new VariableDeclarationNode(context));
        addChild(new CompoundStatementNode(context));
    }
}