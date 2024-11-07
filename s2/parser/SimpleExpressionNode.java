package enshud.s2.parser;

import java.util.List;
import java.util.ArrayList;

public class SimpleExpressionNode extends AstNode {
    private SignNode signNode;
    private TermNode leftTermNode;
    private AdditiveOperatorNode additiveOperatorNode;
    private TermNode termNode;
    private List<AdditiveOperatorNode> additiveOperatorNodes;
    private List<TermNode> termNodes;

    public SimpleExpressionNode() throws SyntaxException {
        this.signNode = null;
        this.leftTermNode = null;
        this.additiveOperatorNode = null;
        this.termNode = null;
        this.additiveOperatorNodes = new ArrayList<>();
        this.termNodes = new ArrayList<>();
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SPLUS", "SMINUS")) {
            this.signNode = new SignNode();
            this.signNode.parse(context);
        }
        this.leftTermNode = new TermNode();
        this.leftTermNode.parse(context);
        while (context.equalsAny(0, "SPLUS", "SMINUS", "SOR")) {
            this.additiveOperatorNode = new AdditiveOperatorNode();
            this.additiveOperatorNodes.add(this.additiveOperatorNode);
            this.additiveOperatorNode.parse(context);
            this.termNode = new TermNode();
            this.termNodes.add(this.termNode);
            this.termNode.parse(context);
        }
    }
}