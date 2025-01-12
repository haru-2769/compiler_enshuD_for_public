package enshud.s4.compiler;

import java.util.HashMap;

public class ProgramNode extends AstNode {
    private Token programName;
    private BlockNode blockNode;
    private CompoundStatementNode compoundStatementNode;
    private HashMap<String, Variable> variableList;
    private HashMap<String, SubProgram> subProgramList;

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

    public HashMap<String, Variable> getVariableList() {
        return this.variableList;
    }

    public HashMap<String, SubProgram> getSubProgramList() {
        return this.subProgramList;
    }

    public void setVariableList(HashMap<String, Variable> variables) {
        this.variableList = variables;
    }

    public void setSubProgramList(HashMap<String, SubProgram> subPrograms) {
        this.subProgramList = subPrograms;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}
