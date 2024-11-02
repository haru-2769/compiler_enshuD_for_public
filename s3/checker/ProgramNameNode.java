package enshud.s3.checker;

public class ProgramNameNode extends TerminalNode {
    public ProgramNameNode(Token token) {
        super(token);
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
