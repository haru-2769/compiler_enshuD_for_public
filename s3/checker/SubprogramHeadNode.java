package enshud.s3.checker;

public class SubprogramHeadNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPROCEDURE")));
        addChild(new ProcedureNameNode(context.checkTerminalSymbol("SIDENTIFIER")));
        FormalParameterNode formalParameterNode = new FormalParameterNode();
        addChild(formalParameterNode);
        formalParameterNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SSEMICOLON")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }

}