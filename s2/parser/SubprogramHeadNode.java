package enshud.s2.parser;

public class SubprogramHeadNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SPROCEDURE")));
        ProcedureNameNode procedureNameNode = new ProcedureNameNode();
        addChild(procedureNameNode);
        procedureNameNode.parse(context);
        FormalParameterNode formalParameterNode = new FormalParameterNode();
        addChild(formalParameterNode);
        formalParameterNode.parse(context);
        addChild(new TerminalNode(context.checkTerminalSymbol("SSEMICOLON")));
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}