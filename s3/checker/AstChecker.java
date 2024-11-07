package enshud.s3.checker;
 
import java.util.HashSet;
import java.util.Set;
 
public class AstChecker extends Visitor {
<<<<<<< HEAD
    private int level = 0; 
    private Set<String> declaredProcedureNames = new HashSet<>();
 
    private void printIndent() {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
    }
 
=======
>>>>>>> develop
    public void visit(TerminalNode terminalNode) throws SemanticException {
    }
    
    public void visit(ProgramNode programNode) throws SemanticException {
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
<<<<<<< HEAD
        printIndent();
        System.out.println("SubprogramHeadNode: ");
        level++;
        for (AstNode child : subprogramHeadNode.getChildren()) {
            if (child instanceof ProcedureNameNode) {
                ProcedureNameNode procedureNameNode = (ProcedureNameNode) child;
                Token token = ((TerminalNode) procedureNameNode.getChildren().get(0)).getToken();
                String procedureName = token.getLexical();
                if (!declaredProcedureNames.add(procedureName)) {
                    throw new SemanticException(token);
                }
            } else {
                child.accept(this);
            }   
        }
        level--;
=======
>>>>>>> develop
    }
 
	
	public void visit(ProcedureNameNode procedureNameNode) throws SemanticException {
<<<<<<< HEAD
        printIndent();
        System.out.println("ProcedureNameNode: ");
        level++;
        for (AstNode child : procedureNameNode.getChildren()) {
            child.accept(this);
        }
        level--;
        Token token = ((TerminalNode) procedureNameNode.getChildren().get(0)).getToken();
=======
>>>>>>> develop
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