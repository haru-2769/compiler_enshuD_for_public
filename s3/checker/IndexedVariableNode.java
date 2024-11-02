package enshud.s3.checker;

public class IndexedVariableNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new VariableNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
        addChild(new TerminalNode(context.checkTerminalSymbol("SLBRACKET")));
        IndexNode indexNode = new IndexNode();
        addChild(indexNode);
        indexNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SRBRACKET")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}