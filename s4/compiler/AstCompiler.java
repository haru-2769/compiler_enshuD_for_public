package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AstCompiler extends Visitor {
	private int ifCount;
	private int whileCount;
	private int booleanCount;
	private int procCount;
	private int defineStorage;
	private int currentAdress;
	private Variable currentVariable;
	private HashMap<String, Variable> variableList;
	private HashMap<String, SubProgram> subProgramList;
	private List<String> labelList;
	private List<String> defineConstantList;
	private List<String> caslCode;

    public AstCompiler() {
		this.ifCount = 0;
		this.whileCount = 0;
		this.booleanCount = 0;
		this.procCount = 0;
        this.defineStorage = 0;
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
		this.variableList = programNode.getVariableList();
		for (Variable variable : this.variableList.values()) {
			this.defineStorage += variable.getSize();
		}
		this.subProgramList = programNode.getSubProgramList();
		this.caslCode.add("CASL\tSTART\tBEGIN");
		this.caslCode.add("BEGIN\tLAD\tGR6,0");
		this.caslCode.add("\tLAD\tGR7, LIBBUF");
		programNode.getCompoundStatementNode().accept(this);
		this.caslCode.add("\tRET");
		for (SubProgram subProgram : this.subProgramList.values()) {
			for (Variable variable : subProgram.getVariableList()) {
				this.defineStorage += variable.getSize();
			}
		}
		this.caslCode.add("VAR\tDS\t" + this.defineStorage);
		for (int i = 0; i < this.labelList.size(); i++) {
			this.caslCode.add(this.labelList.get(i) + "\tDC\t" + this.defineConstantList.get(i));
		}
		this.caslCode.add("LIBBUF\tDS\t256");
		this.caslCode.add("\tEND");
	}

	@Override
	public void visit(BlockNode blockNode) throws SemanticException {
	}

	@Override
	public void visit(VariableDeclarationNode variableDeclarationNode) throws SemanticException {
	}

	@Override
	public void visit(VariableDeclarationSequenceNode variableDeclarationSequenceNode) throws SemanticException {
	}

	@Override
	public void visit(VariableNameSequenceNode variableNameSequenceNode) throws SemanticException {
	}

	@Override
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
		this.currentVariable = this.variableList.get(variableNameNode.getToken().getLexical());
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
	public void visit(SubProgramDeclarationSequenceNode subprogramDeclarationSequenceNode) throws SemanticException {
	}

	@Override
	public void visit(SubProgramDeclarationNode subprogramDeclarationNode) throws SemanticException {
	}

	@Override
	public void visit(SubProgramHeadNode subprogramHeadNode) throws SemanticException {
	}

	@Override
	public void visit(ProcedureNameNode procedureNameNode) throws SemanticException {
	}

	@Override
	public void visit(FormalParameterNode formalParameterNode) throws SemanticException {
	}

	@Override
	public void visit(FormalParameterSequenceNode formalParameterSequenceNode) throws SemanticException {
	}

	@Override
	public void visit(FormalParameterNameSequenceNode formalParameterNameSequenceNode) throws SemanticException {
	}

	@Override
	public void visit(FormalParameterNameNode formalParameterNameNode) throws SemanticException {
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
		this.caslCode.add("\tCPA\tGR1, =#0000"); //TODO
		this.caslCode.add("\tJZE\tELSE" + count);
		ifNode.getCompoundStatementNodes().get(0).accept(this);
		if (ifNode.getCompoundStatementNodes().size() > 1) {
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
		this.caslCode.add("\tST\tGR1, VAR, GR2");
	}

	@Override
	public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
		leftHandSideNode.getVariableNode().accept(this);
	}

	@Override
	public void visit(VariableNode variableNode) throws SemanticException {
		variableNode.getVariableNode().accept(this);
	}

	@Override
	public void visit(PureVariableNode pureVariableNode) throws SemanticException {
		pureVariableNode.getVariableNameNode().accept(this);
		this.currentAdress = this.currentVariable.getAddress();
		this.caslCode.add("\tLD\tGR2, =" + this.currentAdress);
	}

	@Override
	public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {
		indexedVariableNode.getVariableNameNode().accept(this);
		int variableAdress = this.currentVariable.getAddress();
		indexedVariableNode.getIndexNode().accept(this);
		this.caslCode.add("\tPOP\tGR2");
		this.caslCode.add("\tADDA\tGR2, =" + (variableAdress-1));
	}

	@Override
	public void visit(IndexNode indexNode) throws SemanticException {
		indexNode.getExpressionNode().accept(this);
	}

	@Override
	public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
		this.caslCode.add("\tCALL\tPROC" + this.procCount++);
	}


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
			if (expressionNode.getType().isBoolean()) {
				int count = this.booleanCount++;		
				this.caslCode.add("TRUE" + count + "\tNOP");
				this.caslCode.add("\tLD\tGR1, =#FFFF");
				this.caslCode.add("BOTH" + count + "\tNOP");
				this.caslCode.add("\tPUSH\t0, GR1");
			}
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
			this.caslCode.add("\tLD\tGR1, VAR, GR2");
			this.caslCode.add("\tPUSH\t0, GR1");
        } else if (constantNode != null) {
            constantNode.accept(this);
        } else if (expressionNode != null) {
            expressionNode.accept(this);
        } else {
            factorNode2.accept(this);
			this.caslCode.add("\tPOP\tGR1");
			this.caslCode.add("\tXOR\tGR1, =#FFFF");
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
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(WritelnNode writelnNode) throws SemanticException {
		for (ExpressionNode expressionNode : writelnNode.getExpressionNodes()) {
			expressionNode.accept(this);
			if (expressionNode.getType().isChar()) {
				this.caslCode.add("\tPOP\tGR2");
				this.caslCode.add("\tPOP\tGR1");
				this.caslCode.add("\tCALL\tWRTSTR");
			} else {
				this.caslCode.add("\tPOP\tGR2");
				this.caslCode.add("\tCALL\tWRTINT");
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
			this.caslCode.add("\tLD\tGR1, =" + (token.getLexical().length()-2));
			this.caslCode.add("\tPUSH\t0, GR1");
			this.caslCode.add("\tLAD\tGR2, " + constantNode.getLabel());
			this.caslCode.add("\tPUSH\t0, GR2");
			this.labelList.add(constantNode.getLabel());
			this.defineConstantList.add(token.getLexical());
		}
	}
}
