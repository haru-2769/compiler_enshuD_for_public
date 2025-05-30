package enshud.s2.parser;

public class StandardTypeNode extends AstNode {
    @Override
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN");
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}