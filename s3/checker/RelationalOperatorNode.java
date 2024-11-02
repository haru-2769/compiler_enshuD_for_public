package enshud.s3.checker;

public class RelationalOperatorNode extends NonTerminalNode {
    public RelationalOperatorNode(Context context) throws SyntaxException {
        parse(context);
    }
    
    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SEQUAL", "SNOTEQUAL", "SLESS", "SLESSEQUAL", "SGREAT", "SGREATEQUAL")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}