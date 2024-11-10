package enshud.s2.parser;

public class ArrayTypeNode extends AstNode {
    private IndexMinValueNode indexMinValueNode;
    private IndexMaxValueNode indexMaxValueNode;
    private StandardTypeNode standardTypeNode;

    public ArrayTypeNode() {
        this.indexMinValueNode = null;
        this.indexMaxValueNode = null;
        this.standardTypeNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SARRAY");
        context.checkTerminalSymbol("SLBRACKET");
        this.indexMinValueNode = new IndexMinValueNode();
        this.indexMinValueNode.parse(context);
        context.checkTerminalSymbol("SRANGE");
        this.indexMaxValueNode = new IndexMaxValueNode();
        this.indexMaxValueNode.parse(context);
        context.checkTerminalSymbol("SRBRACKET");
        context.checkTerminalSymbol("SOF");
        this.standardTypeNode = new StandardTypeNode();
        this.standardTypeNode.parse(context);
    }

    public IndexMinValueNode getIndexMinValueNode() {
        return this.indexMinValueNode;
    }

    public IndexMaxValueNode getIndexMaxValueNode() {
        return this.indexMaxValueNode;
    }

    public StandardTypeNode getStandardTypeNode() {
        return this.standardTypeNode;
    }
}
