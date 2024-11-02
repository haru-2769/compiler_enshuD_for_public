package enshud.s3.checker;

public class PureVariableNode extends NonTerminalNode{
    public PureVariableNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}