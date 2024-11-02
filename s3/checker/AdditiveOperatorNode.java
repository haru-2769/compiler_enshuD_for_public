package enshud.s3.checker;

public class AdditiveOperatorNode extends TerminalNode {
    public AdditiveOperatorNode(Token token) {
        super(token);
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
