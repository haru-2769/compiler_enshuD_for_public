package enshud.s2.parser;

public class ProgramNameNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
    	addChild(new TerminalNode(context.checkTerminalSymbol("SIDENTIFIER")));
    };
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
