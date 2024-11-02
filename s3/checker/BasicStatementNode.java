package enshud.s3.checker;

public class BasicStatementNode extends NonTerminalNode {
    public BasicStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER")) {
            if (context.equalsAny(1, "SASSIGN", "SLBRACKET")) {
                addChild(new AssignmentStatementNode(context));
            } else {
                addChild(new ProcedureCallStatementNode(context));
            }
        } else if (context.equalsAny(0, "SREADLN", "SWRITELN")) {
            addChild(new InputOutputStatementNode(context));
        } else {
            addChild(new CompoundStatementNode(context));
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}