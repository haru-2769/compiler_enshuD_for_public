package enshud.s4.compiler;

import java.util.List;
import java.util.ArrayList;

public class VariableNameSequenceNode extends AstNode {
    private List<VariableNameNode> variableNameNodes;

    public VariableNameSequenceNode() throws SyntaxException {
        this.variableNameNodes = new ArrayList<>();
    }
    
    @Override
    public void parse(Context context) throws SyntaxException {
        VariableNameNode variableNameNode = new VariableNameNode();
        this.variableNameNodes.add(variableNameNode);
        variableNameNode.parse(context);
        while (context.equalsAny(0, "SCOMMA")) {
            context.checkTerminalSymbol("SCOMMA");
            variableNameNode = new VariableNameNode();
            this.variableNameNodes.add(variableNameNode);
            variableNameNode.parse(context);
        }
    }

    public List<VariableNameNode> getVariableNameNodes() {
        return this.variableNameNodes;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}