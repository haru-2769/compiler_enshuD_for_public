package enshud.s4.compiler;

import java.util.List;
import java.util.ArrayList;

public class SubProgramDeclarationSequenceNode extends AstNode {
    private List<SubProgramDeclarationNode> subProgramDeclarationNodes;

    public SubProgramDeclarationSequenceNode() throws SyntaxException {
        this.subProgramDeclarationNodes = new ArrayList<>();
    }

    @Override
    public void parse(Context context) throws SyntaxException {
        SubProgramDeclarationNode subProgramDeclarationNode;
        while (context.equalsAny(0, "SPROCEDURE")) {
            subProgramDeclarationNode = new SubProgramDeclarationNode();
            this.subProgramDeclarationNodes.add(subProgramDeclarationNode);
            subProgramDeclarationNode.parse(context);
            context.checkTerminalSymbol("SSEMICOLON");
        }
    }

    public List<SubProgramDeclarationNode> getSubProgramDeclarationNodes() {
        return this.subProgramDeclarationNodes;
    }
    
    @Override
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}