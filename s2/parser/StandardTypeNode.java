package enshud.s2.parser;

public class StandardTypeNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN")));
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}