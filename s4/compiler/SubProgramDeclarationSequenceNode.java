package enshud.s4.compiler;

import java.util.List;
import java.util.ArrayList;

public class SubProgramDeclarationSequenceNode extends AstNode {
    private SubProgramDeclarationNode subProgramDeclarationNode;
    private List<SubProgramDeclarationNode> subProgramDeclarationNodes;

    public SubProgramDeclarationSequenceNode() throws SyntaxException {
        this.subProgramDeclarationNode = null;
        this.subProgramDeclarationNodes = new ArrayList<>();
    }

    public void parse(Context context) throws SyntaxException {
        while (context.equalsAny(0, "SPROCEDURE")) {
            this.subProgramDeclarationNode = new SubProgramDeclarationNode();
            this.subProgramDeclarationNodes.add(this.subProgramDeclarationNode);
            this.subProgramDeclarationNode.parse(context);
            context.checkTerminalSymbol("SSEMICOLON");
        }
    }

    public List<SubProgramDeclarationNode> getSubProgramDeclarationNodes() {
        return this.subProgramDeclarationNodes;
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}