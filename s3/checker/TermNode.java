package enshud.s3.checker;

public class TermNode extends NonTerminalNode {
    public TermNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new FactorNode(context));
        while (context.equalsAny(0, "SSTAR", "SDIVD", "SMOD", "SAND")) {
            addChild(new MultiplicativeOperatorNode(context));
            addChild(new FactorNode(context));
        }
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}