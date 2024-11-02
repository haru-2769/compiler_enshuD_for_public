package enshud.s3.checker;

import java.util.HashSet;
import java.util.Set;

public class AstChecker extends Visitor {
    private int level = 0; 
    private Set<String> declaredProcedureNames = new HashSet<>();

    //             if (child instanceof VariableNameNode) {
    //     VariableNameNode varNode = (VariableNameNode) child;
    //     String varName = varNode.getName(); // 仮定: VariableNameNode には変数名を取得するメソッド getName() がある
    //     declareVariable(varName); // 変数宣言を登録して重複をチェック
    // 

    private void printIndent() {
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
    }

    public void visit(TerminalNode terminalNode) throws SemanticException {
        printIndent();
        System.out.println(terminalNode.getToken().getLexical());
    }
    
    public void visit(ProgramNode programNode) throws SemanticException {
        printIndent();
        System.out.println("ProgramNode: ");
        level++;
        for (AstNode child : programNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

	
	public void visit(ProgramNameNode programNameNode) throws SemanticException {
        printIndent();
        System.out.println("ProgramNameNode: ");
        level++;
        for (AstNode child : programNameNode.getChildren()) {
            child.accept(this);
        }
        level--;	
	}

    
    public void visit(BlockNode blockNode) throws SemanticException {
        printIndent();
        System.out.println("BlockNode: ");
        level++;
        for (AstNode child : blockNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(VariableDeclarationNode variableDeclarationNode) throws SemanticException {
        printIndent();
        System.out.println("VariableDeclarationNode: ");
        level++;
        for (AstNode child : variableDeclarationNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(VariableDeclarationSequenceNode variableDeclarationSequenceNode) throws SemanticException {
        printIndent();
        System.out.println("VariableDeclarationSequenceNode: ");
        level++;
        for (AstNode child : variableDeclarationSequenceNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(VariableNameSequenceNode variableNameSequenceNode) throws SemanticException {
        printIndent();
        System.out.println("VariableNameSequenceNode: ");
        level++;
        for (AstNode child : variableNameSequenceNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

	
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
        printIndent();
        System.out.println("VariableNameNode: ");
        level++;
        for (AstNode child : variableNameNode.getChildren()) {
            child.accept(this);
        }
        level--;
	}

    
    public void visit(TypeNode typeNode) throws SemanticException {
        printIndent();
        System.out.println("TypeNode: ");
        level++;
        for (AstNode child : typeNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

	
	public void visit(StandardTypeNode standardTypeNode) throws SemanticException {
        printIndent();
        System.out.println("StandardTypeNode: ");
        level++;
        for (AstNode child : standardTypeNode.getChildren()) {
            child.accept(this);
        }
        level--;
	}

    
    public void visit(ArrayTypeNode arrayTypeNode) throws SemanticException {
        printIndent();
        System.out.println("ArrayTypeNode: ");
        level++;
        for (AstNode child : arrayTypeNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(IndexMinValueNode indexMinValueNode) throws SemanticException {
        printIndent();
        System.out.println("IndexMinValueNode: ");
        level++;
        for (AstNode child : indexMinValueNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(IndexMaxValueNode indexMaxValueNode) throws SemanticException {
        printIndent();
        System.out.println("IndexMaxValueNode: ");
        level++;
        for (AstNode child : indexMaxValueNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(IntegerNode integerNode) throws SemanticException {
        printIndent();
        System.out.println("IntegerNode: ");
        level++;
        for (AstNode child : integerNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

	
	public void visit(SignNode signNode) throws SemanticException {
        printIndent();
        System.out.println("SignNode: ");
        level++;
        for (AstNode child : signNode.getChildren()) {
            child.accept(this);
        }
        level--;
	}

    
    public void visit(SubprogramDeclarationSequenceNode subprogramDeclarationSequenceNode) throws SemanticException {
        printIndent();
        System.out.println("SubprogramDeclarationSequenceNode: ");
        level++;
        for (AstNode child : subprogramDeclarationSequenceNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(SubprogramDeclarationNode subprogramDeclarationNode) throws SemanticException {
        printIndent();
        System.out.println("SubprogramDeclarationNode: ");
        level++;
        for (AstNode child : subprogramDeclarationNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(SubprogramHeadNode subprogramHeadNode) throws SemanticException {
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
    }

	
	public void visit(ProcedureNameNode procedureNameNode) throws SemanticException {
        printIndent();
        System.out.println("ProcedureNameNode: ");
        level++;
        for (AstNode child : procedureNameNode.getChildren()) {
            child.accept(this);
        }
        level--;
	}

    
    public void visit(FormalParameterNode formalParameterNode) throws SemanticException {
        printIndent();
        System.out.println("FormalParameterNode: ");
        level++;
        for (AstNode child : formalParameterNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(FormalParameterSequenceNode formalParameterSequenceNode) throws SemanticException {
        printIndent();
        System.out.println("FormalParameterSequenceNode: ");
        level++;
        for (AstNode child : formalParameterSequenceNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(FormalParameterNameSequenceNode formalParameterNameSequenceNode) throws SemanticException {
        printIndent();
        System.out.println("FormalParameterNameSequenceNode: ");
        level++;
        for (AstNode child : formalParameterNameSequenceNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

	
	public void visit(FormalParameterNameNode formalParameterNameNode) throws SemanticException {
        printIndent();
        System.out.println("FormalParameterNameNode: ");
        level++;
        for (AstNode child : formalParameterNameNode.getChildren()) {
            child.accept(this);
        }
        level--;
	}

    
    public void visit(CompoundStatementNode compoundStatementNode) throws SemanticException {
        printIndent();
        System.out.println("CompoundStatementNode: ");
        level++;
        for (AstNode child : compoundStatementNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(StatementSequenceNode statementSequenceNode) throws SemanticException {
        printIndent();
        System.out.println("StatementSequenceNode: ");
        level++;
        for (AstNode child : statementSequenceNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(StatementNode statementNode) throws SemanticException {
        printIndent();
        System.out.println("StatementNode: ");
        level++;
        for (AstNode child : statementNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(BasicStatementNode basicStatementNode) throws SemanticException {
        printIndent();
        System.out.println("BasicStatementNode: ");
        level++;
        for (AstNode child : basicStatementNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(AssignmentStatementNode assignmentStatementNode) throws SemanticException {
        printIndent();
        System.out.println("AssignmentStatementNode: ");
        level++;
        for (AstNode child : assignmentStatementNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
        printIndent();
        System.out.println("LeftHandSideNode: ");
        level++;
        for (AstNode child : leftHandSideNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(VariableNode variableNode) throws SemanticException {
        printIndent();
        System.out.println("VariableNode: ");
        level++;
        for (AstNode child : variableNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(PureVariableNode pureVariableNode) throws SemanticException {
        printIndent();
        System.out.println("PureVariableNode: ");
        level++;
        for (AstNode child : pureVariableNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {
        printIndent();
        System.out.println("IndexedVariableNode: ");
        level++;
        for (AstNode child : indexedVariableNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(IndexNode indexNode) throws SemanticException {
        printIndent();
        System.out.println("IndexNode: ");
        level++;
        for (AstNode child : indexNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
        printIndent();
        System.out.println("ProcedureCallStatementNode: ");
        level++;
        for (AstNode child : procedureCallStatementNode.getChildren()) {
            if (child instanceof ProcedureNameNode) {
                ProcedureNameNode procedureNameNode = (ProcedureNameNode) child;
                Token token = ((TerminalNode) procedureNameNode.getChildren().get(0)).getToken();
                String procedureName = token.getLexical();
                if (declaredProcedureNames.add(procedureName)) {
                    throw new SemanticException(token);
                }
            } else {
                child.accept(this);
            } 
        }
        level--;
    }

    
    public void visit(ExpressionSequenceNode expressionSequenceNode) throws SemanticException {
        printIndent();
        System.out.println("ExpressionSequenceNode: ");
        level++;
        for (AstNode child : expressionSequenceNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(ExpressionNode expressionNode) throws SemanticException {
        printIndent();
        System.out.println("ExpressionNode: ");
        level++;
        for (AstNode child : expressionNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
        printIndent();
        System.out.println("SimpleExpressionNode: ");
        level++;
        for (AstNode child : simpleExpressionNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(TermNode termNode) throws SemanticException {
        printIndent();
        System.out.println("TermNode: ");
        level++;
        for (AstNode child : termNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(FactorNode factorNode) throws SemanticException {
        printIndent();
        System.out.println("FactorNode: ");
        level++;
        for (AstNode child : factorNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

	
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
        printIndent();
        System.out.println("RelationalOperatorNode: ");
        level++;
        for (AstNode child : relationalOperatorNode.getChildren()) {
            child.accept(this);
        }
        level--;
	}

	
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
        printIndent();
        System.out.println("AdditiveOperatorNode: ");
        level++;
        for (AstNode child : additiveOperatorNode.getChildren()) {
            child.accept(this);
        }
        level--;
	}

	
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
        printIndent();
        System.out.println("MultiplicativeOperatorNode: ");
        level++;
        for (AstNode child : multiplicativeOperatorNode.getChildren()) {
            child.accept(this);
        }
        level--;
	}

    
    public void visit(InputOutputStatementNode inputOutputStatementNode) throws SemanticException {
        printIndent();
        System.out.println("InputOutputStatementNode: ");
        level++;
        for (AstNode child : inputOutputStatementNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

    
    public void visit(VariableSequenceNode variableSequenceNode) throws SemanticException {
        printIndent();
        System.out.println("VariableSequenceNode: ");
        level++;
        for (AstNode child : variableSequenceNode.getChildren()) {
            child.accept(this);
        }
        level--;
    }

	
	public void visit(ConstantNode constantNode) throws SemanticException {
        printIndent();
        System.out.println("ConstantNode: ");
        level++;
        for (AstNode child : constantNode.getChildren()) {
            child.accept(this);
        }
        level--;
	}
}
