package enshud.s3.checker;

public class StatementNode extends AstNode{
    private BasicStatementNode basicStatementNode;
    Token token1, token2, token3;
    private ExpressionNode expressionNode;
    private CompoundStatementNode compoundStatementNode1, compoundStatementNode2;
    public StatementNode() throws SyntaxException {
        this.basicStatementNode = null;
        this.expressionNode = null;
        this.token1 = null;
        this.token2 = null;
        this.token3 = null;
        this.compoundStatementNode1 = null;
        this.compoundStatementNode2 = null;
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN")) {
            this.basicStatementNode = new BasicStatementNode();
            this.basicStatementNode.parse(context);
        } else if (context.equalsAny(0, "SIF")) {
            this.token1 = context.checkTerminalSymbol("SIF");
            this.expressionNode = new ExpressionNode();
            this.expressionNode.parse(context);
            this.token2 = context.checkTerminalSymbol("STHEN");
            this.compoundStatementNode1 = new CompoundStatementNode();
            this.compoundStatementNode1.parse(context);
            if (context.equalsAny(0, "SELSE")) {
                this.token3 = context.checkTerminalSymbol("SELSE");
                this.compoundStatementNode2 = new CompoundStatementNode();
                this.compoundStatementNode2.parse(context);
            }
        } else {
            this.token1 = context.checkTerminalSymbol("SWHILE");
            this.expressionNode = new ExpressionNode();
            this.expressionNode.parse(context);
            this.token2 = context.checkTerminalSymbol("SDO");
            this.compoundStatementNode1 = new CompoundStatementNode();
            this.compoundStatementNode1.parse(context);
        }
    }

    public BasicStatementNode getBasicStatementNode() {
        return this.basicStatementNode;
    }

    public ExpressionNode getExpressionNode() {
        return this.expressionNode;
    }

    public CompoundStatementNode getCompoundStatementNode1() {
        return this.compoundStatementNode1;
    }

    public CompoundStatementNode getCompoundStatementNode2() {
        return this.compoundStatementNode2;
    }

    public Token getToken1() {
        return this.token1;
    }

    public Token getToken2() {
        return this.token2;
    }

    public Token getToken3() {
        return this.token3;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}