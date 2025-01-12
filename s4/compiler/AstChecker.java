package enshud.s4.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
 
public class AstChecker extends Visitor {
    private int charCount;
    private int ifWhileCount;
    private TypeEnum currentType;
    private int currentValue;
    private String currentSubProgramName;
    private int minValue;
    private int maxValue;
    private int address;
    private List<TypeEnum> variableTypes;
    private HashMap<String, SubProgram> subProgramList;
    private Stack<HashMap<String, Variable>> variableList;
    
    public AstChecker() {
        this.charCount = 0;
        this.ifWhileCount = 0;
        this.currentType = null;
        this.minValue = 0;
        this.maxValue = 0;
        this.address = 0;
        this.variableTypes = new ArrayList<>();
        this.subProgramList = new HashMap<>();
        this.variableList = new Stack<>();
    }

    public void start(ProgramNode programNode) throws SemanticException {
        visit(programNode);
    }

    public void visit(ProgramNode programNode) throws SemanticException {
        this.variableList.push(new HashMap<>());
        programNode.getBlockNode().accept(this);
        programNode.getCompoundStatementNode().accept(this);
        programNode.setVariableList(this.variableList.pop());
        programNode.setSubProgramList(this.subProgramList);

        // for (Variable variable : programNode.getVariableList()) {
        //     System.out.println(variable.getName() + " " + variable.getType() + " " + variable.getAddress() + " " + variable.getSize() + " " + variable.getOffset());
        // }
    }
 
