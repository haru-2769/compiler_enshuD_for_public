package enshud.s2.parser;

import java.util.List;
import java.util.ArrayList;

public class SubprogramDeclarationSequenceNode extends AstNode {
    private SubprogramDeclarationNode subprogramDeclarationNode;
    private List<SubprogramDeclarationNode> subprogramDeclarationNodes;

    public SubprogramDeclarationSequenceNode() {
        this.subprogramDeclarationNode = null;
        this.subprogramDeclarationNodes = new ArrayList<>();
    }

    public void parse(Context context) throws SyntaxException {
        while (context.equalsAny(0, "SPROCEDURE")) {
            this.subprogramDeclarationNode = new SubprogramDeclarationNode();
            this.subprogramDeclarationNodes.add(this.subprogramDeclarationNode);
            this.subprogramDeclarationNode.parse(context);
            context.checkTerminalSymbol("SSEMICOLON");
        }
    }

    public List<SubprogramDeclarationNode> getSubprogramDeclarationNodes() {
        return this.subprogramDeclarationNodes;
    }
}