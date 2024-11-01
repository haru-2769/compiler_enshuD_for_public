package enshud.s2.parser;

public class ProgramNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
    	addChild(new TerminalNode(context.checkTerminalSymbol("SPROGRAM")));
    	ProgramNameNode programNameNode = new ProgramNameNode();
    	addChild(programNameNode);
    	programNameNode.parse(context);
    	addChild(new TerminalNode(context.checkTerminalSymbol("SSEMICOLON")));
    	BlockNode blockNode = new BlockNode();
        addChild(blockNode);
        blockNode.parse(context);
        CompoundStatementNode compoundStatementNode = new CompoundStatementNode();
        addChild(compoundStatementNode);
        compoundStatementNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SDOT")));
        if (context.getIndex() != context.getTokenList().size()) {
            throw new SyntaxException(context.getTokenList().get(context.getIndex()-1));
        }
    };

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
