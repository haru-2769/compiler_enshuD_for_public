package enshud.s4.compiler;

public class FormalParameterNameNode extends AstNode{
    @Override
    public void parse(Context context) throws SyntaxException {
        this.token = context.checkTerminalSymbol("SIDENTIFIER");
    }

    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}