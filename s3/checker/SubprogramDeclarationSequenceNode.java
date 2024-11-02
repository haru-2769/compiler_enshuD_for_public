package enshud.s3.checker;

public class SubprogramDeclarationSequenceNode extends NonTerminalNode {
    public SubprogramDeclarationSequenceNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        while (context.equalsAny(0, "SPROCEDURE")) {
            addChild(new SubprogramDeclarationNode(context));
            context.checkTerminalSymbol("SSEMICOLON");
        }
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}