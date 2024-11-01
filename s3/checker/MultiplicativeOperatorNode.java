package enshud.s3.checker;

public class MultiplicativeOperatorNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SSTAR", "SDIVD", "SMOD", "SAND")));
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}