package enshud.s2.parser;

public class IntegerNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SPLUS", "SMINUS")) {
            SignNode signNode = new SignNode();
            addChild(signNode);
            signNode.parse(context);
        }
    	addChild(new TerminalNode(context.checkTerminalSymbol("SCONSTANT")));
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}