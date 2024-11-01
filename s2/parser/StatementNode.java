package enshud.s2.parser;

public class StatementNode extends NonTerminalNode{
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(0, "SIDENTIFIER", "SREADLN", "SWRITELN", "SBEGIN")) {
            BasicStatementNode basicStatementNode = new BasicStatementNode();
            addChild(basicStatementNode);
            basicStatementNode.parse(context);
        } else if (context.equalsAny(0, "SIF")) {
            addChild(new TerminalNode(context.checkTerminalSymbol("SIF")));
            ExpressionNode expressionNode = new ExpressionNode();
            addChild(expressionNode);
            expressionNode.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("STHEN")));
            CompoundStatementNode compoundStatementNode = new CompoundStatementNode();
            addChild(compoundStatementNode);
            compoundStatementNode.parse(context);
            if (context.equalsAny(0, "SELSE")) {
                addChild(new TerminalNode(context.checkTerminalSymbol("SELSE")));
                CompoundStatementNode compoundStatementNode1 = new CompoundStatementNode();
                addChild(compoundStatementNode1);
                compoundStatementNode1.parse(context);
            }
        } else {
            addChild(new TerminalNode(context.checkTerminalSymbol("SWHILE")));
            ExpressionNode expressionNode = new ExpressionNode();
            addChild(expressionNode);
            expressionNode.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("SDO")));
            CompoundStatementNode compoundStatementNode = new CompoundStatementNode();
            addChild(compoundStatementNode);
            compoundStatementNode.parse(context);
        }
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
