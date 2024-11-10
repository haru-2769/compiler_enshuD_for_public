package enshud.s2.parser;

import java.util.ArrayList;
import java.util.List;

public class TermNode extends AstNode {
    private FactorNode leftFactorNode;
    private MultiplicativeOperatorNode multiplicativeOperatorNode;
    private FactorNode FactorNode;
    private List<MultiplicativeOperatorNode> multiplicativeOperatorNodes;
    private List<FactorNode> factorNodes;

    public TermNode() {
        this.leftFactorNode = null;
        this.multiplicativeOperatorNode = null;
        this.FactorNode = null;
        this.factorNodes = new ArrayList<FactorNode>();
        this.multiplicativeOperatorNodes = new ArrayList<MultiplicativeOperatorNode>();
    }

    public void parse(Context context) throws SyntaxException {
        this.leftFactorNode = new FactorNode();
        this.leftFactorNode.parse(context);
        while (context.equalsAny(0, "SSTAR", "SDIVD", "SMOD", "SAND")) {
            this.multiplicativeOperatorNode = new MultiplicativeOperatorNode();
            this.multiplicativeOperatorNodes.add(this.multiplicativeOperatorNode);
            this.multiplicativeOperatorNode.parse(context);
            this.FactorNode = new FactorNode();
            this.factorNodes.add(this.FactorNode);
            this.FactorNode.parse(context);
        }
    }

    public FactorNode getLeftFactorNode() {
        return this.leftFactorNode;
    }

    public List<FactorNode> getFactorNodes() {
        return this.factorNodes;
    }

    public List<MultiplicativeOperatorNode> getMultiplicativeOperatorNodes() {
        return this.multiplicativeOperatorNodes;
    }
}