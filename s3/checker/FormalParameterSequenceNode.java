package enshud.s3.checker;

public class FormalParameterSequenceNode extends NonTerminalNode{
    public void parse(Context context) throws SyntaxException {
        FormalParameterNameSequenceNode formalParameterNameSequenceNode = new FormalParameterNameSequenceNode();
        addChild(formalParameterNameSequenceNode);
        formalParameterNameSequenceNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SCOLON")));
        StandardTypeNode standardTypeNode = new StandardTypeNode();
        addChild(standardTypeNode);
        standardTypeNode.parse(context);
        while (context.equalsAny(0, "SCOMMA")) {
            FormalParameterNameSequenceNode formalParameterNameSequenceNode1 = new FormalParameterNameSequenceNode();
            addChild(formalParameterNameSequenceNode1);
            formalParameterNameSequenceNode1.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("SCOLON")));
            StandardTypeNode standardTypeNode1 = new StandardTypeNode();
            addChild(standardTypeNode1);
            standardTypeNode1.parse(context);
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}