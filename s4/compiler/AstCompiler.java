package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.List;

public class AstCompiler extends Visitor {
	private int defindeStorageVar;
	private List<String> caslCode;
	private List<String> labels;
	private List<String> definedConstants;

	public AstCompiler() {
		this.defindeStorageVar = 0;
		this.caslCode = new ArrayList<>();
		this.labels = new ArrayList<>();
		this.definedConstants = new ArrayList<>();
	}

	public List<String> getCaslCode() {
		return this.caslCode;
	}

	@Override
	public void visit(ProgramNode programNode) throws SemanticException {
		this.caslCode.add("CASL\tSTART\tBEGIN");
		this.caslCode.add("BEGIN\tLAD\tGR6, 0");
		this.caslCode.add("\tLAD\tGR7, LIBBUF");
        programNode.getCompoundStatementNode().accept(this);
		programNode.getBlockNode().accept(this);
		this.caslCode.add("VAR\tDS\t" + this.defindeStorageVar);
		for (int i = 0; i < this.labels.size(); i++) {
			this.caslCode.add(this.labels.get(i) + "\tDC\t" + this.definedConstants.get(i));
		}
		this.caslCode.add("LIBBUF\tDS\t256");
		this.caslCode.add("\tEND");
	}

	@Override
	public void visit(ProgramNameNode programNameNode) throws SemanticException {
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
		
		
	}

	@Override
	public void visit(SubprogramDeclarationSequenceNode subprogramDeclarationSequenceNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(SubprogramDeclarationNode subprogramDeclarationNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(SubprogramHeadNode subprogramHeadNode) throws SemanticException {
		
		
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
		compoundStatementNode.getStatementSequenceNode().accept(this);
		this.caslCode.add("\tRET");
	}

	@Override
	public void visit(StatementSequenceNode statementSequenceNode) throws SemanticException {
		for (StatementNode statementNode : statementSequenceNode.getStatementNodes()) {
		statementNode.accept(this);
		}
	}

	//TODO
	@Override
	public void visit(StatementNode statementNode) throws SemanticException {
		Token token = statementNode.getToken();
        if (token == null) {
            statementNode.getBasicStatementNode().accept(this);
        } else if (token.getTokenName().equals("SIF")) {
            statementNode.getExpressionNode().accept(this);
            statementNode.getCompoundStatementNode1().accept(this);
            CompoundStatementNode compoundStatementNode2 = statementNode.getCompoundStatementNode2();
            if (compoundStatementNode2 != null) {
                compoundStatementNode2.accept(this);
            }
        } else {
            statementNode.getExpressionNode().accept(this);
            statementNode.getCompoundStatementNode1().accept(this);
        }
	}

	@Override
	public void visit(BasicStatementNode basicStatementNode) throws SemanticException {
		AssignmentStatementNode assignmentStatementNode = basicStatementNode.getAssignmentStatementNode();
        ProcedureCallStatementNode procedureCallStatementNode = basicStatementNode.getProcedureCallStatementNode();
        InputOutputStatementNode inputOutputStatementNode = basicStatementNode.getInputOutputStatementNode();
        CompoundStatementNode compoundStatementNode = basicStatementNode.getCompoundStatementNode();
        if (assignmentStatementNode != null) {
            assignmentStatementNode.accept(this);
        } else if (procedureCallStatementNode != null) {
            procedureCallStatementNode.accept(this);
        } else if (inputOutputStatementNode != null) {
            inputOutputStatementNode.accept(this);
        } else {
            compoundStatementNode.accept(this);
        }
	}

	@Override
	public void visit(AssignmentStatementNode assignmentStatementNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(VariableNode variableNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(PureVariableNode pureVariableNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(IndexNode indexNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(ExpressionSequenceNode expressionSequenceNode) throws SemanticException {
        for (ExpressionNode expressionNode : expressionSequenceNode.getExpressionNodes()) {
            expressionNode.accept(this);
        }
	}

	@Override
	public void visit(ExpressionNode expressionNode) throws SemanticException {
		expressionNode.getLeftSimpleExpressionNode().accept(this);
        RelationalOperatorNode relationalOperatorNode = expressionNode.getRelationalOperatorNode();
        if (relationalOperatorNode != null) {
            expressionNode.getRightSimpleExpressionNode().accept(this);
            relationalOperatorNode.accept(this);
        }
	}

	@Override
	public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
		SignNode signNode = simpleExpressionNode.getSignNode();
        simpleExpressionNode.getLeftTermNode().accept(this);
        if (signNode != null) {
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
        }
	}

	@Override
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
		
	
	}

	@Override
	public void visit(InputOutputStatementNode inputOutputStatementNode) throws SemanticException {
		if (inputOutputStatementNode.getVariableSequenceNodes().size() > 0) {
			for (VariableSequenceNode variableSequenceNode : inputOutputStatementNode.getVariableSequenceNodes()) {
				variableSequenceNode.accept(this);
				//TODO
			}
		} else {
			for (ExpressionSequenceNode expressionSequenceNode : inputOutputStatementNode.getExpressionSequenceNodes()) {
				expressionSequenceNode.accept(this);
				this.caslCode.add("\tPOP\tGR2");
				this.caslCode.add("\tPOP\tGR1");
				this.caslCode.add("\tCALL\tWRTSTR");
			}
			this.caslCode.add("\tCALL\tWRTLN");
		}
	}

	@Override
	public void visit(VariableSequenceNode variableSequenceNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(ConstantNode constantNode) throws SemanticException {
		Token token = constantNode.getToken();
		if (token.getTokenName().equals("STRUE")) {
			//TODO
		} else if (token.getTokenName().equals("SFALSE")) {
			//TODO
		} else if (token.getTokenName().equals("SCONSTANT")) {
			//TODO
		} else {
			this.caslCode.add("\tLD\tGR1, =" + (token.getLexical().length()-2));
			this.caslCode.add("\tPUSH\t0, GR1");
			this.caslCode.add("\tLAD\tGR2, " + constantNode.getLabel());
			this.caslCode.add("\tPUSH\t0, GR2");
			this.labels.add(constantNode.getLabel());
			this.definedConstants.add(token.getLexical());
		}
	}

}
