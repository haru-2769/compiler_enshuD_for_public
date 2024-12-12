package enshud.s3.checker;

import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashMap;
 
public class AstChecker extends Visitor {
    // private String programName;
    private Type currentType;
    private Integer currentValue;
    private Integer minValue;
    private Integer maxValue;
    private List<Type> variableTypes;
    private HashMap<String, ProcedureInfo> procedureNames;
    private Stack<HashMap<String, VariableInfo>> variableNames;
    
    public AstChecker() {
        // this.programName = null;
        this.currentType = null;
        this.variableTypes = new ArrayList<>();
        this.procedureNames = new HashMap<>();
        this.variableNames = new Stack<>();
    }

    public void visit(ProgramNode programNode) throws SemanticException {
        this.variableNames.push(new HashMap<>());
        programNode.getBlockNode().accept(this);
        programNode.getCompoundStatementNode().accept(this);
        this.variableNames.pop();
    }
 
	public void visit(ProgramNameNode programNameNode) throws SemanticException {
        // this.programName = programNameNode.getToken().getLexical();
    }
 
    public void visit(BlockNode blockNode) throws SemanticException {
        blockNode.getVariableDeclarationNode().accept(this);
        blockNode.getSubprogramDeclarationSequenceNode().accept(this);
    }
 
    
    public void visit(VariableDeclarationNode variableDeclarationNode) throws SemanticException {
        if (variableDeclarationNode.getVariableDeclarationSequenceNode() != null) {
            variableDeclarationNode.getVariableDeclarationSequenceNode().accept(this);
        }
    }
 
    
    public void visit(VariableDeclarationSequenceNode variableDeclarationSequenceNode) throws SemanticException {
        List<VariableNameSequenceNode> variableNameSequenceNodes = variableDeclarationSequenceNode.getVariableNameSequenceNodes();
        List<TypeNode> typeNodes = variableDeclarationSequenceNode.getTypeNodes();
        for (int i = 0; i < variableNameSequenceNodes.size(); i++) {
            typeNodes.get(i).accept(this);
            variableNameSequenceNodes.get(i).accept(this);
        }
    }
 
    
    public void visit(VariableNameSequenceNode variableNameSequenceNode) throws SemanticException {
        HashMap<String, VariableInfo> currentScope = variableNames.peek();
        List<VariableNameNode> variableNameNodes = variableNameSequenceNode.getVariableNameNodes();
        for (VariableNameNode variableNameNode : variableNameNodes) {
            Token variableName = variableNameNode.getToken();
            if (currentScope.put(variableName.getLexical(), new VariableInfo(this.currentType, this.minValue, this.maxValue)) != null || /*variableName.getLexical().equals(programName) ||*/ procedureNames.containsKey(variableName.getLexical())) {//
                throw new SemanticException(variableName);
            }
        }
        this.minValue = null;
        this.maxValue = null;
    }
 
	
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
	}
 
    
    public void visit(TypeNode typeNode) throws SemanticException {
        StandardTypeNode standardTypeNode = typeNode.getStandardTypeNode();
        ArrayTypeNode arrayTypeNode = typeNode.getArrayTypeNode();
        if (standardTypeNode != null) {
            standardTypeNode.accept(this);
        } else {
            arrayTypeNode.accept(this);
        }
    }
 
	
	public void visit(StandardTypeNode standardTypeNode) throws SemanticException {
        if (standardTypeNode.getToken().getTokenName().equals("SINTEGER")) {
            this.currentType = Type.INTEGER;
        } else if (standardTypeNode.getToken().getTokenName().equals("SCHAR")) {
            this.currentType = Type.CHAR;
        } else {
            this.currentType = Type.BOOLEAN;
        }
	}
 
    
    public void visit(ArrayTypeNode arrayTypeNode) throws SemanticException {
        arrayTypeNode.getIndexMinValueNode().accept(this);
        arrayTypeNode.getIndexMaxValueNode().accept(this);
        arrayTypeNode.getStandardTypeNode().accept(this);
        if (this.currentType == Type.INTEGER) {
            this.currentType = Type.ARRAY_OF_INTEGER;
        } else if (this.currentType == Type.CHAR) {
            this.currentType = Type.ARRAY_OF_CHAR;
        } else {
            this.currentType = Type.ARRAY_OF_BOOLEAN;
        }
    }
 
    
    public void visit(IndexMinValueNode indexMinValueNode) throws SemanticException {
        indexMinValueNode.getIntegerNode().accept(this);
        this.minValue = this.currentValue;
    }
 
    
    public void visit(IndexMaxValueNode indexMaxValueNode) throws SemanticException {
        indexMaxValueNode.getIntegerNode().accept(this);
        this.maxValue = this.currentValue;
    }
 
    
    public void visit(IntegerNode integerNode) throws SemanticException {
        SignNode signNode = integerNode.getSignNode();
        if (signNode == null || signNode.getToken().getTokenName().equals("SPLUS")) {
            this.currentValue = Integer.parseInt(integerNode.getToken().getLexical());
        } else {
            this.currentValue = -Integer.parseInt(integerNode.getToken().getLexical());
        }
    }
 
	
	public void visit(SignNode signNode) throws SemanticException {
	}
 
    
    public void visit(SubprogramDeclarationSequenceNode subprogramDeclarationSequenceNode) throws SemanticException {
        for (SubprogramDeclarationNode subprogramDeclarationNode : subprogramDeclarationSequenceNode.getSubprogramDeclarationNodes()) {
            subprogramDeclarationNode.accept(this);
        }
    }
 
    
    public void visit(SubprogramDeclarationNode subprogramDeclarationNode) throws SemanticException {
        this.variableNames.push(new HashMap<>());
        subprogramDeclarationNode.getSubprogramHeadNode().accept(this);
        subprogramDeclarationNode.getVariableDeclarationNode().accept(this);
        subprogramDeclarationNode.getCompoundStatementNode().accept(this);
        this.variableNames.pop();
    }
 
    
    public void visit(SubprogramHeadNode subprogramHeadNode) throws SemanticException {
        Token procedureName = subprogramHeadNode.getProcedureNameNode().getToken();
        String currentSubprogramName = procedureName.getLexical();
        ProcedureInfo procedureInfo = new ProcedureInfo();
        if (procedureNames.put(currentSubprogramName, procedureInfo) != null /*|| currentSubprogramName.equals(programName)*/) {
            throw new SemanticException(procedureName);
        }
        this.variableTypes.clear();
        subprogramHeadNode.getFormalParameterNode().accept(this);
        procedureInfo.setType(this.variableTypes);
    }
 
	
	public void visit(ProcedureNameNode procedureNameNode) throws SemanticException {
	}
 
    
    public void visit(FormalParameterNode formalParameterNode) throws SemanticException {
        FormalParameterSequenceNode formalParameterSequenceNode = formalParameterNode.getFormalParameterSequenceNode();
        if (formalParameterSequenceNode != null) {
            formalParameterSequenceNode.accept(this);
        }
    }
 
    
    public void visit(FormalParameterSequenceNode formalParameterSequenceNode) throws SemanticException {
        List<FormalParameterNameSequenceNode> formalParameterNameSequenceNodes = formalParameterSequenceNode.getFormalParameterNameSequenceNodes();
        List<StandardTypeNode> standardTypeNodes = formalParameterSequenceNode.getStandardTypeNodes();
        for (int i = 0; i < formalParameterNameSequenceNodes.size(); i++) {
            standardTypeNodes.get(i).accept(this);
            formalParameterNameSequenceNodes.get(i).accept(this);
        }
    }
 
    
    public void visit(FormalParameterNameSequenceNode formalParameterNameSequenceNode) throws SemanticException {
        HashMap<String, VariableInfo> currentScope = variableNames.peek();
        List<FormalParameterNameNode> formalParameterNameNodes = formalParameterNameSequenceNode.getFormalParameterNameNodes();
        for (FormalParameterNameNode formalParameterNameNode : formalParameterNameNodes) {
            Token formalParameterName = formalParameterNameNode.getToken();
            if (currentScope.containsKey(formalParameterName.getLexical()) || /*formalParameterName.getLexical().equals(programName) ||*/ procedureNames.containsKey(formalParameterName.getLexical())) {
                throw new SemanticException(formalParameterName);
            }
            currentScope.put(formalParameterName.getLexical(), new VariableInfo(this.currentType, null, null));
            this.variableTypes.add(this.currentType);
        }
    }
 
	
	public void visit(FormalParameterNameNode formalParameterNameNode) throws SemanticException {
	}
 
    
    public void visit(CompoundStatementNode compoundStatementNode) throws SemanticException {
        compoundStatementNode.getStatementSequenceNode().accept(this);
    }
 
    
    public void visit(StatementSequenceNode statementSequenceNode) throws SemanticException {
        for (StatementNode statementNode : statementSequenceNode.getStatementNodes()) {
            statementNode.accept(this);
        }
    }
 
    
    public void visit(StatementNode statementNode) throws SemanticException {
        Token token = statementNode.getToken();
        if (token == null) {
            statementNode.getBasicStatementNode().accept(this);
        } else if (token.getTokenName().equals("SIF")) {
            statementNode.getExpressionNode().accept(this);
            if (!this.currentType.isBoolean()) {
                throw new SemanticException(token);
            }
            statementNode.getCompoundStatementNode1().accept(this);
            CompoundStatementNode compoundStatementNode2 = statementNode.getCompoundStatementNode2();
            if (compoundStatementNode2 != null) {
                compoundStatementNode2.accept(this);
            }
        } else {
            statementNode.getExpressionNode().accept(this);
            if (!this.currentType.isBoolean()) {
                throw new SemanticException(token);
            }
            statementNode.getCompoundStatementNode1().accept(this);
        }
    }
 
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
 
    
    public void visit(AssignmentStatementNode assignmentStatementNode) throws SemanticException {
        assignmentStatementNode.getLeftHandSideNode().accept(this);
        Type leftHandSideType = this.currentType;
        if (leftHandSideType.isArray()) {
            throw new SemanticException(assignmentStatementNode.getToken());
        }
        assignmentStatementNode.getExpressionNode().accept(this);
        Type expressionType = this.currentType;
        if (leftHandSideType != expressionType) {
            throw new SemanticException(assignmentStatementNode.getToken());
        }
    }
 
    
    public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
        leftHandSideNode.getVariableNode().accept(this);
    }
 
    
    public void visit(VariableNode variableNode) throws SemanticException {
        PureVariableNode pureVariableNode = variableNode.getPureVariableNode();
        IndexedVariableNode indexedVariableNode = variableNode.getIndexedVariableNode();
        if (pureVariableNode != null) {
            pureVariableNode.accept(this);
        } else {
            indexedVariableNode.accept(this);
        }
    }
 
    
    public void visit(PureVariableNode pureVariableNode) throws SemanticException {
        Token variableName = pureVariableNode.getVariableNameNode().getToken();
        for (int i = variableNames.size() - 1; i >= 0; i--) {
            HashMap<String, VariableInfo> scope = variableNames.get(i);
            if (scope.containsKey(variableName.getLexical())) {
                this.currentType = scope.get(variableName.getLexical()).getType();
                return;
            }
        }
        throw new SemanticException(variableName);
    }
 
    
    public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {
        Token variableName = indexedVariableNode.getVariableNameNode().getToken();
        for (int i = variableNames.size() - 1; i >= 0; i--) {
            HashMap<String, VariableInfo> scope = variableNames.get(i);
            if (scope.containsKey(variableName.getLexical())) {
                Type type = scope.get(variableName.getLexical()).getType();
                indexedVariableNode.getIndexNode().accept(this);
                if (!this.currentType.isInteger()) {
                    throw new SemanticException(variableName);
                }
                this.currentType = switch (type) {
                    case ARRAY_OF_INTEGER -> Type.INTEGER;
                    case ARRAY_OF_CHAR -> Type.CHAR;
                    case ARRAY_OF_BOOLEAN -> Type.BOOLEAN;
                    default -> throw new SemanticException(variableName);
                };
                return;
            }
        }
        throw new SemanticException(variableName);
    }
 
    
    public void visit(IndexNode indexNode) throws SemanticException {
        indexNode.getExpressionNode().accept(this);
    }
 
    
    public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
        Token procedureName = procedureCallStatementNode.getProcedureNameNode().getToken();
        if (!procedureNames.containsKey(procedureName.getLexical())) {
            throw new SemanticException(procedureName);
        }
        this.variableTypes.clear();
        ExpressionSequenceNode expressionSequenceNode = procedureCallStatementNode.getExpressionSequenceNode();
        if (expressionSequenceNode != null) {
            expressionSequenceNode.accept(this);
        }
        if (!procedureNames.get(procedureName.getLexical()).getType().equals(this.variableTypes)) {
            throw new SemanticException(procedureName);
        }
    }
 
    
    public void visit(ExpressionSequenceNode expressionSequenceNode) throws SemanticException {
        List<ExpressionNode> expressionNodes = expressionSequenceNode.getExpressionNodes();
        for (ExpressionNode expressionNode : expressionNodes) {
            expressionNode.accept(this);
            this.variableTypes.add(this.currentType);
        }
    }
 
    
    public void visit(ExpressionNode expressionNode) throws SemanticException {
        expressionNode.getLeftSimpleExpressionNode().accept(this);
        RelationalOperatorNode relationalOperatorNode = expressionNode.getRelationalOperatorNode();
        if (relationalOperatorNode != null) {
            Type leftType = this.currentType;
            if (leftType.isArray()) {
                throw new SemanticException(relationalOperatorNode.getToken());
            }
            expressionNode.getRightSimpleExpressionNode().accept(this);
            Type rightType = this.currentType;
            if (rightType != leftType) {
                throw new SemanticException(relationalOperatorNode.getToken());
            }
            relationalOperatorNode.accept(this);
        }
    }
 
    
    public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
        SignNode signNode = simpleExpressionNode.getSignNode();
        simpleExpressionNode.getLeftTermNode().accept(this);
        if (signNode != null) {
            if (!this.currentType.isInteger()) {
                throw new SemanticException(signNode.getToken());
            }
        }
        List<AdditiveOperatorNode> additiveOperatorNodes = simpleExpressionNode.getAdditiveOperatorNodes();
        List<TermNode> termNodes = simpleExpressionNode.getTermNodes();
        for (int i = 0; i < additiveOperatorNodes.size(); i++) {
            Type leftType = this.currentType;
            termNodes.get(i).accept(this);
            Type rightType = this.currentType;
            additiveOperatorNodes.get(i).accept(this);
            if (leftType != this.currentType || rightType != this.currentType) {
                throw new SemanticException(additiveOperatorNodes.get(i).getToken());
            }
        }
    }
 
    
    public void visit(TermNode termNode) throws SemanticException {
        termNode.getLeftFactorNode().accept(this);
        List<MultiplicativeOperatorNode> multiplicativeOperatorNodes = termNode.getMultiplicativeOperatorNodes();
        List<FactorNode> factorNodes = termNode.getFactorNodes();
        for (int i = 0; i < multiplicativeOperatorNodes.size(); i++) {
            Type leftType = this.currentType;
            factorNodes.get(i).accept(this);
            Type rightType = this.currentType;
            multiplicativeOperatorNodes.get(i).accept(this);
            if (leftType != this.currentType || rightType != this.currentType) {
                throw new SemanticException(multiplicativeOperatorNodes.get(i).getToken());
            }
        }
    }
 
    
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
            if (!this.currentType.isBoolean()) {
                throw new SemanticException(factorNode.getToken());
            }
        }
    }
 
	
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
        this.currentType = Type.BOOLEAN;
	}
 
	
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
        Token dditiveOperator = additiveOperatorNode.getToken();
        if (dditiveOperator.getTokenName().equals("SPLUS") || dditiveOperator.getTokenName().equals("SMINUS")) {
            this.currentType = Type.INTEGER;
        } else {
            this.currentType = Type.BOOLEAN;
        }
	}
 
	
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
        Token multiplicativeOperator = multiplicativeOperatorNode.getToken();
        if (multiplicativeOperator.getTokenName().equals("SAND")) {
            this.currentType = Type.BOOLEAN;
        } else {
            this.currentType = Type.INTEGER;
        }
	}
 
    
    public void visit(InputOutputStatementNode inputOutputStatementNode) throws SemanticException {
        for (VariableSequenceNode variableSequenceNode : inputOutputStatementNode.getVariableSequenceNodes()) {
            this.variableTypes.clear();
            variableSequenceNode.accept(this);
            for (Type type : this.variableTypes) {
                if (type.isArgument()) {
                    throw new SemanticException(inputOutputStatementNode.getToken());
                }
            }
        }
        for (ExpressionSequenceNode expressionSequenceNode : inputOutputStatementNode.getExpressionSequenceNodes()) {
            this.variableTypes.clear();
            expressionSequenceNode.accept(this);
            for (Type type : this.variableTypes) {
                if (type.isArgument()) {
                    throw new SemanticException(inputOutputStatementNode.getToken());
                }
            }
        }
    }
 
    
    public void visit(VariableSequenceNode variableSequenceNode) throws SemanticException {
        List<VariableNode> variableNodes = variableSequenceNode.getVariableNodes();
        for (VariableNode variableNode : variableNodes) {
            variableNode.accept(this);
            this.variableTypes.add(this.currentType);
        }
    }
 
	
	public void visit(ConstantNode constantNode) throws SemanticException {
        Token constant = constantNode.getToken();
        if (constant.getTokenName().equals("SCONSTANT")) {
            this.currentType = Type.INTEGER;
        } else if (constant.getTokenName().equals("SSTRING")) {
            this.currentType = Type.CHAR;
        } else {
            this.currentType = Type.BOOLEAN;
        }   
	}
}