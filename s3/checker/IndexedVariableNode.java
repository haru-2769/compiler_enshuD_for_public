package enshud.s3.checker;

public class IndexedVariableNode extends AstNode {
    private VariableNameNode variableNameNode;
    private IndexNode indexNode;

    public IndexedVariableNode() throws SyntaxException {
        this.variableNameNode = null;
        this.indexNode = null;
    }

    protected void parse(Context context) throws SyntaxException {
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
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}