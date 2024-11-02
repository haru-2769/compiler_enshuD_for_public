package enshud.s3.checker;

public class BasicStatementNode extends AstNode {
    private AssignmentStatementNode assignmentStatementNode;
    private ProcedureCallStatementNode procedureCallStatementNode;
    private InputOutputStatementNode inputOutputStatementNode;
    private CompoundStatementNode compoundStatementNode;
    
    public BasicStatementNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER")) {
            if (context.equalsAny(1, "SASSIGN", "SLBRACKET")) {
                assignmentStatementNode = new AssignmentStatementNode(context);
            } else {
                procedureCallStatementNode = new ProcedureCallStatementNode(context);
            }
        } else if (context.equalsAny(0, "SREADLN", "SWRITELN")) {
            inputOutputStatementNode = new InputOutputStatementNode(context);
        } else {
            compoundStatementNode = new CompoundStatementNode(context);
        }
    }

    public AssignmentStatementNode getAssignmentStatementNode() {
        return assignmentStatementNode;
    }

    public ProcedureCallStatementNode getProcedureCallStatementNode() {
        return procedureCallStatementNode;
    }

    public InputOutputStatementNode getInputOutputStatementNode() {
        return inputOutputStatementNode;
    }

    public CompoundStatementNode getCompoundStatementNode() {
        return compoundStatementNode;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}