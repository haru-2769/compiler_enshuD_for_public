package enshud.s3.checker;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Stack;
 
import enshud.s2.parser.*;

public class AstChecker extends Visitor {
	private int subProgramCount;
	private TypeEnum currentType;
	private int currentValue;
	private String currentSubProgramName;
	private int minValue;
	private int maxValue;
	private int address;
	private boolean isGlobal;
	private boolean isRightValue;
	private List<TypeEnum> variableTypes;
	private HashMap<String, SubProgram> subProgramList;
	private Stack<HashMap<String, Variable>> variableList;
	private HashMap<String, Token> unassignedWarningList;
	private HashMap<String, Token> globalUnassignedWarningList;
	private List<String> usedSubProgramList;
	
	public AstChecker() {
		this.subProgramCount = 0;
		this.currentType = null;
		this.minValue = 0;
		this.maxValue = 0;
		this.variableTypes = new ArrayList<>();
		this.subProgramList = new HashMap<>();
		this.variableList = new Stack<>();
		this.unassignedWarningList = new LinkedHashMap<>();
		this.globalUnassignedWarningList = new LinkedHashMap<>();
		this.usedSubProgramList = new ArrayList<>();
	}
	
	private void printUnassignedWarning() {
		for (Token token : unassignedWarningList.values()) {
			System.out.println("Warning: Variable " + token.getLexical() + " is used before being assigned : line " + token.getLineCount());
		}
		for (Token token : globalUnassignedWarningList.values()) {
			System.out.println("Warning: Variable " + token.getLexical() + " is used before being assigned : line " + token.getLineCount());
		}
	}
	
	public void start(ProgramNode programNode) throws SemanticException {
		visit(programNode);
		printUnassignedWarning();
	}
	
	@Override
	public void visit(ProgramNode programNode) throws SemanticException {
		this.variableList.push(new HashMap<>());
		programNode.getBlockNode().accept(this);
		programNode.getCompoundStatementNode().accept(this);
		for (Variable variable : variableList.peek().values()) {
			if (!variable.isReferenced()) {
				System.out.println("Warning: Variable " + variable.getName() + " is never used : line " + variable.getLine());
			}
		}
		programNode.setVariableList(this.variableList.pop());
		programNode.setSubProgramList(this.subProgramList);
	}
	
	@Override
	public void visit(BlockNode blockNode) throws SemanticException {
		this.isGlobal = true;
		this.address = 0;
		blockNode.getVariableDeclarationNode().accept(this);
		this.isGlobal = false;
		blockNode.getSubProgramDeclarationSequenceNode().accept(this);
		this.isGlobal = true;
	}
	
	@Override
	public void visit(VariableDeclarationNode variableDeclarationNode) throws SemanticException {
		if (variableDeclarationNode.getVariableDeclarationSequenceNode() != null) {
			variableDeclarationNode.getVariableDeclarationSequenceNode().accept(this);
		}
	}
	
	@Override
	public void visit(VariableDeclarationSequenceNode variableDeclarationSequenceNode) throws SemanticException {
		List<VariableNameSequenceNode> variableNameSequenceNodes = variableDeclarationSequenceNode.getVariableNameSequenceNodes();
		List<TypeNode> typeNodes = variableDeclarationSequenceNode.getTypeNodes();
		for (int i = 0; i < variableNameSequenceNodes.size(); i++) {
			typeNodes.get(i).accept(this);
			variableNameSequenceNodes.get(i).accept(this);
		}
	}
	
	@Override
	public void visit(VariableNameSequenceNode variableNameSequenceNode) throws SemanticException {
		HashMap<String, Variable> currentScope = variableList.peek();
		List<VariableNameNode> variableNameNodes = variableNameSequenceNode.getVariableNameNodes();
		int size = Math.max(1, this.maxValue-this.minValue+1);
		for (VariableNameNode variableNameNode : variableNameNodes) {
			Token variableName = variableNameNode.getToken();
			if (currentScope.put(variableName.getLexical(), new Variable(variableName.getLexical(), this.currentType, this.address, size, this.minValue, variableName.getLineCount(), this.isGlobal)) != null || /*variableName.getLexical().equals(programName) ||*/ subProgramList.containsKey(variableName.getLexical())) {//
				throw new SemanticException(variableName.getLineCount());
			}
			this.address += size;
		}
		this.minValue = 0;
		this.maxValue = 0;
	}
	
