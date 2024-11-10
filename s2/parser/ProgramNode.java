package enshud.s2.parser;

public class ProgramNode extends AstNode {
    private ProgramNameNode programNameNode;
    private BlockNode blockNode;
    private CompoundStatementNode compoundStatementNode;

    public ProgramNode() {
        this.programNameNode = null;
        this.blockNode = null;
        this.compoundStatementNode = null;
    }

    public void parse(Context context) throws SyntaxException {
    	context.checkTerminalSymbol("SPROGRAM");
        this.programNameNode = new ProgramNameNode();
        this.programNameNode.parse(context);
    	context.checkTerminalSymbol("SSEMICOLON");
        this.blockNode = new BlockNode();
        this.blockNode.parse(context);
        this.compoundStatementNode = new CompoundStatementNode();
        this.compoundStatementNode.parse(context);
        context.checkTerminalSymbol("SDOT");
        if (context.getIndex() != context.getTokenList().size()) {
            throw new SyntaxException(context.getTokenList().get(context.getIndex()-1));
        }
    };

    public ProgramNameNode getProgramNameNode() {
        return this.programNameNode;
    }

    public BlockNode getBlockNode() {
        return this.blockNode;
    }

    public CompoundStatementNode getCompoundStatementNode() {
        return this.compoundStatementNode;
    }
}
