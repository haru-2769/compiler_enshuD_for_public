package enshud.s4.compiler;

public class FactorNode extends ExprNode{
    private VariableNode variableNode;
    private ConstantNode constantNode;
    private ExpressionNode expressionNode;
    private FactorNode factorNode;
    private Token token;

    public FactorNode() throws SyntaxException {
        this.variableNode = null;
        this.constantNode = null;
        this.expressionNode = null;
        this.factorNode = null;
        this.token = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.setLine(context.getLineCount());
        if (context.equalsAny(0, "SIDENTIFIER")) {
            this.variableNode = new VariableNode();
            this.variableNode.parse(context);
        } else if (context.equalsAny(0, "SCONSTANT", "SSTRING", "STRUE", "SFALSE")) {
            this.constantNode = new ConstantNode();
            this.constantNode.parse(context);
        } else if (context.equalsAny(0, "SLPAREN")) {
            context.checkTerminalSymbol("SLPAREN");
            this.expressionNode = new ExpressionNode();
            this.expressionNode.parse(context);
            context.checkTerminalSymbol("SRPAREN");
        } else {
            this.token = context.checkTerminalSymbol("SNOT");
            this.factorNode = new FactorNode();
            this.factorNode.parse(context);
        }
    }

    public VariableNode getVariableNode() {
        return this.variableNode;
    }

    public ConstantNode getConstantNode() {
        return this.constantNode;
    }

    public ExpressionNode getExpressionNode() {
        return this.expressionNode;
    }

    public FactorNode getFactorNode() {
        return this.factorNode;
    }

    public Token getToken() {
        return this.token;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}