	@Override
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
	}
	
	@Override
	public void visit(TypeNode typeNode) throws SemanticException {
		StandardTypeNode standardTypeNode = typeNode.getStandardTypeNode();
		ArrayTypeNode arrayTypeNode = typeNode.getArrayTypeNode();
		if (standardTypeNode != null) {
			standardTypeNode.accept(this);
		} else {
			arrayTypeNode.accept(this);
		}
	}
	
	@Override
	public void visit(StandardTypeNode standardTypeNode) throws SemanticException {
		if (standardTypeNode.getToken().getTokenName().equals("SINTEGER")) {
			this.currentType = TypeEnum.INTEGER;
		} else if (standardTypeNode.getToken().getTokenName().equals("SCHAR")) {
			this.currentType = TypeEnum.CHAR;
		} else {
			this.currentType = TypeEnum.BOOLEAN;
		}
	}
	
	@Override
	public void visit(ArrayTypeNode arrayTypeNode) throws SemanticException {
		arrayTypeNode.getIndexMinValueNode().accept(this);
		arrayTypeNode.getIndexMaxValueNode().accept(this);
		arrayTypeNode.getStandardTypeNode().accept(this);
		if (this.currentType == TypeEnum.INTEGER) {
			this.currentType = TypeEnum.ARRAY_OF_INTEGER;
		} else if (this.currentType == TypeEnum.CHAR) {
			this.currentType = TypeEnum.ARRAY_OF_CHAR;
		} else {
			this.currentType = TypeEnum.ARRAY_OF_BOOLEAN;
		}
	}
	
	@Override
	public void visit(IndexMinValueNode indexMinValueNode) throws SemanticException {
		indexMinValueNode.getIntegerNode().accept(this);
		this.minValue = this.currentValue;
	}
	
	@Override
	public void visit(IndexMaxValueNode indexMaxValueNode) throws SemanticException {
		indexMaxValueNode.getIntegerNode().accept(this);
		this.maxValue = this.currentValue;
	}
	
	@Override
	public void visit(IntegerNode integerNode) throws SemanticException {
		SignNode signNode = integerNode.getSignNode();
		if (signNode == null || signNode.getToken().getTokenName().equals("SPLUS")) {
			this.currentValue = Integer.parseInt(integerNode.getToken().getLexical());
		} else {
			this.currentValue = -Integer.parseInt(integerNode.getToken().getLexical());
		}
	}
	
	@Override
	public void visit(SignNode signNode) throws SemanticException {
	}
	
	@Override
	public void visit(SubProgramDeclarationSequenceNode subProgramDeclarationSequenceNode) throws SemanticException {
		for (SubProgramDeclarationNode subProgramDeclarationNode : subProgramDeclarationSequenceNode.getSubProgramDeclarationNodes()) {
			subProgramDeclarationNode.accept(this);
		}
	}
	
	@Override
	public void visit(SubProgramDeclarationNode subProgramDeclarationNode) throws SemanticException {
		this.variableList.push(new LinkedHashMap<>());
		this.address = 0;
		subProgramDeclarationNode.getSubProgramHeadNode().accept(this);
		subProgramDeclarationNode.getVariableDeclarationNode().accept(this);
		subProgramDeclarationNode.getCompoundStatementNode().accept(this);        
		for (Variable variable : variableList.peek().values()) {
			if (!variable.isReferenced()) {
				System.out.println("Warning: Variable " + variable.getName() + " is not used : line " + variable.getLine());
			}
		}
		subProgramList.get(currentSubProgramName).setVariableList(this.variableList.pop());
	}
	
	@Override
	public void visit(SubProgramHeadNode subProgramHeadNode) throws SemanticException {
		Token procedureName = subProgramHeadNode.getToken();
		currentSubProgramName = procedureName.getLexical();
		SubProgram subProgram = new SubProgram(currentSubProgramName, this.subProgramCount++);
		if (subProgramList.put(currentSubProgramName, subProgram) != null /*|| currentSubProgramName.equals(programName)*/) {
			throw new SemanticException(procedureName.getLineCount());
		}
		subProgramHeadNode.getFormalParameterNode().accept(this);
		subProgram.setArgumentList((LinkedHashMap<String, Variable>) variableList.peek());
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
		List<FormalParameterNameSequenceNode> formalParameterNameSequenceNodes = formalParameterSequenceNode.getFormalParameterNameSequenceNodes();
		List<StandardTypeNode> standardTypeNodes = formalParameterSequenceNode.getStandardTypeNodes();
		for (int i = 0; i < formalParameterNameSequenceNodes.size(); i++) {
			standardTypeNodes.get(i).accept(this);
			formalParameterNameSequenceNodes.get(i).accept(this);
		}
	}
	
	@Override
	public void visit(FormalParameterNameSequenceNode formalParameterNameSequenceNode) throws SemanticException {
		LinkedHashMap<String, Variable> currentScope = (LinkedHashMap<String, Variable>) variableList.peek();
		List<FormalParameterNameNode> formalParameterNameNodes = formalParameterNameSequenceNode.getFormalParameterNameNodes();
		for (FormalParameterNameNode formalParameterNameNode : formalParameterNameNodes) {
			Token formalParameterName = formalParameterNameNode.getToken();
			if (currentScope.containsKey(formalParameterName.getLexical()) || /*formalParameterName.getLexical().equals(programName) ||*/ subProgramList.containsKey(formalParameterName.getLexical())) {
				throw new SemanticException(formalParameterName.getLineCount());
			}
		    	currentScope.put(formalParameterName.getLexical(), new Variable(formalParameterName.getLexical(), this.currentType, this.address, 1, 0, formalParameterName.getLineCount(), false ,true));
		    	this.address++;
		}
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
		ifNode.getExpressionNode().accept(this);
		if (!this.currentType.isBoolean()) {
			throw new SemanticException(ifNode.getLine());
		}
		for (CompoundStatementNode compoundStatementNode : ifNode.getCompoundStatementNodes()) {
			compoundStatementNode.accept(this);
		}
	}
	
	@Override
	public void visit(WhileNode whileNode) throws SemanticException {
		whileNode.getExpressionNode().accept(this);
		if (!this.currentType.isBoolean()) {
			throw new SemanticException(whileNode.getLine());
		}
		whileNode.getCompoundStatementNode().accept(this);
	}
	
	@Override
	public void visit(AssignmentStatementNode assignmentStatementNode) throws SemanticException {
		assignmentStatementNode.getLeftHandSideNode().accept(this);
		TypeEnum leftHandSideType = this.currentType;
		if (leftHandSideType.isArray()) {
			throw new SemanticException(assignmentStatementNode.getLine());
		}
		assignmentStatementNode.getExpressionNode().accept(this);
		TypeEnum expressionType = this.currentType;
		if (leftHandSideType != expressionType) {
			throw new SemanticException(assignmentStatementNode.getLine());
		}
	}
	
	@Override
	public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
		this.isRightValue = false;
		leftHandSideNode.getVariableNode().accept(this);
	}
	
	@Override
	public void visit(VariableNode variableNode) throws SemanticException {
		variableNode.setIsRightValue(this.isRightValue);
		variableNode.getVariableNode().accept(this);
		variableNode.setType(this.currentType);
	}
	
	@Override
	public void visit(PureVariableNode pureVariableNode) throws SemanticException {
		Token variableName = pureVariableNode.getVariableNameNode().getToken();
		for (int i = variableList.size() - 1; i >= 0; i--) {
			HashMap<String, Variable> scope = variableList.get(i);
			if (scope.containsKey(variableName.getLexical())) {
				Variable variable = scope.get(variableName.getLexical());
				if (this.isRightValue) {
					variable.setReferenced();
					if (!variable.isAssigned()) {
						if (variable.isGlobal() && !this.isGlobal) {
							this.globalUnassignedWarningList.putIfAbsent(variableName.getLexical(), variableName);
						} else {
							this.unassignedWarningList.putIfAbsent(variableName.getLexical(), variableName);
						}
					}
				} else {
					variable.setAssigned();
				}
				this.currentType = variable.getType();
				return;
			}
		}
		throw new SemanticException(variableName.getLineCount());
	}
	
	@Override
	public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {
		Token variableName = indexedVariableNode.getVariableNameNode().getToken();
		for (int i = variableList.size() - 1; i >= 0; i--) {
			HashMap<String, Variable> scope = variableList.get(i);
			if (scope.containsKey(variableName.getLexical())) {
				Variable variable = scope.get(variableName.getLexical());
				if (this.isRightValue) {
					variable.setReferenced();
					if (!variable.isAssigned()) {
						if (variable.isGlobal() && !this.isGlobal) {
							this.globalUnassignedWarningList.putIfAbsent(variableName.getLexical(), variableName);
						} else {
							this.unassignedWarningList.putIfAbsent(variableName.getLexical(), variableName);
						}
					}
				} else {
					variable.setAssigned();
				}
				TypeEnum type = variable.getType();
				indexedVariableNode.getIndexNode().accept(this);
				if (!this.currentType.isInteger()) {
					throw new SemanticException(variableName.getLineCount());
				}
				this.currentType = switch (type) {
					case ARRAY_OF_INTEGER -> TypeEnum.INTEGER;
					case ARRAY_OF_CHAR -> TypeEnum.CHAR;
					case ARRAY_OF_BOOLEAN -> TypeEnum.BOOLEAN;
					default -> throw new SemanticException(variableName.getLineCount());
				};
				return;
			}
		}
		throw new SemanticException(variableName.getLineCount());
	}
	
	@Override
	public void visit(IndexNode indexNode) throws SemanticException {
		indexNode.getExpressionNode().accept(this);
	}
	
	@Override
	public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
		procedureCallStatementNode.setGlobal(isGlobal);
		Token procedureName = procedureCallStatementNode.getToken();
		if (isGlobal) {
			if (!this.usedSubProgramList.contains(procedureName.getLexical())) {
				this.usedSubProgramList.add(procedureName.getLexical());                
				for (Variable variable : variableList.peek().values()) {
					if (variable.isAssigned()) {
						this.globalUnassignedWarningList.remove(variable.getName());
					}
				}
			}
		}
		if (!subProgramList.containsKey(procedureName.getLexical())) {
			throw new SemanticException(procedureName.getLineCount());
		}
		this.variableTypes.clear();
		for (ExpressionNode expressionNode : procedureCallStatementNode.getExpressionNodes()) {
			expressionNode.accept(this);
			this.variableTypes.add(this.currentType);
		}
		List<TypeEnum> typeList = new ArrayList<>();
		for (Variable variable : this.subProgramList.get(procedureName.getLexical()).getArgumentList().values()) {
			typeList.add(variable.getType());
		}
		if (!this.variableTypes.equals(typeList)) {
			throw new SemanticException(procedureName.getLineCount());
		}
	}
	
	
	@Override
	public void visit(ExpressionNode expressionNode) throws SemanticException {
		this.isRightValue = true;
		expressionNode.getLeftSimpleExpressionNode().accept(this);
		RelationalOperatorNode relationalOperatorNode = expressionNode.getRelationalOperatorNode();
		if (relationalOperatorNode != null) {
			TypeEnum leftType = this.currentType;
			if (leftType.isArray()) {
				throw new SemanticException(relationalOperatorNode.getToken().getLineCount());
		    	}
			expressionNode.getRightSimpleExpressionNode().accept(this);
			TypeEnum rightType = this.currentType;
			if (rightType != leftType) {
				throw new SemanticException(relationalOperatorNode.getToken().getLineCount());
		    	}
			relationalOperatorNode.accept(this);
		}
		expressionNode.setType(this.currentType);
	}
	
	@Override
	public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
		SignNode signNode = simpleExpressionNode.getSignNode();
		simpleExpressionNode.getLeftTermNode().accept(this);
		if (signNode != null) {
			if (!this.currentType.isInteger()) {
				throw new SemanticException(signNode.getToken().getLineCount());
			}
		}
		List<AdditiveOperatorNode> additiveOperatorNodes = simpleExpressionNode.getAdditiveOperatorNodes();
		List<TermNode> termNodes = simpleExpressionNode.getTermNodes();
		for (int i = 0; i < additiveOperatorNodes.size(); i++) {
			TypeEnum leftType = this.currentType;
			termNodes.get(i).accept(this);
			TypeEnum rightType = this.currentType;
			additiveOperatorNodes.get(i).accept(this);
			if (leftType != this.currentType || rightType != this.currentType) {
				throw new SemanticException(additiveOperatorNodes.get(i).getToken().getLineCount());
			}
		}
		simpleExpressionNode.setType(this.currentType);
	}
	
	@Override
	public void visit(TermNode termNode) throws SemanticException {
		termNode.getLeftFactorNode().accept(this);
		List<MultiplicativeOperatorNode> multiplicativeOperatorNodes = termNode.getMultiplicativeOperatorNodes();
		List<FactorNode> factorNodes = termNode.getFactorNodes();
		for (int i = 0; i < multiplicativeOperatorNodes.size(); i++) {
			TypeEnum leftType = this.currentType;
			factorNodes.get(i).accept(this);
			TypeEnum rightType = this.currentType;
			multiplicativeOperatorNodes.get(i).accept(this);
			if (leftType != this.currentType || rightType != this.currentType) {
				throw new SemanticException(multiplicativeOperatorNodes.get(i).getToken().getLineCount());
			}
		}
		termNode.setType(this.currentType);
	}
	
	@Override
	public void visit(FactorNode factorNode) throws SemanticException {
		AstNode astNode = factorNode.getAstNode();
		astNode.accept(this);
		if (astNode instanceof FactorNode) {
			if (!this.currentType.isBoolean()) {
				throw new SemanticException(factorNode.getToken().getLineCount());
			}
		}
		factorNode.setType(this.currentType);
	}
	
	@Override
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
		this.currentType = TypeEnum.BOOLEAN;
	}
	
	@Override
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
		Token dditiveOperator = additiveOperatorNode.getToken();
		if (dditiveOperator.getTokenName().equals("SPLUS") || dditiveOperator.getTokenName().equals("SMINUS")) {
			this.currentType = TypeEnum.INTEGER;
		} else {
			this.currentType = TypeEnum.BOOLEAN;
		}
	}
	
	@Override
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
		Token multiplicativeOperator = multiplicativeOperatorNode.getToken();
		if (multiplicativeOperator.getTokenName().equals("SAND")) {
			this.currentType = TypeEnum.BOOLEAN;
		} else {
			this.currentType = TypeEnum.INTEGER;
		}
	}
	
	@Override
	public void visit(ReadlnNode readlnNode) throws SemanticException {
		this.isRightValue = false;
		for (VariableNode variableNode : readlnNode.getVariableNodes()) {
			variableNode.accept(this);
			if (currentType.isArgument()) {
				throw new SemanticException(readlnNode.getLine());
			}
		}
	}
	
	@Override
	public void visit(WritelnNode writelnNode) throws SemanticException {
		for (ExpressionNode expressionNode : writelnNode.getExpressionNodes()) {
			expressionNode.accept(this);
			if (currentType.isArgument()) {
				throw new SemanticException(writelnNode.getLine());
			}
		}
	}
	
	@Override
	public void visit(ConstantNode constantNode) throws SemanticException {
		Token constant = constantNode.getToken();
		if (constant.getTokenName().equals("SCONSTANT")) {
			this.currentType = TypeEnum.INTEGER;
		} else if (constant.getTokenName().equals("SSTRING")) {
			if (constant.getLexical().length() > 3) {
				this.currentType = TypeEnum.ARRAY_OF_CHAR;
			} else {
				this.currentType = TypeEnum.CHAR;
			}
		} else {
			this.currentType = TypeEnum.BOOLEAN;
		}   
	}
}
