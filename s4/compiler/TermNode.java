package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.List;

public class TermNode extends ExprNode {
    private FactorNode leftFactorNode;
    private MultiplicativeOperatorNode multiplicativeOperatorNode;
    private List<MultiplicativeOperatorNode> multiplicativeOperatorNodes;
    private List<FactorNode> factorNodes;

    public TermNode() throws SyntaxException {
        this.leftFactorNode = null;
        this.multiplicativeOperatorNode = null;
        this.factorNodes = new ArrayList<FactorNode>();
        this.multiplicativeOperatorNodes = new ArrayList<MultiplicativeOperatorNode>();
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.setLine(context.getLineCount());
        this.leftFactorNode = new FactorNode();
        this.leftFactorNode.parse(context);
        while (context.equalsAny(0, "SSTAR", "SDIVD", "SMOD", "SAND")) {
            this.multiplicativeOperatorNode = new MultiplicativeOperatorNode();
            this.multiplicativeOperatorNodes.add(this.multiplicativeOperatorNode);
            this.multiplicativeOperatorNode.parse(context);
            FactorNode factorNode = new FactorNode();
            this.factorNodes.add(factorNode);
            factorNode.parse(context);
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
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}