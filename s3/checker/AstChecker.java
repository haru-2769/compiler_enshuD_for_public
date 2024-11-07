package enshud.s3.checker;
 
import java.util.HashSet;
import java.util.Set;
 
public class AstChecker extends Visitor {
    private Set<String> programNames;
    private Set<String> procedureNames;
    
    public AstChecker() {
        this.programNames = new HashSet<>();
        this.procedureNames = new HashSet<>();
    }
    
    public void visit(ProgramNode programNode) throws SemanticException {
        Token programName = programNode.getProgramNameNode().getToken();
        if (programNames.add(programName.getLexical()) == false) {
            throw new SemanticException(programName);
        }
        programNode.getBlockNode().accept(this);
        programNode.getCompoundStatementNode().accept(this);
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
        //TODO
    }
 
    
    public void visit(VariableNameSequenceNode variableNameSequenceNode) throws SemanticException {
        //TODO
    }
 
	
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
        //TODO
	}
 
    
    public void visit(TypeNode typeNode) throws SemanticException {
        //TODO
    }
 
	
	public void visit(StandardTypeNode standardTypeNode) throws SemanticException {
        //TODO
	}
 
    
    public void visit(ArrayTypeNode arrayTypeNode) throws SemanticException {
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
        subprogramDeclarationNode.getSubprogramHeadNode().accept(this);
        subprogramDeclarationNode.getVariableDeclarationNode().accept(this);
        subprogramDeclarationNode.getCompoundStatementNode().accept(this);
    }
 
    
    public void visit(SubprogramHeadNode subprogramHeadNode) throws SemanticException {
        Token procedureName = subprogramHeadNode.getProcedureNameNode().getToken();
        if (procedureNames.add(procedureName.getLexical()) == false) {
            throw new SemanticException(procedureName);
        }
        subprogramHeadNode.getFormalParameterNode().accept(this);
    }
 
	
	public void visit(ProcedureNameNode procedureNameNode) throws SemanticException {
	}
 
    
    public void visit(FormalParameterNode formalParameterNode) throws SemanticException {
    }
 
    
    public void visit(FormalParameterSequenceNode formalParameterSequenceNode) throws SemanticException {
    }
 
    
    public void visit(FormalParameterNameSequenceNode formalParameterNameSequenceNode) throws SemanticException {
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
    }
 
    
    public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
    }
 
    
    public void visit(VariableNode variableNode) throws SemanticException {
    }
 
    
    public void visit(PureVariableNode pureVariableNode) throws SemanticException {
    }
 
    
    public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {

    }
 
    
    public void visit(IndexNode indexNode) throws SemanticException {
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
    }
 
    
    public void visit(ExpressionNode expressionNode) throws SemanticException {
    }
 
    
    public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
    }
 
    
    public void visit(TermNode termNode) throws SemanticException {
    }
 
    
    public void visit(FactorNode factorNode) throws SemanticException {
    }
 
	
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
	}
 
	
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
	}
 
	
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
	}
 
    
    public void visit(InputOutputStatementNode inputOutputStatementNode) throws SemanticException {
    }
 
    
    public void visit(VariableSequenceNode variableSequenceNode) throws SemanticException {
    }
 
	
	public void visit(ConstantNode constantNode) throws SemanticException {
	}
}