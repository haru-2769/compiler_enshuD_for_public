package enshud.s3.checker;

public class ProgramNode extends NonTerminalNode {
    public ProgramNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
    	context.checkTerminalSymbol("SPROGRAM");
        addChild(new ProgramNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
    	context.checkTerminalSymbol("SSEMICOLON");
        addChild(new BlockNode(context));;
        addChild(new CompoundStatementNode(context));
        context.checkTerminalSymbol("SDOT");
        if (context.getIndex() != context.getTokenList().size()) {
            throw new SyntaxException(context.getTokenList().get(context.getIndex()-1));
        }
    };

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
