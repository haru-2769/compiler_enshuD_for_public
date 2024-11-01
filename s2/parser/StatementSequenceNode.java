package enshud.s2.parser;

public class StatementSequenceNode extends NonTerminalNode{
    public void parse(Context context) throws SyntaxException {
        do {
            StatementNode statementNode = new StatementNode();
            addChild(statementNode);
            statementNode.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("SSEMICOLON")));
        } while (context.equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN", "SIF", "SWHILE"));
    }
}