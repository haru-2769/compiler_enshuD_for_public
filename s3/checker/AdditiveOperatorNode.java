package enshud.s3.checker;

public class AdditiveOperatorNode extends NonTerminalNode {
    public AdditiveOperatorNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPLUS", "SMINUS", "SOR")));
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
