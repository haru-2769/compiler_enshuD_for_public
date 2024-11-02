package enshud.s3.checker;

public class VariableNameSequenceNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new VariableNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
        while (context.equalsAny(0, "SCOMMA")) {
            context.checkTerminalSymbol("SCOMMA");
            addChild(new VariableNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
        }
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}