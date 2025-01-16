package enshud.s4.compiler;

import java.util.List;
import java.util.ArrayList;

public class WritelnNode extends StmtNode {
    final private List<ExpressionNode> expressionNodes;

    public WritelnNode() {
        this.expressionNodes = new ArrayList<>();
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.setLine(context.getLineCount());
        context.checkTerminalSymbol("SWRITELN");
        if (context.equalsAny(0, "SLPAREN")) {
            ExpressionNode  expressionNode;
            context.checkTerminalSymbol("SLPAREN");
            while (true) {
                expressionNode = new ExpressionNode();
                expressionNode.parse(context);
                this.expressionNodes.add(expressionNode);
                if (context.equalsAny(0, "SCOMMA")) {
                    context.checkTerminalSymbol("SCOMMA");
                } else {
                    break;
                }
            }
            context.checkTerminalSymbol("SRPAREN");
        }
    }

    public List<ExpressionNode> getExpressionNodes() {
        return this.expressionNodes;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
