package enshud.s2.parser;

import java.util.List;
import java.util.ArrayList;

public class VariableDeclarationSequenceNode extends AstNode {
    private List<VariableNameSequenceNode> variableNameSequenceNodes;
    private List<TypeNode> typeNodes;

    public VariableDeclarationSequenceNode() throws SyntaxException {
        this.variableNameSequenceNodes = new ArrayList<>();
        this.typeNodes = new ArrayList<>();
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        VariableNameSequenceNode variableNameSequenceNode;
        TypeNode typeNode;
        do {
            variableNameSequenceNode = new VariableNameSequenceNode();
            this.variableNameSequenceNodes.add(variableNameSequenceNode);
            variableNameSequenceNode.parse(context);
            context.checkTerminalSymbol("SCOLON");
            typeNode = new TypeNode();
            this.typeNodes.add(typeNode);
            typeNode.parse(context);
            context.checkTerminalSymbol("SSEMICOLON");
        } while (context.equalsAny(0, "SIDENTIFIER"));
    }

    public List<VariableNameSequenceNode> getVariableNameSequenceNodes() {
        return this.variableNameSequenceNodes;
    }

    public List<TypeNode> getTypeNodes() {
        return this.typeNodes;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}