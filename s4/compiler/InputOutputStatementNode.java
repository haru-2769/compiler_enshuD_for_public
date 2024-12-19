package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.List;

public class InputOutputStatementNode extends AstNode {
    private List<VariableNode> variableNodes;
    private List<ExpressionNode> expressionNodes;
    private Token token;

    public InputOutputStatementNode() throws SyntaxException {
        this.variableNodes = new ArrayList<>();
        this.expressionNodes = new ArrayList<>();
        this.token = null;
    }

    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SREADLN")) {
            VariableNode variableNode;
            this.token = context.checkTerminalSymbol("SREADLN");
            if (context.equalsAny(0, "SLPAREN")) {
                context.checkTerminalSymbol("SLPAREN");
                variableNode = new VariableNode();
                this.variableNodes.add(variableNode);
                variableNode.parse(context);
                context.checkTerminalSymbol("SRPAREN");
            }
        } else {
            ExpressionNode expressionNode;
            this.token = context.checkTerminalSymbol("SWRITELN");
            if (context.equalsAny(0, "SLPAREN")) {
                context.checkTerminalSymbol("SLPAREN");
                expressionNode = new ExpressionNode();
                this.expressionNodes.add(expressionNode);
                expressionNode.parse(context);
                while (context.equalsAny(0, "SCOMMA")) {
                    context.checkTerminalSymbol("SCOMMA");
                    expressionNode = new ExpressionNode();
                    this.expressionNodes.add(expressionNode);
                    expressionNode.parse(context);
                }
                context.checkTerminalSymbol("SRPAREN");
            }
        }
    }

    public Token getToken() {
        return this.token;
    }

    public List<VariableNode> getVariableNodes() {
        return this.variableNodes;
    }

    public List<ExpressionNode> getExpressionNodes() {
        return this.expressionNodes;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
