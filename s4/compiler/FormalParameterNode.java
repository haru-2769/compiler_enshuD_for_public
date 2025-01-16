package enshud.s4.compiler;

public class FormalParameterNode extends AstNode {
    private FormalParameterSequenceNode formalParameterSequenceNode;

    public FormalParameterNode() throws SyntaxException {
        this.formalParameterSequenceNode = null;
    }

    @Override
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

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}