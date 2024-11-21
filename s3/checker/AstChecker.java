package enshud.s3.checker;
 
import java.util.HashSet;
import java.util.List;
// import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.HashMap;
 
public class AstChecker extends Visitor {
    private String programName;
    private String currentType;
    private Set<String> procedureNames;
    private Stack<HashMap<String, VariableInfo>> variableNames;
    
    public AstChecker() {
        this.programName = null;
        this.currentType = null;
        this.procedureNames = new HashSet<>();
        this.variableNames = new Stack<>();
    }
    
    // private void check(HashMap<String, VariableInfo> currentScope) {
    //     for (Map.Entry<String, VariableInfo> entry : currentScope.entrySet()) {
    //         String variableName = entry.getKey();
    //         VariableInfo variableInfo = entry.getValue();
    //         System.out.println("Variable Name: " + variableName + ", Type: " + variableInfo.getType() + ", Referenced: " + variableInfo.isReferenced());
    //     }
    // }

    public void visit(ProgramNode programNode) throws SemanticException {
        this.variableNames.push(new HashMap<>());
        this.programName = programNode.getProgramNameNode().getToken().getLexical();
        programNode.getBlockNode().accept(this);
        programNode.getCompoundStatementNode().accept(this);
        this.variableNames.pop();
    }
 
