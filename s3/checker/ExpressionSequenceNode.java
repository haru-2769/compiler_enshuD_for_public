package enshud.s3.checker;

import java.util.List;
import java.util.ArrayList;

public class ExpressionSequenceNode extends AstNode {
    private ExpressionNode expressionNode;
    private List<ExpressionNode> expressionNodes;

    public ExpressionSequenceNode() throws SyntaxException {
        this.expressionNodes = new ArrayList<>();
        this.expressionNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        this.expressionNode = new ExpressionNode();
        this.expressionNodes.add(this.expressionNode);
        this.expressionNode.parse(context);
        while (context.equalsAny(0, "SCOMMA")) {
            context.checkTerminalSymbol("SCOMMA");
            this.expressionNode = new ExpressionNode();
            this.expressionNodes.add(this.expressionNode);
            this.expressionNode.parse(context);
        }
    }

    public List<ExpressionNode> getExpressionNodes() {
        return this.expressionNodes;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
