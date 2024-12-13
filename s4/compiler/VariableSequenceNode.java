package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.List;

public class VariableSequenceNode extends AstNode {
    private VariableNode variableNode;
    private List<VariableNode> variableNodes;

    public VariableSequenceNode() throws SyntaxException {
        this.variableNodes = new ArrayList<VariableNode>();
    }

    public void parse(Context context) throws SyntaxException {
        this.variableNode = new VariableNode();
        this.variableNodes.add(this.variableNode);
        this.variableNode.parse(context);
        while (context.equalsAny(0, "SCOMMA")) {
            context.checkTerminalSymbol("SCOMMA");
            this.variableNode = new VariableNode();
            this.variableNodes.add(this.variableNode);
            this.variableNode.parse(context);
        }
    }

    public List<VariableNode> getVariableNodes() {
        return this.variableNodes;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}