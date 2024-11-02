package enshud.s3.checker;

public class StandardTypeNode extends TerminalNode {
    public StandardTypeNode(Token token) {
        super(token);
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}