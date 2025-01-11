package enshud.s4.compiler;

import java.util.ArrayList;

public class AstCompiler extends Visitor {
    private ArrayList<String> caslCode;

    public AstCompiler() {
        caslCode = new ArrayList<>();
    }

    public void start(ProgramNode programNode) {
        visit(programNode);
    }

    public ArrayList<String> getCaslCode() {
        return caslCode;
    }

	public void visit(ProgramNode programNode) {
	}

	@Override
	public void visit(BlockNode blockNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(VariableDeclarationNode variableDeclarationNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(VariableDeclarationSequenceNode variableDeclarationSequenceNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(VariableNameSequenceNode variableNameSequenceNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(VariableNameNode variableNameNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(TypeNode typeNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(StandardTypeNode standardTypeNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(ArrayTypeNode arrayTypeNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(IndexMinValueNode indexMinValueNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(IndexMaxValueNode indexMaxValueNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(IntegerNode integerNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(SignNode signNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(SubProgramDeclarationSequenceNode subprogramDeclarationSequenceNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(SubProgramDeclarationNode subprogramDeclarationNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(SubProgramHeadNode subprogramHeadNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(ProcedureNameNode procedureNameNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(FormalParameterNode formalParameterNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(FormalParameterSequenceNode formalParameterSequenceNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(FormalParameterNameSequenceNode formalParameterNameSequenceNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(FormalParameterNameNode formalParameterNameNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(CompoundStatementNode compoundStatementNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(StatementNode statementNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}


	@Override
	public void visit(AssignmentStatementNode assignmentStatementNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(LeftHandSideNode leftHandSideNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(VariableNode variableNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(PureVariableNode pureVariableNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(IndexedVariableNode indexedVariableNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(IndexNode indexNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(ProcedureCallStatementNode procedureCallStatementNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}


	@Override
	public void visit(ExpressionNode expressionNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(SimpleExpressionNode simpleExpressionNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(TermNode termNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(FactorNode factorNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(RelationalOperatorNode relationalOperatorNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(AdditiveOperatorNode additiveOperatorNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(MultiplicativeOperatorNode multiplicativeOperatorNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}


	@Override
	public void visit(ConstantNode constantNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(IfNode ifNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(WhileNode whileNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(ReadlnNode readlnNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void visit(WritelnNode writelnNode) throws SemanticException {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
