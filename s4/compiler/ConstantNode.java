package enshud.s4.compiler;

public class ConstantNode extends ExprNode {
    
    @Override
    public void parse(Context context) throws SyntaxException {
        this.setLine(context.getLineCount());
        this.token = context.checkTerminalSymbol("SCONSTANT", "SSTRING", "STRUE", "SFALSE");
        if (this.token.getTokenName().equals("SCONSTANT")) {
            this.setType(TypeEnum.INTEGER);
        } else if (this.token.getTokenName().equals("SSTRING")) {
            this.setType(TypeEnum.CHAR);
        } else {
            this.setType(TypeEnum.BOOLEAN);
        }
    }

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}