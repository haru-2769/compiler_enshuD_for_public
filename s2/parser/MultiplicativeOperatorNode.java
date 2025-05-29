package enshud.s2.parser;

public class MultiplicativeOperatorNode extends AstNode {   
    @Override 
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SSTAR", "SDIVD", "SMOD", "SAND");
    }

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}