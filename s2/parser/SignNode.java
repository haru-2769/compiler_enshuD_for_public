package enshud.s2.parser;

public class SignNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPLUS", "SMINUS")));
    };
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}