package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class AstCompiler extends Visitor {
	private int ifCount;
	private int whileCount;
	private int booleanCount;
	private int defineStorage;
	private int argumentStorage;
	private Variable currentVariable;
	private Stack<HashMap<String, Variable>> variableLists;
	private HashMap<String, SubProgram> subProgramList;
	private List<String> labelList;
	private List<String> defineConstantList;
	private List<String> caslCode;

    public AstCompiler() {
		this.ifCount = 0;
		this.whileCount = 0;
		this.booleanCount = 0;
        this.defineStorage = 0;
		this.variableLists = new Stack<>();
		this.labelList = new ArrayList<>();
		this.defineConstantList = new ArrayList<>();
		this.caslCode = new ArrayList<>();
    }

    public void start(ProgramNode programNode) throws SemanticException {
        visit(programNode);
    }

    public List<String> getCaslCode() {
        return this.caslCode;
    }

	@Override
	public void visit(ProgramNode programNode) throws SemanticException {
		this.variableLists.push(programNode.getVariableList());
		this.subProgramList = programNode.getSubProgramList();
		this.caslCode.add("CASL\tSTART\tBEGIN");
		this.caslCode.add("BEGIN\tLAD\tGR6,0");
		this.caslCode.add("\tLAD\tGR7, LIBBUF");
		programNode.getCompoundStatementNode().accept(this);
		this.caslCode.add("\tRET");
		programNode.getBlockNode().accept(this);
		this.defineStorage = 0;
		for (Variable variable : this.variableLists.peek().values()) {
			this.defineStorage += variable.getSize();
		}
		this.caslCode.add("VAR\tDS\t" + this.defineStorage);
		for (int i = 0; i < this.labelList.size(); i++) {
			this.caslCode.add(this.labelList.get(i) + "\tDC\t" + this.defineConstantList.get(i));
		}
		this.caslCode.add("LIBBUF\tDS\t256");
		this.caslCode.add("\tEND");
		this.variableLists.pop();
	}

	@Override
	public void visit(BlockNode blockNode) throws SemanticException {
		blockNode.getVariableDeclarationNode().accept(this);
		blockNode.getSubProgramDeclarationSequenceNode().accept(this);
	}

	@Override
	public void visit(VariableDeclarationNode variableDeclarationNode) throws SemanticException {
		VariableDeclarationSequenceNode variableDeclarationSequenceNode = variableDeclarationNode.getVariableDeclarationSequenceNode();
		if (variableDeclarationSequenceNode != null) {
			variableDeclarationSequenceNode.accept(this);
		}
	}

	@Override
	public void visit(VariableDeclarationSequenceNode variableDeclarationSequenceNode) throws SemanticException {
		List<VariableNameSequenceNode> variableNameSequenceNodes = variableDeclarationSequenceNode.getVariableNameSequenceNodes();
		List<TypeNode> typeNodes = variableDeclarationSequenceNode.getTypeNodes();
		for (int i = 0; i < variableNameSequenceNodes.size(); i++) {
			variableNameSequenceNodes.get(i).accept(this);
			typeNodes.get(i).accept(this);
		}
	}

	@Override
	public void visit(VariableNameSequenceNode variableNameSequenceNode) throws SemanticException {
		for (VariableNameNode variableNameNode : variableNameSequenceNode.getVariableNameNodes()) {
			variableNameNode.accept(this);
		}
	}

	@Override
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
		for (int i = variableLists.size() - 1; i >= 0; i--) {
            HashMap<String, Variable> scope = variableLists.get(i);
            if (scope.containsKey(variableNameNode.getToken().getLexical())) {
                this.currentVariable = scope.get(variableNameNode.getToken().getLexical());
                return;
            }
        }
	}

	@Override
	public void visit(TypeNode typeNode) throws SemanticException {
	}

	@Override
	public void visit(StandardTypeNode standardTypeNode) throws SemanticException {
	}

	@Override
	public void visit(ArrayTypeNode arrayTypeNode) throws SemanticException {
	}

	@Override
	public void visit(IndexMinValueNode indexMinValueNode) throws SemanticException {
	}

	@Override
	public void visit(IndexMaxValueNode indexMaxValueNode) throws SemanticException {
	}

	@Override
	public void visit(IntegerNode integerNode) throws SemanticException {
	}

	@Override
	public void visit(SignNode signNode) throws SemanticException {
		if (signNode.getToken().getTokenName().equals("SMINUS")) {
			this.caslCode.add("\tPOP\tGR2");
			this.caslCode.add("\tLD\tGR1, =0");
			this.caslCode.add("\tSUBA\tGR1, GR2");
			this.caslCode.add("\tPUSH\t0, GR1");
		}
	}

	@Override
	public void visit(SubProgramDeclarationSequenceNode subProgramDeclarationSequenceNode) throws SemanticException {
		for (SubProgramDeclarationNode subProgramDeclarationNode : subProgramDeclarationSequenceNode.getSubProgramDeclarationNodes()) {
			subProgramDeclarationNode.accept(this);
		}
	}

	@Override
	public void visit(SubProgramDeclarationNode subProgramDeclarationNode) throws SemanticException {
		SubProgram subProgram = this.subProgramList.get(subProgramDeclarationNode.getSubProgramHeadNode().getProcedureNameNode().getToken().getLexical());
		this.variableLists.push(subProgram.getVariableList());
		this.defineStorage = 0;
		for (Variable variable : this.variableLists.peek().values()) {
			this.defineStorage += variable.getSize();
		}
		this.argumentStorage = 0;
		for (Variable variable : subProgram.getArgumentList().values()) {
			this.argumentStorage += variable.getSize();
		}
		this.caslCode.add(subProgram.getLabel() + "\tNOP");
		this.caslCode.add("\tSUBL\tGR8, =" + this.defineStorage);
		this.caslCode.add("\tLD\tGR5, GR8");
		subProgramDeclarationNode.getSubProgramHeadNode().accept(this);
		subProgramDeclarationNode.getVariableDeclarationNode().accept(this);
		subProgramDeclarationNode.getCompoundStatementNode().accept(this);
		this.caslCode.add("\tADDL\tGR8, =" + this.defineStorage);
		this.caslCode.add("\tRET");
		this.variableLists.pop();
	}

	@Override
	public void visit(SubProgramHeadNode subProgramHeadNode) throws SemanticException {
		subProgramHeadNode.getFormalParameterNode().accept(this);
	}

	@Override
	public void visit(ProcedureNameNode procedureNameNode) throws SemanticException {
	}

	@Override
	public void visit(FormalParameterNode formalParameterNode) throws SemanticException {
		FormalParameterSequenceNode formalParameterSequenceNode = formalParameterNode.getFormalParameterSequenceNode();
		if (formalParameterSequenceNode != null) {
			formalParameterSequenceNode.accept(this);
		}
	}

	@Override
	public void visit(FormalParameterSequenceNode formalParameterSequenceNode) throws SemanticException {
		for (FormalParameterNameSequenceNode formalParameterNameSequenceNode : formalParameterSequenceNode.getFormalParameterNameSequenceNodes()) {
			formalParameterNameSequenceNode.accept(this);
		}
	}

	@Override
	public void visit(FormalParameterNameSequenceNode formalParameterNameSequenceNode) throws SemanticException {
		for (FormalParameterNameNode formalParameterNameNode : formalParameterNameSequenceNode.getFormalParameterNameNodes()) {
			formalParameterNameNode.accept(this);
			this.caslCode.add("\tLD\tGR2, =" + (this.defineStorage + this.argumentStorage - this.currentVariable.getAddress()));
			this.caslCode.add("\tADDL\tGR2, GR5");
			this.caslCode.add("\tLD\tGR2, 0, GR2");
			this.caslCode.add("\tLD\tGR3, =" + this.currentVariable.getAddress());
			this.caslCode.add("\tADDL\tGR3, GR5");
			this.caslCode.add("\tST\tGR2, 0, GR3");
		}
	}

	@Override
	public void visit(FormalParameterNameNode formalParameterNameNode) throws SemanticException {
		for (int i = variableLists.size() - 1; i >= 0; i--) {
            HashMap<String, Variable> scope = variableLists.get(i);
            if (scope.containsKey(formalParameterNameNode.getToken().getLexical())) {
                this.currentVariable = scope.get(formalParameterNameNode.getToken().getLexical());
                return;
            }
        }
	}

	@Override
	public void visit(CompoundStatementNode compoundStatementNode) throws SemanticException {
		for (StatementNode statementNode : compoundStatementNode.getStatementNodes()) {
			statementNode.accept(this);
		}
	}

	@Override
	public void visit(StatementNode statementNode) throws SemanticException {
		statementNode.getAstNode().accept(this);
	}

	@Override
	public void visit(IfNode ifNode) throws SemanticException {
		int count = this.ifCount++;
		ifNode.getExpressionNode().accept(this);
		this.caslCode.add("\tPOP\tGR1");
		this.caslCode.add("\tCPA\tGR1, =#0000");
		this.caslCode.add("\tJZE\tELSE" + count);
		ifNode.getCompoundStatementNodes().get(0).accept(this);
		if (ifNode.getCompoundStatementNodes().size() == 2) {
			this.caslCode.add("\tJUMP\tENDIF" + count);
			this.caslCode.add("ELSE" + count + "\tNOP");
			ifNode.getCompoundStatementNodes().get(1).accept(this);
			this.caslCode.add("ENDIF" + count + "\tNOP");
		} else {
			this.caslCode.add("ELSE" + count + "\tNOP");
		}
	}

	@Override
	public void visit(WhileNode whileNode) throws SemanticException {
		int count = this.whileCount++;
		this.caslCode.add("LOOP" + count +"\tNOP");
		whileNode.getExpressionNode().accept(this);
		this.caslCode.add("\tPOP\tGR1");
		this.caslCode.add("\tCPL\tGR1, =#0000");
		this.caslCode.add("\tJZE\tENDLP" + count);
		whileNode.getCompoundStatementNode().accept(this);
		this.caslCode.add("\tJUMP\tLOOP" + count);
		this.caslCode.add("ENDLP" + count + "\tNOP");
	}

	@Override
	public void visit(AssignmentStatementNode assignmentStatementNode) throws SemanticException {
		assignmentStatementNode.getExpressionNode().accept(this);
		assignmentStatementNode.getLeftHandSideNode().accept(this);
		this.caslCode.add("\tPOP\tGR1");
		this.caslCode.add("\tST\tGR1, 0, GR2");
	}

	@Override
	public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
		leftHandSideNode.getVariableNode().accept(this);
	}

	@Override
	public void visit(VariableNode variableNode) throws SemanticException {
		variableNode.getVariableNode().accept(this);
		if (this.currentVariable.isGlobal()) {
			this.caslCode.add("\tLAD\tGR1, VAR");
			this.caslCode.add("\tADDL\tGR2, GR1");
		} else {
			this.caslCode.add("\tADDL\tGR2, GR5");
		}
		if (variableNode.isRightValue()) {
			if (!variableNode.getType().isArray()) {
				this.caslCode.add("\tLD\tGR1, 0, GR2");
				this.caslCode.add("\tPUSH\t0, GR1");
			} else {
				this.caslCode.add("\tPUSH\t" + this.currentVariable.getSize());
				this.caslCode.add("\tPUSH\t0, GR2");
			}
		}
	}

	@Override
	public void visit(PureVariableNode pureVariableNode) throws SemanticException {
		pureVariableNode.getVariableNameNode().accept(this);
		this.caslCode.add("\tLD\tGR2, =" + this.currentVariable.getAddress());
	}

	@Override
	public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {
		indexedVariableNode.getIndexNode().accept(this);
		indexedVariableNode.getVariableNameNode().accept(this);
		this.caslCode.add("\tPOP\tGR2");
		this.caslCode.add("\tADDA\tGR2, =" + (this.currentVariable.getAddress()-this.currentVariable.getOffset()));
	}

	@Override
	public void visit(IndexNode indexNode) throws SemanticException {
		indexNode.getExpressionNode().accept(this);
	}

	@Override
	public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
		SubProgram subProgram = this.subProgramList.get(procedureCallStatementNode.getProcedureNameNode().getToken().getLexical());
		for (ExpressionNode expressionNode : procedureCallStatementNode.getExpressionNodes()) {
			expressionNode.accept(this);
		}
		this.caslCode.add("\tCALL\t" + subProgram.getLabel());
		this.caslCode.add("\tADDL\tGR8, =" + subProgram.getArgumentList().size());
		if (!procedureCallStatementNode.isGlobal()) {
			this.caslCode.add("\tLD\tGR5, GR8");
		}
	}

	@Override
	public void visit(ExpressionNode expressionNode) throws SemanticException {
		expressionNode.getLeftSimpleExpressionNode().accept(this);
        RelationalOperatorNode relationalOperatorNode = expressionNode.getRelationalOperatorNode();
        if (relationalOperatorNode != null) {
            expressionNode.getRightSimpleExpressionNode().accept(this);
			this.caslCode.add("\tPOP\tGR2");
			this.caslCode.add("\tPOP\tGR1");
			if (expressionNode.getLeftSimpleExpressionNode().getType().isBoolean()) {
				this.caslCode.add("\tCPL\tGR1, GR2");
			} else {
				this.caslCode.add("\tCPA\tGR1, GR2");
			}
            relationalOperatorNode.accept(this);
			int count = this.booleanCount++;		
			this.caslCode.add("TRUE" + count + "\tNOP");
			this.caslCode.add("\tLD\tGR1, =#FFFF");
			this.caslCode.add("BOTH" + count + "\tNOP");
			this.caslCode.add("\tPUSH\t0, GR1");
		}
	}
 
	@Override
	public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
		SignNode signNode = simpleExpressionNode.getSignNode();
        simpleExpressionNode.getLeftTermNode().accept(this);
        if (signNode != null) {
			signNode.accept(this);
        }
        List<AdditiveOperatorNode> additiveOperatorNodes = simpleExpressionNode.getAdditiveOperatorNodes();
        List<TermNode> termNodes = simpleExpressionNode.getTermNodes();
        for (int i = 0; i < additiveOperatorNodes.size(); i++) {
            termNodes.get(i).accept(this);
            additiveOperatorNodes.get(i).accept(this);
        }
	}
 
	@Override
	public void visit(TermNode termNode) throws SemanticException {
        termNode.getLeftFactorNode().accept(this);
        List<MultiplicativeOperatorNode> multiplicativeOperatorNodes = termNode.getMultiplicativeOperatorNodes();
        List<FactorNode> factorNodes = termNode.getFactorNodes();
        for (int i = 0; i < multiplicativeOperatorNodes.size(); i++) {
            factorNodes.get(i).accept(this);
            multiplicativeOperatorNodes.get(i).accept(this);
        }
	}
 
	@Override
	public void visit(FactorNode factorNode) throws SemanticException {
        VariableNode variableNode = factorNode.getVariableNode();
        ConstantNode constantNode = factorNode.getConstantNode();
        ExpressionNode expressionNode = factorNode.getExpressionNode();
        FactorNode factorNode2 = factorNode.getFactorNode();
        if (variableNode != null) {
            variableNode.accept(this);
        } else if (constantNode != null) {
            constantNode.accept(this);
        } else if (expressionNode != null) {
            expressionNode.accept(this);
        } else {
            factorNode2.accept(this);
			this.caslCode.add("\tPOP\tGR1");
			this.caslCode.add("\tXOR\tGR1, =#FFFF");
			this.caslCode.add("\tPUSH\t0, GR1");
        }
	}

	@Override
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
		int count = this.booleanCount;
		Token relationalOperator = relationalOperatorNode.getToken();
		if (relationalOperator.getTokenName().equals("SEQUAL")) {
			this.caslCode.add("\tJZE\tTRUE" + count);
		} else if (relationalOperator.getTokenName().equals("SNOTEQUAL")) {
			this.caslCode.add("\tJNZ\tTRUE" + count);
		} else if (relationalOperator.getTokenName().equals("SLESS")) {
			this.caslCode.add("\tJMI\tTRUE" + count);
		} else if (relationalOperator.getTokenName().equals("SLESSEQUAL")) {
			this.caslCode.add("\tJMI\tTRUE" + count);
			this.caslCode.add("\tJZE\tTRUE" + count);
		} else if (relationalOperator.getTokenName().equals("SGREAT")) {
			this.caslCode.add("\tJPL\tTRUE" + count);
		} else {
			this.caslCode.add("\tJPL\tTRUE" + count);
			this.caslCode.add("\tJZE\tTRUE" + count);
		}
		this.caslCode.add("\tLD\tGR1, =#0000");
		this.caslCode.add("\tJUMP\tBOTH" + count);
	}

	@Override
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
		Token additiveOperator = additiveOperatorNode.getToken();
		this.caslCode.add("\tPOP\tGR2");
		this.caslCode.add("\tPOP\tGR1");
		if (additiveOperator.getTokenName().equals("SPLUS")) {
			this.caslCode.add("\tADDA\tGR1, GR2");
		} else if (additiveOperator.getTokenName().equals("SMINUS")) {
			this.caslCode.add("\tSUBA\tGR1, GR2");
		} else {
			this.caslCode.add("\tOR\tGR1, GR2");
		}
		this.caslCode.add("\tPUSH\t0, GR1");
	}

	@Override
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
		Token multiplicativeOperator = multiplicativeOperatorNode.getToken();
		this.caslCode.add("\tPOP\tGR2");
		this.caslCode.add("\tPOP\tGR1");
		if (multiplicativeOperator.getTokenName().equals("SSTAR")) {
			this.caslCode.add("\tCALL\tMULT");
			this.caslCode.add("\tPUSH\t0, GR2");
		} else if (multiplicativeOperator.getTokenName().equals("SDIVD")) {
			this.caslCode.add("\tCALL\tDIV");
			this.caslCode.add("\tPUSH\t0, GR2");
		} else if (multiplicativeOperator.getTokenName().equals("SMOD")) {
			this.caslCode.add("\tCALL\tDIV");
			this.caslCode.add("\tPUSH\t0, GR1");
		} else {
			this.caslCode.add("\tAND\tGR1, GR2");
			this.caslCode.add("\tPUSH\t0, GR1");
		}
	}

	@Override
	public void visit(ReadlnNode readlnNode) throws SemanticException {
		for (VariableNode variableNode : readlnNode.getVariableNodes()) {
			variableNode.accept(this);
			if (variableNode.getType().isChar()) {
				this.caslCode.add("\tCALL\tRDCH");
			} else if (variableNode.getType().isInteger()) {
				this.caslCode.add("\tCALL\tRDINT");
			} else {
				this.caslCode.add("\tLD\tGR1, =" + this.currentVariable.getSize());
				this.caslCode.add("\tCALL\tRDSTR");
			}
		}
		if (readlnNode.getVariableNodes().size() == 0) {
			this.caslCode.add("\tCALL\tRDLN");
		}
	}

	@Override
	public void visit(WritelnNode writelnNode) throws SemanticException {
		for (ExpressionNode expressionNode : writelnNode.getExpressionNodes()) {
			expressionNode.accept(this);
			this.caslCode.add("\tPOP\tGR2");
			if (expressionNode.getType().isChar()) {
				this.caslCode.add("\tCALL\tWRTCH");
			} else if (expressionNode.getType().isInteger()) {
				this.caslCode.add("\tCALL\tWRTINT");
			} else {
				this.caslCode.add("\tPOP\tGR1");
				this.caslCode.add("\tCALL\tWRTSTR");
			}
		}
		this.caslCode.add("\tCALL\tWRTLN");
	}

	@Override
	public void visit(ConstantNode constantNode) throws SemanticException {
		Token token = constantNode.getToken();
		if (token.getTokenName().equals("STRUE")) {
			this.caslCode.add("\tPUSH\t#FFFF");
		} else if (token.getTokenName().equals("SFALSE")) {
			this.caslCode.add("\tPUSH\t#0000");
		} else if (token.getTokenName().equals("SCONSTANT")) {
			this.caslCode.add("\tPUSH\t" + token.getLexical());
		} else {
			if (token.getLexical().length() > 3) {
				this.caslCode.add("\tPUSH\t" + (token.getLexical().length()-2));
				this.caslCode.add("\tLAD\tGR2, " + constantNode.getLabel());
				this.caslCode.add("\tPUSH\t0, GR2");
				this.labelList.add(constantNode.getLabel());
				this.defineConstantList.add(token.getLexical());
			} else {
				this.caslCode.add("\tLD\tGR1, =" + token.getLexical());
				this.caslCode.add("\tPUSH\t0, GR1");
			}
		}
	}
}
