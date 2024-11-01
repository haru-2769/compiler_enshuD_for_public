package enshud.s3.checker;

public class ProgramNameNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
    	addChild(new TerminalNode(context.checkTerminalSymbol("SIDENTIFIER")));
    };
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
