package enshud.s2.parser;

import java.util.List;
import java.util.ArrayList;

public class VariableNameSequenceNode extends AstNode {
    private VariableNameNode variableNameNode;
    private List<VariableNameNode> variableNameNodes;

    public VariableNameSequenceNode() throws SyntaxException {
        this.variableNameNode = null;
        this.variableNameNodes = new ArrayList<>();
    }
    
    public void parse(Context context) throws SyntaxException {
        this.variableNameNode = new VariableNameNode();
        this.variableNameNodes.add(this.variableNameNode);
        this.variableNameNode.parse(context);
        while (context.equalsAny(0, "SCOMMA")) {
            context.checkTerminalSymbol("SCOMMA");
            this.variableNameNode = new VariableNameNode();
            this.variableNameNodes.add(this.variableNameNode);
            this.variableNameNode.parse(context);
        }
    }

    public List<VariableNameNode> getVariableNameNodes() {
        return this.variableNameNodes;
    }
}