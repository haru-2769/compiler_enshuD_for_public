package enshud.s2.parser;

public class FactorNode extends ExprNode{
    private AstNode astNode;

    public FactorNode() throws SyntaxException {
        this.astNode = null;
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.setLine(context.getLineCount());
        if (context.equalsAny(0, "SIDENTIFIER")) {
            this.astNode = new VariableNode();
            this.astNode.parse(context);
        } else if (context.equalsAny(0, "SCONSTANT", "SSTRING", "STRUE", "SFALSE")) {
            this.astNode = new ConstantNode();
            this.astNode.parse(context);
        } else if (context.equalsAny(0, "SLPAREN")) {
            context.checkTerminalSymbol("SLPAREN");
            this.astNode = new ExpressionNode();
            this.astNode.parse(context);
            context.checkTerminalSymbol("SRPAREN");
        } else {
            context.checkTerminalSymbol("SNOT");
            this.astNode = new FactorNode();
            this.astNode.parse(context);
        }
    }

    public AstNode getAstNode() {
        return this.astNode;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}