package enshud.s3.checker;

public class VariableDeclarationSequenceNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        do {
            VariableNameSequenceNode variableNameSequenceNode = new VariableNameSequenceNode();
            addChild(variableNameSequenceNode);
            variableNameSequenceNode.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("SCOLON")));
            TypeNode typeNode = new TypeNode();
            addChild(typeNode);
            typeNode.parse(context);
            addChild(new TerminalNode(context.checkTerminalSymbol("SSEMICOLON")));
        } while (context.equalsAny(0, "SIDENTIFIER"));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}