    public void visit(BlockNode blockNode) throws SemanticException {
        blockNode.getVariableDeclarationNode().accept(this);
        blockNode.getSubProgramDeclarationSequenceNode().accept(this);
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
        HashMap<String, Variable> currentScope = variableList.peek();
        List<VariableNameNode> variableNameNodes = variableNameSequenceNode.getVariableNameNodes();
        int size = Math.max(1, this.maxValue-this.minValue+1);
        for (VariableNameNode variableNameNode : variableNameNodes) {
            Token variableName = variableNameNode.getToken();
            if (currentScope.put(variableName.getLexical(), new Variable(variableName.getLexical(), this.currentType, this.address, size, this.minValue)) != null || /*variableName.getLexical().equals(programName) ||*/ subProgramList.containsKey(variableName.getLexical())) {//
                throw new SemanticException(variableName.getLineCount());
            }
            this.address += size;
        }
        this.minValue = 0;
        this.maxValue = 0;
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
            this.currentType = TypeEnum.INTEGER;
        } else if (standardTypeNode.getToken().getTokenName().equals("SCHAR")) {
            this.currentType = TypeEnum.CHAR;
        } else {
            this.currentType = TypeEnum.BOOLEAN;
        }
	}
 
    
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
 
    
    public void visit(SubProgramDeclarationSequenceNode subprogramDeclarationSequenceNode) throws SemanticException {
        for (SubProgramDeclarationNode subprogramDeclarationNode : subprogramDeclarationSequenceNode.getSubProgramDeclarationNodes()) {
            subprogramDeclarationNode.accept(this);
        }
    }
 
    
    public void visit(SubProgramDeclarationNode subprogramDeclarationNode) throws SemanticException {
        this.variableList.push(new HashMap<>());
        subprogramDeclarationNode.getSubProgramHeadNode().accept(this);
        subprogramDeclarationNode.getVariableDeclarationNode().accept(this);
        subprogramDeclarationNode.getCompoundStatementNode().accept(this);
        subProgramList.get(currentSubProgramName).setVariableList(new ArrayList<> (this.variableList.pop().values()));
    }
 
    
    public void visit(SubProgramHeadNode subprogramHeadNode) throws SemanticException {
        Token procedureName = subprogramHeadNode.getProcedureNameNode().getToken();
        currentSubProgramName = procedureName.getLexical();
        SubProgram subProgram = new SubProgram(currentSubProgramName);
        if (subProgramList.put(currentSubProgramName, subProgram) != null /*|| currentSubProgramName.equals(programName)*/) {
            throw new SemanticException(procedureName.getLineCount());
        }
        this.variableTypes.clear();
        subprogramHeadNode.getFormalParameterNode().accept(this);
        subProgram.setArgumentType(this.variableTypes);
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
        HashMap<String, Variable> currentScope = variableList.peek();
        this.address = 0;
        List<FormalParameterNameNode> formalParameterNameNodes = formalParameterNameSequenceNode.getFormalParameterNameNodes();
        for (FormalParameterNameNode formalParameterNameNode : formalParameterNameNodes) {
            Token formalParameterName = formalParameterNameNode.getToken();
            if (currentScope.containsKey(formalParameterName.getLexical()) || /*formalParameterName.getLexical().equals(programName) ||*/ subProgramList.containsKey(formalParameterName.getLexical())) {
                throw new SemanticException(formalParameterName.getLineCount());
            }
            currentScope.put(formalParameterName.getLexical(), new Variable(formalParameterName.getLexical(), this.currentType, this.address, 1, 0));
            this.address++;
            this.variableTypes.add(this.currentType);
        }
    }
 
	
	public void visit(FormalParameterNameNode formalParameterNameNode) throws SemanticException {
	}
 
    
    public void visit(CompoundStatementNode compoundStatementNode) throws SemanticException {
        for (StatementNode statementNode : compoundStatementNode.getStatementNodes()) {
            statementNode.accept(this);
        }
    }
 
    
    public void visit(StatementNode statementNode) throws SemanticException {
        statementNode.getAstNode().accept(this);
    }

    public void visit(IfNode ifNode) throws SemanticException {
        ifNode.setLabelCount(this.ifWhileCount++);
        ifNode.getExpressionNode().accept(this);
        if (!this.currentType.isBoolean()) {
            throw new SemanticException(ifNode.getLine());
        }
        for (CompoundStatementNode compoundStatementNode : ifNode.getCompoundStatementNodes()) {
            compoundStatementNode.accept(this);
        }
    }

    public void visit(WhileNode whileNode) throws SemanticException {
        whileNode.setLabelCount(this.ifWhileCount++);
        whileNode.getExpressionNode().accept(this);
        if (!this.currentType.isBoolean()) {
            throw new SemanticException(whileNode.getLine());
        }
        whileNode.getCompoundStatementNode().accept(this);
    }
 
    
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
 
    
    public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
        leftHandSideNode.getVariableNode().accept(this);
    }
 
    
    public void visit(VariableNode variableNode) throws SemanticException {
        variableNode.getVariableNode().accept(this);
    }
 
    
    public void visit(PureVariableNode pureVariableNode) throws SemanticException {
        Token variableName = pureVariableNode.getVariableNameNode().getToken();
        for (int i = variableList.size() - 1; i >= 0; i--) {
            HashMap<String, Variable> scope = variableList.get(i);
            if (scope.containsKey(variableName.getLexical())) {
                this.currentType = scope.get(variableName.getLexical()).getType();
                return;
            }
        }
        throw new SemanticException(variableName.getLineCount());
    }
 
    
    public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {
        Token variableName = indexedVariableNode.getVariableNameNode().getToken();
        for (int i = variableList.size() - 1; i >= 0; i--) {
            HashMap<String, Variable> scope = variableList.get(i);
            if (scope.containsKey(variableName.getLexical())) {
                TypeEnum type = scope.get(variableName.getLexical()).getType();
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
 
    
    public void visit(IndexNode indexNode) throws SemanticException {
        indexNode.getExpressionNode().accept(this);
    }
 
    
    public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
        Token procedureName = procedureCallStatementNode.getProcedureNameNode().getToken();
        if (!subProgramList.containsKey(procedureName.getLexical())) {
            throw new SemanticException(procedureName.getLineCount());
        }
        this.variableTypes.clear();
        for (ExpressionNode expressionNode : procedureCallStatementNode.getExpressionNodes()) {
            expressionNode.accept(this);
        }
        if (!subProgramList.get(procedureName.getLexical()).getArgumentList().equals(this.variableTypes)) {
            throw new SemanticException(procedureName.getLineCount());
        }
    }

 
    
    public void visit(ExpressionNode expressionNode) throws SemanticException {
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
                throw new SemanticException(factorNode.getToken().getLineCount());
            }
        }
        factorNode.setType(this.currentType);
    }
 
	
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
        this.currentType = TypeEnum.BOOLEAN;
	}
 
	
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
        Token dditiveOperator = additiveOperatorNode.getToken();
        if (dditiveOperator.getTokenName().equals("SPLUS") || dditiveOperator.getTokenName().equals("SMINUS")) {
            this.currentType = TypeEnum.INTEGER;
        } else {
            this.currentType = TypeEnum.BOOLEAN;
        }
	}
 
	
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
        Token multiplicativeOperator = multiplicativeOperatorNode.getToken();
        if (multiplicativeOperator.getTokenName().equals("SAND")) {
            this.currentType = TypeEnum.BOOLEAN;
        } else {
            this.currentType = TypeEnum.INTEGER;
        }
	}
 
    
    public void visit(ReadlnNode readlnNode) throws SemanticException {
        for (VariableNode variableNode : readlnNode.getVariableNodes()) {
            variableNode.accept(this);
            if (currentType.isArgument()) {
                throw new SemanticException(readlnNode.getLine());
            }
        }
    }

    public void visit(WritelnNode writelnNode) throws SemanticException {
        for (ExpressionNode expressionNode : writelnNode.getExpressionNodes()) {
            expressionNode.accept(this);
            if (currentType.isArgument()) {
                throw new SemanticException(writelnNode.getLine());
            }
        }
    }
	
	public void visit(ConstantNode constantNode) throws SemanticException {
        Token constant = constantNode.getToken();
        if (constant.getTokenName().equals("SCONSTANT")) {
            this.currentType = TypeEnum.INTEGER;
        } else if (constant.getTokenName().equals("SSTRING")) {
            constantNode.setLabel("CHAR" + this.charCount++);
            this.currentType = TypeEnum.CHAR;
        } else {
            this.currentType = TypeEnum.BOOLEAN;
        }   
	}
}