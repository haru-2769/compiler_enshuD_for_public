package enshud.s3.checker;

public class InputOutputStatementNode extends NonTerminalNode {
    public InputOutputStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SREADLN")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SREADLN")));
            if (context.equalsAny(0, "SLPAREN")) {
                addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
                addChild(new VariableSequenceNode(context));
                addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
            }
        } else {
            addChild(new TerminalNode(context.checkTerminalSymbol("SWRITELN")));
            if (context.equalsAny(0, "SLPAREN")) {
                addChild(new TerminalNode(context.checkTerminalSymbol("SLPAREN")));
                addChild(new ExpressionSequenceNode(context));
                addChild(new TerminalNode(context.checkTerminalSymbol("SRPAREN")));
            }
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
