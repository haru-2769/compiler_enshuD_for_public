package enshud.s2.parser;

public class IndexedVariableNode extends AstNode {
    private VariableNameNode variableNameNode;
    private IndexNode indexNode;

    public IndexedVariableNode() {
        this.variableNameNode = null;
        this.indexNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.variableNameNode = new VariableNameNode();
        this.variableNameNode.parse(context);
        context.checkTerminalSymbol("SLBRACKET");
        this.indexNode = new IndexNode();
        this.indexNode.parse(context);
        context.checkTerminalSymbol("SRBRACKET");
    }

    public VariableNameNode getVariableNameNode() {
        return this.variableNameNode;
    }

    public IndexNode getIndexNode() {
        return this.indexNode;
    }
}