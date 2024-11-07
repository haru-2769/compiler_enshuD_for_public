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
	}
 
    
    public void visit(BlockNode blockNode) throws SemanticException {
    }
 
    
    public void visit(VariableDeclarationNode variableDeclarationNode) throws SemanticException {
    }
 
    
    public void visit(VariableDeclarationSequenceNode variableDeclarationSequenceNode) throws SemanticException {
    }
 
    
    public void visit(VariableNameSequenceNode variableNameSequenceNode) throws SemanticException {
    }
 
	
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
	}
 
    
    public void visit(TypeNode typeNode) throws SemanticException {
    }
 
	
	public void visit(StandardTypeNode standardTypeNode) throws SemanticException {
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
    }
 
    
    public void visit(SubprogramDeclarationNode subprogramDeclarationNode) throws SemanticException {
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
    }
 
    
    public void visit(StatementNode statementNode) throws SemanticException {
    }
 
    
    public void visit(BasicStatementNode basicStatementNode) throws SemanticException {
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