package enshud.s2.parser;

import java.util.List;
import java.util.ArrayList;

public class SimpleExpressionNode extends ExprNode {
    private SignNode signNode;
    private TermNode leftTermNode;
    private List<AdditiveOperatorNode> additiveOperatorNodes;
    private List<TermNode> termNodes;

    public SimpleExpressionNode() throws SyntaxException {
        this.signNode = null;
        this.leftTermNode = null;
        this.additiveOperatorNodes = new ArrayList<>();
        this.termNodes = new ArrayList<>();
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        this.setLine(context.getLineCount());
        if (context.equalsAny(0, "SPLUS", "SMINUS")) {
            this.signNode = new SignNode();
            this.signNode.parse(context);
        }
        this.leftTermNode = new TermNode();
        this.leftTermNode.parse(context);
        AdditiveOperatorNode additiveOperatorNode;
        while (context.equalsAny(0, "SPLUS", "SMINUS", "SOR")) {
            additiveOperatorNode = new AdditiveOperatorNode();
            this.additiveOperatorNodes.add(additiveOperatorNode);
            additiveOperatorNode.parse(context);
            TermNode termNode = new TermNode();
            this.termNodes.add(termNode);
            termNode.parse(context);
        }
    }

    public SignNode getSignNode() {
        return this.signNode;
    }

    public TermNode getLeftTermNode() {
        return this.leftTermNode;
    }

    public List<AdditiveOperatorNode> getAdditiveOperatorNodes() {
        return this.additiveOperatorNodes;
    }

    public List<TermNode> getTermNodes() {
        return this.termNodes;
    }

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}