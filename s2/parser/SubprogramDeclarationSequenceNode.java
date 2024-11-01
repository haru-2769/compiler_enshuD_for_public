package enshud.s2.parser;

public class SubprogramDeclarationSequenceNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        while (context.equalsAny(0, "SPROCEDURE")) {
            SubprogramDeclarationNode subprogramDeclarationNode = new SubprogramDeclarationNode();
            addChild(subprogramDeclarationNode);
            subprogramDeclarationNode.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("SSEMICOLON")));
        }
    }
}