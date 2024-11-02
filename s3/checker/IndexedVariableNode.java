package enshud.s3.checker;

public class IndexedVariableNode extends NonTerminalNode {
    public IndexedVariableNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new VariableNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
        addChild(new TerminalNode(context.checkTerminalSymbol("SLBRACKET")));
        addChild(new IndexNode(context));
        addChild(new TerminalNode(context.checkTerminalSymbol("SRBRACKET")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}