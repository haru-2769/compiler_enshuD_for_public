package enshud.s3.checker;

public class ProcedureNameNode extends TerminalNode {

	public ProcedureNameNode(Token token) {
		super(token);
	}
	
	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}
}