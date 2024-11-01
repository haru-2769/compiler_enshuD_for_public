package enshud.s2.parser;

public class ArrayTypeNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SARRAY")));
        addChild(new TerminalNode(context.checkTerminalSymbol("SLBRACKET")));
        IndexMinValueNode indexMinValueNode = new IndexMinValueNode();
        addChild(indexMinValueNode);
        indexMinValueNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SRANGE")));
        IndexMaxValueNode indexMaxValueNode = new IndexMaxValueNode();
        addChild(indexMaxValueNode);
        indexMaxValueNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SRBRACKET")));
        addChild(new TerminalNode(context.checkTerminalSymbol("SOF")));
        StandardTypeNode standardTypeNode = new StandardTypeNode();
        addChild(standardTypeNode);
        standardTypeNode.parse(context);
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
