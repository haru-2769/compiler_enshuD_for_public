package enshud.s3.checker;

public class VariableNameNode extends TerminalNode {
    public VariableNameNode(Token token) {
        super(token);
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
