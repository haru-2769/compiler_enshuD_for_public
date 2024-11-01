package enshud.s3.checker;

public class CompoundStatementNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SBEGIN")));
        StatementSequenceNode statementSequenceNode = new StatementSequenceNode();
        addChild(statementSequenceNode);
        statementSequenceNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SEND")));
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}