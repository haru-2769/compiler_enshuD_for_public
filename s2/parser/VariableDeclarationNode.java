package enshud.s2.parser;

public class VariableDeclarationNode extends NonTerminalNode {
    public VariableDeclarationNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
    	if (context.equalsAny(0, "SVAR")) {
    		context.checkTerminalSymbol("SVAR");
            addChild(new VariableDeclarationSequenceNode(context));
    	} 
    }
}