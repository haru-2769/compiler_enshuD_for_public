package enshud.s3.checker;

import java.util.List;
import java.util.ArrayList;

public class InputOutputStatementNode extends AstNode {
    VariableSequenceNode variableSequenceNode;
    ExpressionSequenceNode expressionSequenceNode;
    private List<VariableSequenceNode> variableSequenceNodes;
    private List<ExpressionSequenceNode> expressionSequenceNodes;
    private Token token;

    public InputOutputStatementNode() throws SyntaxException {
        this.variableSequenceNode = null;
        this.expressionSequenceNode = null;
        this.variableSequenceNodes = new ArrayList<>();
        this.expressionSequenceNodes = new ArrayList<>();
        this.token = null;
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SREADLN")) {
            this.token = context.checkTerminalSymbol("SREADLN");
            if (context.equalsAny(0, "SLPAREN")) {
                context.checkTerminalSymbol("SLPAREN");
                this.variableSequenceNode = new VariableSequenceNode();
                this.variableSequenceNodes.add(this.variableSequenceNode);
                this.variableSequenceNode.parse(context);
                context.checkTerminalSymbol("SRPAREN");
            }
        } else {
            this.token = context.checkTerminalSymbol("SWRITELN");
            if (context.equalsAny(0, "SLPAREN")) {
                context.checkTerminalSymbol("SLPAREN");
                this.expressionSequenceNode = new ExpressionSequenceNode();
                this.expressionSequenceNodes.add(this.expressionSequenceNode);
                this.expressionSequenceNode.parse(context);
                context.checkTerminalSymbol("SRPAREN");
            }
        }
    }

    public Token getToken() {
        return this.token;
    }

    public List<VariableSequenceNode> getVariableSequenceNodes() {
        return this.variableSequenceNodes;
    }

    public List<ExpressionSequenceNode> getExpressionSequenceNodes() {
        return this.expressionSequenceNodes;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
