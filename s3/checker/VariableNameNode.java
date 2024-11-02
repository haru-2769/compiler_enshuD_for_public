package enshud.s3.checker;

public class VariableNameNode extends NonTerminalNode {
    public VariableNameNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SIDENTIFIER")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
