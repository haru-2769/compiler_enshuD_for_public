package enshud.s3.checker;

public class ProgramNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
    	context.checkTerminalSymbol("SPROGRAM");
        addChild(new ProgramNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
    	context.checkTerminalSymbol("SSEMICOLON");
    	BlockNode blockNode = new BlockNode();
        addChild(blockNode);
        blockNode.parse(context);
        CompoundStatementNode compoundStatementNode = new CompoundStatementNode();
        addChild(compoundStatementNode);
        compoundStatementNode.parse(context);
        context.checkTerminalSymbol("SDOT");
        if (context.getIndex() != context.getTokenList().size()) {
            throw new SyntaxException(context.getTokenList().get(context.getIndex()-1));
        }
    };

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
