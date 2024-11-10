package enshud.s2.parser;

import java.util.List;
import java.util.ArrayList;

public class FormalParameterSequenceNode extends AstNode{
    FormalParameterNameSequenceNode formalParameterNameSequenceNode;
    StandardTypeNode standardTypeNode;
    private List<FormalParameterNameSequenceNode> formalParameterNameSequenceNodes;
    private List<StandardTypeNode> standardTypeNodes;

    public FormalParameterSequenceNode() {
        this.formalParameterNameSequenceNode = null;
        this.standardTypeNode = null;
        this.formalParameterNameSequenceNodes = new ArrayList<>();
        this.standardTypeNodes = new ArrayList<>();
    }

    public void parse(Context context) throws SyntaxException {
        this.formalParameterNameSequenceNode = new FormalParameterNameSequenceNode();
        this.formalParameterNameSequenceNodes.add(this.formalParameterNameSequenceNode);
        this.formalParameterNameSequenceNode.parse(context);
        context.checkTerminalSymbol("SCOLON");
        this.standardTypeNode = new StandardTypeNode();
        this.standardTypeNodes.add(this.standardTypeNode);
        this.standardTypeNode.parse(context);
        while (context.equalsAny(0, "SSEMICOLON")) {
            context.checkTerminalSymbol("SSEMICOLON");
            this.formalParameterNameSequenceNode = new FormalParameterNameSequenceNode();
            this.formalParameterNameSequenceNodes.add(this.formalParameterNameSequenceNode);
            this.formalParameterNameSequenceNode.parse(context);
            context.checkTerminalSymbol("SCOLON");
            this.standardTypeNode = new StandardTypeNode();
            this.standardTypeNodes.add(this.standardTypeNode);
            this.standardTypeNode.parse(context);
        }
    }

    public List<FormalParameterNameSequenceNode> getFormalParameterNameSequenceNodes() {
        return this.formalParameterNameSequenceNodes;
    }

    public List<StandardTypeNode> getStandardTypeNodes() {
        return this.standardTypeNodes;
    }

}