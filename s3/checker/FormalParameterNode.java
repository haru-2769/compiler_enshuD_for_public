package enshud.s3.checker;

public class FormalParameterNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SLPAREN")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
            FormalParameterSequenceNode formalParameterSequenceNode = new FormalParameterSequenceNode();
            addChild(formalParameterSequenceNode);
            formalParameterSequenceNode.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}