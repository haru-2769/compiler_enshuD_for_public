package enshud.s3.checker;
 
import java.util.List;

public class AstPrinter extends Visitor {
    private int indent = 0;

    public void printTab() {
        for (int i = 0; i < indent; i++) {
            System.out.print("\t");
        }
    }

    public void visit(ProgramNode programNode) throws SemanticException {
        System.out.println("ProgramNode");
        printTab();
        System.out.println("├─ProgramNameNode");
        programNode.getProgramNameNode().accept(this);
        printTab();
        System.out.println("├─BlockNode");
        programNode.getBlockNode().accept(this);
        printTab();
        System.out.println("└─CompoundStatementNode");
        programNode.getCompoundStatementNode().accept(this);
    }
 
	public void visit(ProgramNameNode programNameNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+programNameNode.getToken().getLexical()+"\"");
        indent--;
    }
 
    public void visit(BlockNode blockNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("├─VariableDeclarationNode");
        blockNode.getVariableDeclarationNode().accept(this);
        printTab();
        System.out.println("└─SubprogramDeclarationSequenceNode");
        blockNode.getSubprogramDeclarationSequenceNode().accept(this);
        indent--;
    }
 
    
    public void visit(VariableDeclarationNode variableDeclarationNode) throws SemanticException {
        indent++;
        if (variableDeclarationNode.getVariableDeclarationSequenceNode() != null) {
            printTab();
            System.out.println("└─VariableDeclarationSequenceNode");
            variableDeclarationNode.getVariableDeclarationSequenceNode().accept(this);
        }
        indent--;
    }
 
    
    public void visit(VariableDeclarationSequenceNode variableDeclarationSequenceNode) throws SemanticException {
        indent++;
        List<VariableNameSequenceNode> variableNameSequenceNodes = variableDeclarationSequenceNode.getVariableNameSequenceNodes();
        List<TypeNode> typeNodes = variableDeclarationSequenceNode.getTypeNodes();
        for (int i = 0; i < variableNameSequenceNodes.size()-1; i++) {
            printTab();
            System.out.println("├─VariableNameSequenceNode");
            variableNameSequenceNodes.get(i).accept(this);
            printTab();
            System.out.println("├─TypeNode");
            typeNodes.get(i).accept(this);
        }
        printTab();
        System.out.println("├─VariableNameSequenceNode");
        variableNameSequenceNodes.get(variableNameSequenceNodes.size()-1).accept(this);
        printTab();
        System.out.println("└─TypeNode");
        typeNodes.get(typeNodes.size()-1).accept(this);
        indent--;
    }
 
    
    public void visit(VariableNameSequenceNode variableNameSequenceNode) throws SemanticException {
        indent++;
        List<VariableNameNode> variableNameNodes = variableNameSequenceNode.getVariableNameNodes();
        for (int i = 0; i < variableNameNodes.size()-1; i++) {
            printTab();
            System.out.println("├─VariableNameNode");
            variableNameNodes.get(i).accept(this);
        }
        printTab();
        System.out.println("└─VariableNameNode");
        variableNameNodes.get(variableNameNodes.size()-1).accept(this);
        indent--;
    }
 
	
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+variableNameNode.getToken().getLexical()+"\"");
        indent--;
	}
 
    
    public void visit(TypeNode typeNode) throws SemanticException {
        indent++;
        printTab();
        StandardTypeNode standardTypeNode = typeNode.getStandardTypeNode();
        ArrayTypeNode arrayTypeNode = typeNode.getArrayTypeNode();
        if (standardTypeNode != null) {
            System.out.println("└─StandardTypeNode");
            standardTypeNode.accept(this);
        } else {
            System.out.println("└─ArrayTypeNode");
            arrayTypeNode.accept(this);
        }
        indent--;
    }
 
	
	public void visit(StandardTypeNode standardTypeNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+standardTypeNode.getToken().getLexical()+"\"");
        indent--;
    }
 
    
    public void visit(ArrayTypeNode arrayTypeNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("├─IndexMinValueNode");
        arrayTypeNode.getIndexMinValueNode().accept(this);
        printTab();
        System.out.println("├─IndexMaxValueNode");
        arrayTypeNode.getIndexMaxValueNode().accept(this);
        printTab();
        System.out.println("└─StandardTypeNode");
        arrayTypeNode.getStandardTypeNode().accept(this);
        indent--;
    }
 
    
    public void visit(IndexMinValueNode indexMinValueNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─IntegerNode");
        indexMinValueNode.getIntegerNode().accept(this);
        indent--;
    }
 
    
    public void visit(IndexMaxValueNode indexMaxValueNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─IntegerNode");
        indexMaxValueNode.getIntegerNode().accept(this);
        indent--;
    }
 
    
    public void visit(IntegerNode integerNode) throws SemanticException {
        indent++;
        SignNode signNode = integerNode.getSignNode();
        if (signNode != null) {
            printTab();
            System.out.println("├─SignNode");
            signNode.accept(this);
        }
        printTab();
        System.out.println("└─\""+integerNode.getToken().getLexical()+"\"");
        indent--;
    }
 
	
	public void visit(SignNode signNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+signNode.getToken().getLexical()+"\"");
        indent--;
	}
 
    
    public void visit(SubprogramDeclarationSequenceNode subprogramDeclarationSequenceNode) throws SemanticException {
        indent++;
        List<SubprogramDeclarationNode> subprogramDeclarationNodes = subprogramDeclarationSequenceNode.getSubprogramDeclarationNodes();
        if (subprogramDeclarationNodes.size() > 0) {
            for (int i = 0; i < subprogramDeclarationNodes.size()-1; i++) {
                printTab();
                System.out.println("├─SubprogramDeclarationNode");
                subprogramDeclarationNodes.get(i).accept(this);
            }
            printTab();
            System.out.println("└─SubprogramDeclarationNode");
            subprogramDeclarationNodes.get(subprogramDeclarationNodes.size()-1).accept(this);
        }
        indent--;
    }
 
    
    public void visit(SubprogramDeclarationNode subprogramDeclarationNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("├─SubprogramHeadNode");
        subprogramDeclarationNode.getSubprogramHeadNode().accept(this);
        printTab();
        System.out.println("├─VariableDeclarationNode");
        subprogramDeclarationNode.getVariableDeclarationNode().accept(this);
        printTab();
        System.out.println("└─CompoundStatementNode");
        subprogramDeclarationNode.getCompoundStatementNode().accept(this);
        indent--;
    }
 
    
    public void visit(SubprogramHeadNode subprogramHeadNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("├─ProcedureNameNode");
        subprogramHeadNode.getProcedureNameNode().accept(this);
        printTab();
        System.out.println("└─FormalParameterNode");
        subprogramHeadNode.getFormalParameterNode().accept(this);
        indent--;
    }
 
	
	public void visit(ProcedureNameNode procedureNameNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+procedureNameNode.getToken().getLexical()+"\"");
        indent--;
	}
 
    
    public void visit(FormalParameterNode formalParameterNode) throws SemanticException {
        indent++;
        FormalParameterSequenceNode formalParameterSequenceNode = formalParameterNode.getFormalParameterSequenceNode();
        if (formalParameterSequenceNode != null) {
            printTab();
            System.out.println("└─FormalParameterSequenceNode");
            formalParameterSequenceNode.accept(this);
        }
        indent--;
    }
 
    
    public void visit(FormalParameterSequenceNode formalParameterSequenceNode) throws SemanticException {
        indent++;
        List<FormalParameterNameSequenceNode> formalParameterNameSequenceNodes = formalParameterSequenceNode.getFormalParameterNameSequenceNodes();
        List<StandardTypeNode> standardTypeNodes = formalParameterSequenceNode.getStandardTypeNodes();
        for (int i = 0; i < formalParameterNameSequenceNodes.size()-1; i++) {
            printTab();
            System.out.println("├─FormalParameterNameSequenceNode");
            formalParameterNameSequenceNodes.get(i).accept(this);
            printTab();
            System.out.println("├─StandardTypeNode");
            standardTypeNodes.get(i).accept(this);
        }
        printTab();
        System.out.println("├─FormalParameterNameSequenceNode");
        formalParameterNameSequenceNodes.get(formalParameterNameSequenceNodes.size()-1).accept(this);
        printTab();
        System.out.println("└─StandardTypeNode");
        standardTypeNodes.get(standardTypeNodes.size()-1).accept(this);
        indent--;
    }
 
    
    public void visit(FormalParameterNameSequenceNode formalParameterNameSequenceNode) throws SemanticException {
        indent++;
        List<FormalParameterNameNode> formalParameterNameNodes = formalParameterNameSequenceNode.getFormalParameterNameNodes();
        for (int i = 0; i < formalParameterNameNodes.size()-1; i++) {
            printTab();
            System.out.println("├─FormalParameterNameNode");
            formalParameterNameNodes.get(i).accept(this);
        }
        printTab();
        System.out.println("└─FormalParameterNameNode");
        formalParameterNameNodes.get(formalParameterNameNodes.size()-1).accept(this);
        indent--;
    }
 
	
	public void visit(FormalParameterNameNode formalParameterNameNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+formalParameterNameNode.getToken().getLexical()+"\"");
        indent--;
	}
 
    
    public void visit(CompoundStatementNode compoundStatementNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─StatementSequenceNode");
        compoundStatementNode.getStatementSequenceNode().accept(this);
        indent--;
    }
 
    
    public void visit(StatementSequenceNode statementSequenceNode) throws SemanticException {
        indent++;
        List<StatementNode> statementNodes = statementSequenceNode.getStatementNodes();
        for (int i = 0; i < statementNodes.size()-1; i++) {
            printTab();
            System.out.println("├─StatementNode");
            statementNodes.get(i).accept(this);
        }
        printTab();
        System.out.println("└─StatementNode");
        statementNodes.get(statementNodes.size()-1).accept(this);
        indent--;
    }
 
    
    public void visit(StatementNode statementNode) throws SemanticException {
        indent++;
        Token token = statementNode.getToken();
        if (token == null) {
            printTab();
            System.out.println("└─BasicStatementNode");
            statementNode.getBasicStatementNode().accept(this);
        } else {
            printTab();
            System.out.println("├─\""+token.getLexical()+"\"");
            printTab();
            System.out.println("├─ExpressionNode");
            statementNode.getExpressionNode().accept(this);
            CompoundStatementNode compoundStatementNode2 = statementNode.getCompoundStatementNode2();
            if (compoundStatementNode2 != null) {
                printTab();
                System.out.println("├─CompoundStatementNode");
                statementNode.getCompoundStatementNode1().accept(this);
                printTab();
                System.out.println("└─CompoundStatementNode");
                compoundStatementNode2.accept(this);
            } else {
                printTab();
                System.out.println("└─CompoundStatementNode");
                statementNode.getCompoundStatementNode1().accept(this);
            }
        }
        indent--;
    }
 
    public void visit(BasicStatementNode basicStatementNode) throws SemanticException {
        indent++;
        AssignmentStatementNode assignmentStatementNode = basicStatementNode.getAssignmentStatementNode();
        ProcedureCallStatementNode procedureCallStatementNode = basicStatementNode.getProcedureCallStatementNode();
        InputOutputStatementNode inputOutputStatementNode = basicStatementNode.getInputOutputStatementNode();
        CompoundStatementNode compoundStatementNode = basicStatementNode.getCompoundStatementNode();
        if (assignmentStatementNode != null) {
            printTab();
            System.out.println("└─AssignmentStatementNode");
            assignmentStatementNode.accept(this);
        } else if (procedureCallStatementNode != null) {
            printTab();
            System.out.println("└─ProcedureCallStatementNode");
            procedureCallStatementNode.accept(this);
        } else if (inputOutputStatementNode != null) {
            printTab();
            System.out.println("└─InputOutputStatementNode");
            inputOutputStatementNode.accept(this);
        } else {
            printTab();
            System.out.println("└─CompoundStatementNode");
            compoundStatementNode.accept(this);
        }
        indent--;
    }
 
    
    public void visit(AssignmentStatementNode assignmentStatementNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("├─LeftHandSideNode");
        assignmentStatementNode.getLeftHandSideNode().accept(this);
        printTab();
        System.out.println("├─\""+assignmentStatementNode.getToken().getLexical()+"\"");
        printTab();
        System.out.println("└─ExpressionNode");
        assignmentStatementNode.getExpressionNode().accept(this);
        indent--;
    }
 
    
    public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─VariableNode");
        leftHandSideNode.getVariableNode().accept(this);
        indent--;
    }
 
    
    public void visit(VariableNode variableNode) throws SemanticException {
        indent++;
        PureVariableNode pureVariableNode = variableNode.getPureVariableNode();
        IndexedVariableNode indexedVariableNode = variableNode.getIndexedVariableNode();
        if (pureVariableNode != null) {
            printTab();
            System.out.println("└─PureVariableNode");
            pureVariableNode.accept(this);
        } else {
            printTab();
            System.out.println("└─IndexedVariableNode");
            indexedVariableNode.accept(this);
        }
        indent--;
    }
 
    
    public void visit(PureVariableNode pureVariableNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─VariableNode");
        pureVariableNode.getVariableNameNode().accept(this);
        indent--;
    }
 
    
    public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("├─VariableNode");
        indexedVariableNode.getVariableNameNode().accept(this);
        printTab();
        System.out.println("└─IndexNode");
        indexedVariableNode.getIndexNode().accept(this);
        indent--;
    }
 
    
    public void visit(IndexNode indexNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─ExpressionNode");
        indexNode.getExpressionNode().accept(this);
        indent--;
    }
 
    
    public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
        indent++;
        ExpressionSequenceNode expressionSequenceNode = procedureCallStatementNode.getExpressionSequenceNode();
        if (expressionSequenceNode != null) {
            printTab();
            System.out.println("├─ProcedureNameNode");
            procedureCallStatementNode.getProcedureNameNode().accept(this);
            printTab();
            System.out.println("└─ExpressionSequenceNode");
            expressionSequenceNode.accept(this);
        } else {
            printTab();
            System.out.println("└─ProcedureNameNode");
            procedureCallStatementNode.getProcedureNameNode().accept(this);
        }
        indent--;
    }
 
    
    public void visit(ExpressionSequenceNode expressionSequenceNode) throws SemanticException {
        indent++;
        List<ExpressionNode> expressionNodes = expressionSequenceNode.getExpressionNodes();
        for (int i = 0; i < expressionNodes.size()-1; i++) {
            printTab();
            System.out.println("├─ExpressionNode");
            expressionNodes.get(i).accept(this);
        }
        printTab();
        System.out.println("└─ExpressionNode");
        expressionNodes.get(expressionNodes.size()-1).accept(this);
        indent--;
    }
 
    
    public void visit(ExpressionNode expressionNode) throws SemanticException {
        indent++;
        RelationalOperatorNode relationalOperatorNode = expressionNode.getRelationalOperatorNode();
        if (relationalOperatorNode != null) {
            printTab();
            System.out.println("├─SimpleExpressionNode");
            expressionNode.getLeftSimpleExpressionNode().accept(this);
            printTab();
            System.out.println("├─RelationalOperatorNode");
            relationalOperatorNode.accept(this);
            printTab();
            System.out.println("└─SimpleExpressionNode");
            expressionNode.getRightSimpleExpressionNode().accept(this);
        } else {
            printTab();
            System.out.println("└─SimpleExpressionNode");
            expressionNode.getLeftSimpleExpressionNode().accept(this);
        }
        indent--;
    }
 
    
    public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
        indent++;
        SignNode signNode = simpleExpressionNode.getSignNode();
        if (signNode != null) {
            printTab();
            System.out.println("├─SignNode");
            signNode.accept(this);
        }
        List<AdditiveOperatorNode> additiveOperatorNodes = simpleExpressionNode.getAdditiveOperatorNodes();
        List<TermNode> termNodes = simpleExpressionNode.getTermNodes();
        if (additiveOperatorNodes.size() > 0) {
            printTab();
            System.out.println("├─TermNode");
            simpleExpressionNode.getLeftTermNode().accept(this);
            for (int i = 0; i < additiveOperatorNodes.size() - 1; i++) {
                printTab();
                System.out.println("├─AdditiveOperatorNode");
                additiveOperatorNodes.get(i).accept(this);
                printTab();
                System.out.println("├─TermNode");
                termNodes.get(i).accept(this);
            }
            printTab();
            System.out.println("├─AdditiveOperatorNode");
            additiveOperatorNodes.get(additiveOperatorNodes.size()-1).accept(this);
            printTab();
            System.out.println("└─TermNode");
            termNodes.get(termNodes.size()-1).accept(this);
        } else {
            printTab();
            System.out.println("└─TermNode");
            simpleExpressionNode.getLeftTermNode().accept(this);
        }
        indent--;
    }
 
    
    public void visit(TermNode termNode) throws SemanticException {
        indent++;
        List<MultiplicativeOperatorNode> multiplicativeOperatorNodes = termNode.getMultiplicativeOperatorNodes();
        List<FactorNode> factorNodes = termNode.getFactorNodes();
        if (multiplicativeOperatorNodes.size() > 0) {
            printTab();
            System.out.println("├─FactorNode");
            termNode.getLeftFactorNode().accept(this);
            for (int i = 0; i < multiplicativeOperatorNodes.size()-1; i++) {
                printTab();
                System.out.println("├─MultiplicativeOperatorNode");
                multiplicativeOperatorNodes.get(i).accept(this);
                printTab();
                System.out.println("├─FactorNode");
                factorNodes.get(i).accept(this);
            }
            printTab();
            System.out.println("├─MultiplicativeOperatorNode");
            multiplicativeOperatorNodes.get(multiplicativeOperatorNodes.size()-1).accept(this);
            printTab();
            System.out.println("└─FactorNode");
            factorNodes.get(factorNodes.size()-1).accept(this);
        } else {
            printTab();
            System.out.println("└─FactorNode");
            termNode.getLeftFactorNode().accept(this);
        }
        indent--;
    }
 
    
    public void visit(FactorNode factorNode) throws SemanticException {
        indent++;
        VariableNode variableNode = factorNode.getVariableNode();
        ConstantNode constantNode = factorNode.getConstantNode();
        ExpressionNode expressionNode = factorNode.getExpressionNode();
        FactorNode factorNode2 = factorNode.getFactorNode();
        if (variableNode != null) {
            printTab();
            System.out.println("└─VariableNode");
            variableNode.accept(this);
        } else if (constantNode != null) {
            printTab();
            System.out.println("└─ConstantNode");
            constantNode.accept(this);
        } else if (expressionNode != null) {
            printTab();
            System.out.println("└─ExpressionNode");
            expressionNode.accept(this);
        } else {
            printTab();
            System.out.println("├─\""+factorNode.getToken().getLexical()+"\"");
            printTab();
            System.out.println("└─FactorNode");
            factorNode2.accept(this);
        }
        indent--;
    }
 
	
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+relationalOperatorNode.getToken().getLexical()+"\"");
        indent--;
	}
 
	
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+additiveOperatorNode.getToken().getLexical()+"\"");
        indent--;
	}
 
	
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+multiplicativeOperatorNode.getToken().getLexical()+"\"");
        indent--;
	}
 
    
    public void visit(InputOutputStatementNode inputOutputStatementNode) throws SemanticException {
        indent++;
        printTab();
        if (inputOutputStatementNode.getVariableSequenceNodes().size() > 0) {
            System.out.println("├─\""+inputOutputStatementNode.getToken().getLexical()+"\"");
            for (int i = 0; i < inputOutputStatementNode.getVariableSequenceNodes().size()-1; i++) {
                printTab();
                System.out.println("├─VariableSequenceNode");
                inputOutputStatementNode.getVariableSequenceNodes().get(i).accept(this);
            }
            printTab();
            System.out.println("└─VariableSequenceNode");
            inputOutputStatementNode.getVariableSequenceNodes().get(inputOutputStatementNode.getVariableSequenceNodes().size()-1).accept(this);
        } else if (inputOutputStatementNode.getExpressionSequenceNodes().size() > 0) {
            System.out.println("├─\""+inputOutputStatementNode.getToken().getLexical()+"\"");
            for (int i = 0; i < inputOutputStatementNode.getExpressionSequenceNodes().size()-1; i++) {
                printTab();
                System.out.println("├─ExpressionSequenceNode");
                inputOutputStatementNode.getExpressionSequenceNodes().get(i).accept(this);
            }
            printTab();
            System.out.println("└─ExpressionSequenceNode");
            inputOutputStatementNode.getExpressionSequenceNodes().get(inputOutputStatementNode.getExpressionSequenceNodes().size()-1).accept(this);
        } else {
            System.out.println("└─\""+inputOutputStatementNode.getToken().getLexical()+"\"");
        }
        indent--;
    }
 
    
    public void visit(VariableSequenceNode variableSequenceNode) throws SemanticException {
        indent++;
        List<VariableNode> variableNodes = variableSequenceNode.getVariableNodes();
        for (int i = 0; i < variableNodes.size()-1; i++) {
            printTab();
            System.out.println("├─VariableNode");
            variableNodes.get(i).accept(this);
        }
        printTab();
        System.out.println("└─VariableNode");
        variableNodes.get(variableNodes.size()-1).accept(this);
        indent--;
    }
 
	
	public void visit(ConstantNode constantNode) throws SemanticException {
        indent++;
        printTab();
        System.out.println("└─\""+constantNode.getToken().getLexical()+"\"");
        indent--;
	}
}