package enshud.s4.compiler;

public class StatementNode extends AstNode{
    private AstNode astNode;

    public StatementNode() {
        this.astNode = null;
    }

    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER")) {
            if (context.equalsAny(1, "SASSIGN", "SLBRACKET")) {
                this.astNode = new AssignmentStatementNode();
                this.astNode.parse(context);
            } else {
                this.astNode = new ProcedureCallStatementNode();
                this.astNode.parse(context);
            }
        } else if (context.equalsAny(0, "SREADLN")) {
            this.astNode = new ReadlnNode();
            this.astNode.parse(context);
        } else if (context.equalsAny(0, "SWRITELN")) {
            this.astNode = new WritelnNode();
            this.astNode.parse(context);
        } else if (context.equalsAny(0, "SIF")) {
            this.astNode = new IfNode();
            this.astNode.parse(context);
        } else if (context.equalsAny(0, "SWHILE")) {
            this.astNode = new WhileNode();
            this.astNode.parse(context);
        } else {
            this.astNode = new CompoundStatementNode();
            this.astNode.parse(context);
        }
    }

    public AstNode getAstNode() {
        return this.astNode;
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}