package enshud.s3.checker;

import java.util.List;
import java.util.ArrayList;

public class VariableDeclarationSequenceNode extends AstNode {
    private VariableNameSequenceNode variableNameSequenceNode;
    private TypeNode typeNode;
    private List<VariableNameSequenceNode> variableNameSequenceNodes;
    private List<TypeNode> typeNodes;

    public VariableDeclarationSequenceNode() throws SyntaxException {
        this.variableNameSequenceNode = null;
        this.typeNode = null;
        this.variableNameSequenceNodes = new ArrayList<>();
        this.typeNodes = new ArrayList<>();
    }

    public void parse(Context context) throws SyntaxException {
        do {
            this.variableNameSequenceNode = new VariableNameSequenceNode();
            this.variableNameSequenceNodes.add(this.variableNameSequenceNode);
            this.variableNameSequenceNode.parse(context);
            context.checkTerminalSymbol("SCOLON");
            this.typeNode = new TypeNode();
            this.typeNodes.add(this.typeNode);
            this.typeNode.parse(context);
            context.checkTerminalSymbol("SSEMICOLON");
        } while (context.equalsAny(0, "SIDENTIFIER"));
    }

    public List<VariableNameSequenceNode> getVariableNameSequenceNodes() {
        return this.variableNameSequenceNodes;
    }

    public List<TypeNode> getTypeNodes() {
        return this.typeNodes;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}