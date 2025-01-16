package enshud.s4.compiler;

import java.util.List;
import java.util.ArrayList;

public class FormalParameterSequenceNode extends AstNode{
    private List<FormalParameterNameSequenceNode> formalParameterNameSequenceNodes;
    private List<StandardTypeNode> standardTypeNodes;

    public FormalParameterSequenceNode() throws SyntaxException {
        this.formalParameterNameSequenceNodes = new ArrayList<>();
        this.standardTypeNodes = new ArrayList<>();
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        FormalParameterNameSequenceNode formalParameterNameSequenceNode = new FormalParameterNameSequenceNode();
        this.formalParameterNameSequenceNodes.add(formalParameterNameSequenceNode);
        formalParameterNameSequenceNode.parse(context);
        context.checkTerminalSymbol("SCOLON");
        StandardTypeNode standardTypeNode = new StandardTypeNode();
        this.standardTypeNodes.add(standardTypeNode);
        standardTypeNode.parse(context);
        while (context.equalsAny(0, "SSEMICOLON")) {
            context.checkTerminalSymbol("SSEMICOLON");
            formalParameterNameSequenceNode = new FormalParameterNameSequenceNode();
            this.formalParameterNameSequenceNodes.add(formalParameterNameSequenceNode);
            formalParameterNameSequenceNode.parse(context);
            context.checkTerminalSymbol("SCOLON");
            standardTypeNode = new StandardTypeNode();
            this.standardTypeNodes.add(standardTypeNode);
            standardTypeNode.parse(context);
        }
    }

    public List<FormalParameterNameSequenceNode> getFormalParameterNameSequenceNodes() {
        return this.formalParameterNameSequenceNodes;
    }

    public List<StandardTypeNode> getStandardTypeNodes() {
        return this.standardTypeNodes;
    }

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}