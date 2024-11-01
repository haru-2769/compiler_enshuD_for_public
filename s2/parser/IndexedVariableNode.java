package enshud.s2.parser;

public class IndexedVariableNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        VariableNameNode variableNameNode = new VariableNameNode();
        addChild(variableNameNode);
        variableNameNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SLBRACKET")));
        IndexNode indexNode = new IndexNode();
        addChild(indexNode);
        indexNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SRBRACKET")));
    }
}