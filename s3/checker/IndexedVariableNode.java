package enshud.s3.checker;

public class IndexedVariableNode extends NonTerminalNode {
    public IndexedVariableNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableNameNode(context));
        context.checkTerminalSymbol("SLBRACKET");
        addChild(new IndexNode(context));
        context.checkTerminalSymbol("SRBRACKET");
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}