package enshud.s3.checker;

public class VariableNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        if (context.equalsAny(1, "SLBRACKET")) {
            IndexedVariableNode indexedVariableNode = new IndexedVariableNode();
            addChild(indexedVariableNode);
            indexedVariableNode.parse(context);
        } else {
            PureVariableNode pureVariableNode = new PureVariableNode();
            addChild(pureVariableNode);
            pureVariableNode.parse(context);
        }
    }

    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}