	public void visit(ProgramNameNode programNameNode) throws SemanticException {
        //TODO
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
            if (currentScope.put(variableName.getLexical(), new VariableInfo(currentType)) != null || variableName.getLexical().equals(programName) || procedureNames.contains(variableName.getLexical())) {
                throw new SemanticException(variableName);
            }
        }
    }
 
	
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
        //TODO
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
        this.currentType = standardTypeNode.getToken().getLexical();
	}
 
    
    public void visit(ArrayTypeNode arrayTypeNode) throws SemanticException {
        // arrayTypeNode.getIndexMinValueNode().accept(this);
        // arrayTypeNode.getIndexMaxValueNode().accept(this);
        arrayTypeNode.getStandardTypeNode().accept(this);
        this.currentType = "array of " + this.currentType;
    }
 
    
    public void visit(IndexMinValueNode indexMinValueNode) throws SemanticException {
    }
 
    
    public void visit(IndexMaxValueNode indexMaxValueNode) throws SemanticException {
    }
 
    
    public void visit(IntegerNode integerNode) throws SemanticException {
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
        if (!procedureNames.add(procedureName.getLexical()) || procedureName.getLexical().equals(programName) || variableNames.peek().containsKey(procedureName.getLexical())) {
            throw new SemanticException(procedureName);
        }
        subprogramHeadNode.getFormalParameterNode().accept(this);
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
            if (currentScope.containsKey(formalParameterName.getLexical())) {
                throw new SemanticException(formalParameterName);
            }
            currentScope.put(formalParameterName.getLexical(), new VariableInfo(currentType));
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
            if (!this.currentType.equals("boolean")) {
                throw new SemanticException(token);
            }
            statementNode.getCompoundStatementNode1().accept(this);
            CompoundStatementNode compoundStatementNode2 = statementNode.getCompoundStatementNode2();
            if (compoundStatementNode2 != null) {
                compoundStatementNode2.accept(this);
            }
        } else {
            statementNode.getExpressionNode().accept(this);
            if (!this.currentType.equals("boolean")) {
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
        String leftHandSideType = this.currentType;
        if (leftHandSideType.startsWith("array of ")) {
            throw new SemanticException(assignmentStatementNode.getToken());
        }
        assignmentStatementNode.getExpressionNode().accept(this);
        String expressionType = this.currentType;
        if (!leftHandSideType.equals(expressionType)) {
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
                scope.get(variableName.getLexical()).setReferenced(true);
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
                scope.get(variableName.getLexical()).setReferenced(true);
                String type = scope.get(variableName.getLexical()).getType();
                if (type.startsWith("array of ")) {
                    indexedVariableNode.getIndexNode().accept(this);
                    if (!this.currentType.equals("integer")) {
                        throw new SemanticException(variableName);
                    }
                    this.currentType = type.substring(9);
                    return;
                } else {
                    throw new SemanticException(variableName);
                }
            }
        }
        throw new SemanticException(variableName);
    }
 
    
    public void visit(IndexNode indexNode) throws SemanticException {
        indexNode.getExpressionNode().accept(this);
    }
 
    
    public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
        Token procedureName = procedureCallStatementNode.getProcedureNameNode().getToken();
        if (procedureNames.contains(procedureName.getLexical()) == false) {
            throw new SemanticException(procedureName);
        }
        ExpressionSequenceNode expressionSequenceNode = procedureCallStatementNode.getExpressionSequenceNode();
        if (expressionSequenceNode != null) {
            expressionSequenceNode.accept(this);
        }
    }
 
    
    public void visit(ExpressionSequenceNode expressionSequenceNode) throws SemanticException {
        List<ExpressionNode> expressionNodes = expressionSequenceNode.getExpressionNodes();
        for (ExpressionNode expressionNode : expressionNodes) {
            expressionNode.accept(this);
        }
    }
 
    
    public void visit(ExpressionNode expressionNode) throws SemanticException {
        expressionNode.getLeftSimpleExpressionNode().accept(this);
        RelationalOperatorNode relationalOperatorNode = expressionNode.getRelationalOperatorNode();
        if (relationalOperatorNode != null) {
            String leftType = this.currentType;
            if (!leftType.equals("integer") && !leftType.equals("char") && !leftType.equals("boolean")) {
                throw new SemanticException(relationalOperatorNode.getToken());
            }
            expressionNode.getRightSimpleExpressionNode().accept(this);
            String rightType = this.currentType;
            if (!rightType.equals(leftType)) {
                throw new SemanticException(relationalOperatorNode.getToken());
            }
            relationalOperatorNode.accept(this);
        }
    }
 
    
    public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
        SignNode signNode = simpleExpressionNode.getSignNode();
        simpleExpressionNode.getLeftTermNode().accept(this);
        if (signNode != null) {
            signNode.accept(this); //TODO
            if (!this.currentType.equals("integer")) {
                throw new SemanticException(signNode.getToken());
            }
        }
        List<AdditiveOperatorNode> additiveOperatorNodes = simpleExpressionNode.getAdditiveOperatorNodes();
        List<TermNode> termNodes = simpleExpressionNode.getTermNodes();
        for (int i = 0; i < additiveOperatorNodes.size(); i++) {
            String leftType = this.currentType;
            termNodes.get(i).accept(this);
            String rightType = this.currentType;
            additiveOperatorNodes.get(i).accept(this);
            if (!leftType.equals(this.currentType) || !rightType.equals(this.currentType)) {
                throw new SemanticException(additiveOperatorNodes.get(i).getToken());
            }
        }
    }
 
    
    public void visit(TermNode termNode) throws SemanticException {
        termNode.getLeftFactorNode().accept(this);
        List<MultiplicativeOperatorNode> multiplicativeOperatorNodes = termNode.getMultiplicativeOperatorNodes();
        List<FactorNode> factorNodes = termNode.getFactorNodes();
        for (int i = 0; i < multiplicativeOperatorNodes.size(); i++) {
            String leftType = this.currentType;
            factorNodes.get(i).accept(this);
            String rightType = this.currentType;
            multiplicativeOperatorNodes.get(i).accept(this);
            if (!leftType.equals(this.currentType) || !rightType.equals(this.currentType)) {
                throw new SemanticException(multiplicativeOperatorNodes.get(i).getToken());
            }
        }
    }
 
    
    public void visit(FactorNode factorNode) throws SemanticException {
        VariableNode variableNode = factorNode.getVariableNode();
        ConstantNode constantNode = factorNode.getConstantNode();
        ExpressionNode expressionNode = factorNode.getExpressionNode();
        FactorNode factorNode2 = factorNode.getFactorNode();
        //Token token = factorNode.getToken();
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
 
	
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
        this.currentType = "boolean";
	}
 
	
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
        Token dditiveOperator = additiveOperatorNode.getToken();
        if (dditiveOperator.getTokenName().equals("SPLUS") || dditiveOperator.getTokenName().equals("SMINUS")) {
            this.currentType = "integer";
        } else {
            this.currentType = "boolean";
        }
	}
 
	
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
        Token multiplicativeOperator = multiplicativeOperatorNode.getToken();
        if (multiplicativeOperator.getTokenName().equals("SAND")) {
            this.currentType = "boolean";
        } else {
            this.currentType = "integer";
        }
	}
 
    
    public void visit(InputOutputStatementNode inputOutputStatementNode) throws SemanticException {
        for (VariableSequenceNode variableSequenceNode : inputOutputStatementNode.getVariableSequenceNodes()) {
            variableSequenceNode.accept(this);
        }
        for (ExpressionSequenceNode expressionSequenceNode : inputOutputStatementNode.getExpressionSequenceNodes()) {
            expressionSequenceNode.accept(this);
        }
    }
 
    
    public void visit(VariableSequenceNode variableSequenceNode) throws SemanticException {
    }
 
	
	public void visit(ConstantNode constantNode) throws SemanticException {
        Token constant = constantNode.getToken();
        if (constant.getTokenName().equals("SCONSTANT")) {
            this.currentType = "integer";
        } else if (constant.getTokenName().equals("SSTRING")) {
            this.currentType = "char";
        } else {
            this.currentType = "boolean";
        }   
	}
}