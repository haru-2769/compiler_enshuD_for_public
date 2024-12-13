package enshud.s4.compiler;

import java.util.List;
import java.util.ArrayList;

public class AstCompiler extends Visitor {
	private List<String> caslCode;

	public AstCompiler() {
		caslCode = new ArrayList<>();
	}

	public List<String> getCaslCode() {
		
		return caslCode;
	}

	@Override
	public void visit(ProgramNode programNode) throws SemanticException {
		
		
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
		
		
	}

	@Override
	public void visit(StatementSequenceNode statementSequenceNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(StatementNode statementNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(BasicStatementNode basicStatementNode) throws SemanticException {
		
		
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
		
		
	}

	@Override
	public void visit(ExpressionNode expressionNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(TermNode termNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(FactorNode factorNode) throws SemanticException {
		
		
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
		
		
	}

	@Override
	public void visit(VariableSequenceNode variableSequenceNode) throws SemanticException {
		
		
	}

	@Override
	public void visit(ConstantNode constantNode) throws SemanticException {
		
		
	}

}
