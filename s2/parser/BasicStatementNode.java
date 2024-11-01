package enshud.s2.parser;

public class BasicStatementNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER")) {
            if (context.equalsAny(1, "SASSIGN", "SLBRACKET")) {
                AssignmentStatementNode assignmentStatementNode = new AssignmentStatementNode();
                addChild(assignmentStatementNode);
                assignmentStatementNode.parse(context);
            } else {
                ProcedureCallStatementNode procedureCallStatementNode = new ProcedureCallStatementNode();
                addChild(procedureCallStatementNode);
                procedureCallStatementNode.parse(context);
            }
        } else if (context.equalsAny(0, "SREADLN", "SWRITELN")) {
            InputOutputStatementNode inputOutputStatementNode = new InputOutputStatementNode();
            addChild(inputOutputStatementNode);
            inputOutputStatementNode.parse(context);
        } else {
            CompoundStatementNode compoundStatementNode = new CompoundStatementNode();
            addChild(compoundStatementNode);
            compoundStatementNode.parse(context);
        }
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}