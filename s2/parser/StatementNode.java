package enshud.s2.parser;

public class StatementNode extends AstNode{
    private BasicStatementNode basicStatementNode;
    Token token;
    private ExpressionNode expressionNode;
    private CompoundStatementNode compoundStatementNode1, compoundStatementNode2;

    public StatementNode() {
        this.basicStatementNode = null;
        this.expressionNode = null;
        this.token = null;
        this.compoundStatementNode1 = null;
        this.compoundStatementNode2 = null;
    }

    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN")) {
            this.basicStatementNode = new BasicStatementNode();
            this.basicStatementNode.parse(context);
        } else if (context.equalsAny(0, "SIF")) {
            this.token = context.checkTerminalSymbol("SIF");
            this.expressionNode = new ExpressionNode();
            this.expressionNode.parse(context);
            context.checkTerminalSymbol("STHEN");
            this.compoundStatementNode1 = new CompoundStatementNode();
            this.compoundStatementNode1.parse(context);
            if (context.equalsAny(0, "SELSE")) {
                context.checkTerminalSymbol("SELSE");
                this.compoundStatementNode2 = new CompoundStatementNode();
                this.compoundStatementNode2.parse(context);
            }
        } else {
            this.token = context.checkTerminalSymbol("SWHILE");
            this.expressionNode = new ExpressionNode();
            this.expressionNode.parse(context);
            context.checkTerminalSymbol("SDO");
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

    public Token getToken() {
        return this.token;
    }
}