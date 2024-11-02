package enshud.s3.checker;

public class BasicStatementNode extends AstNode {
    private AssignmentStatementNode assignmentStatementNode;
    private ProcedureCallStatementNode procedureCallStatementNode;
    private InputOutputStatementNode inputOutputStatementNode;
    private CompoundStatementNode compoundStatementNode;
    
    public BasicStatementNode() throws SyntaxException {
        this.assignmentStatementNode = null;
        this.procedureCallStatementNode = null;
        this.inputOutputStatementNode = null;
        this.compoundStatementNode = null;
    }

    protected void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER")) {
            if (context.equalsAny(1, "SASSIGN", "SLBRACKET")) {
                this.assignmentStatementNode = new AssignmentStatementNode();
                this.assignmentStatementNode.parse(context);
            } else {
                this.procedureCallStatementNode = new ProcedureCallStatementNode();
                this.procedureCallStatementNode.parse(context);
            }
        } else if (context.equalsAny(0, "SREADLN", "SWRITELN")) {
            this.inputOutputStatementNode = new InputOutputStatementNode();
            this.inputOutputStatementNode.parse(context);
        } else {
            this.compoundStatementNode = new CompoundStatementNode();
            this.compoundStatementNode.parse(context);
        }
    }

    public AssignmentStatementNode getAssignmentStatementNode() {
        return this.assignmentStatementNode;
    }

    public ProcedureCallStatementNode getProcedureCallStatementNode() {
        return this.procedureCallStatementNode;
    }

    public InputOutputStatementNode getInputOutputStatementNode() {
        return this.inputOutputStatementNode;
    }

    public CompoundStatementNode getCompoundStatementNode() {
        return this.compoundStatementNode;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}