package enshud.s3.checker;

import java.util.ArrayList;
import java.util.List;

public class NonTerminalNode implements AstNode {
    private final List<AstNode> children = new ArrayList<>();

    public void addChild(AstNode child) {
        this.children.add(child);
    }

    public List<AstNode> getChildren() {
        return this.children;
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class ProgramNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class BlockNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class VariableDeclarationNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class VariableDeclarationSequenceNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class VariableNameSequenceNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class TypeNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class ArrayTypeNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class IndexMinValueNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class IndexMaxValueNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class IntegerNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class SubprogramDeclarationSequenceNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class SubprogramDeclarationNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class SubprogramHeadNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class FormalParameterNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class FormalParameterSequenceNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class FormalParameterNameSequenceNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class CompoundStatementNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class StatementSequenceNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class StatementNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class BasicStatementNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class AssignmentStatementNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class LeftHandSideNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class VariableNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class PureVariableNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class IndexedVariableNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class IndexNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class ProcedureCallStatementNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class ExpressionSequenceNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class ExpressionNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class SimpleExpressionNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class TermNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class FactorNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class InputOutputStatementNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}
class VariableSequenceNode extends NonTerminalNode {
    @Override
    public void accept(Visitor visitor) {
    visitor.visit(this);
    }   
}