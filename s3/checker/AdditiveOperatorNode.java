package enshud.s3.checker;

public class AdditiveOperatorNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPLUS", "SMINUS", "SOR")));
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
