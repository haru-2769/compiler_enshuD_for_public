package enshud.s2.parser;

public class FormalParameterNode extends AstNode {
    private FormalParameterSequenceNode formalParameterSequenceNode;

    public FormalParameterNode() {
        this.formalParameterSequenceNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SLPAREN")) {
            context.checkTerminalSymbol("SLPAREN");
            this.formalParameterSequenceNode = new FormalParameterSequenceNode();
            this.formalParameterSequenceNode.parse(context);
            context.checkTerminalSymbol("SRPAREN");
        }
    }

    public FormalParameterSequenceNode getFormalParameterSequenceNode() {
        return this.formalParameterSequenceNode;
    }
}