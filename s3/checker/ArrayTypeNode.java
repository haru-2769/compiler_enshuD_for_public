package enshud.s3.checker;

public class ArrayTypeNode extends AstNode {
    private IndexMinValueNode indexMinValueNode;
    private IndexMaxValueNode indexMaxValueNode;
    private StandardTypeNode standardTypeNode;

    public ArrayTypeNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        context.checkTerminalSymbol("SARRAY");
        context.checkTerminalSymbol("SLBRACKET");
        indexMinValueNode = new IndexMinValueNode(context);
        context.checkTerminalSymbol("SRANGE");
        indexMaxValueNode = new IndexMaxValueNode(context);
        context.checkTerminalSymbol("SRBRACKET");
        context.checkTerminalSymbol("SOF");
        standardTypeNode = new StandardTypeNode(context);
    }

    public IndexMinValueNode getIndexMinValueNode() {
        return indexMinValueNode;
    }

    public IndexMaxValueNode getIndexMaxValueNode() {
        return indexMaxValueNode;
    }

    public StandardTypeNode getStandardTypeNode() {
        return standardTypeNode;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
