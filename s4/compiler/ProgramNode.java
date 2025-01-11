package enshud.s4.compiler;

import java.util.List;

public class ProgramNode extends AstNode {
    private Token programName;
    private BlockNode blockNode;
    private CompoundStatementNode compoundStatementNode;
    private List<Variable> variableList;
    private List<SubProgram> subProgramList;

    public ProgramNode() throws SyntaxException {
        this.programName = null;
        this.blockNode = null;
        this.compoundStatementNode = null;
    }

    public void parse(Context context) throws SyntaxException {
    	context.checkTerminalSymbol("SPROGRAM");
        this.programName = context.checkTerminalSymbol("SIDENTIFIER");
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

    public Token getProgramName() {
        return this.programName;
    }

    public BlockNode getBlockNode() {
        return this.blockNode;
    }

    public CompoundStatementNode getCompoundStatementNode() {
        return this.compoundStatementNode;
    }

    public List<Variable> getVariableList() {
        return this.variableList;
    }

    public List<SubProgram> getSubProgramList() {
        return this.subProgramList;
    }

    public void setVariableList(List<Variable> variables) {
        this.variableList = variables;
    }

    public void setSubProgramList(List<SubProgram> subPrograms) {
        this.subProgramList = subPrograms;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
