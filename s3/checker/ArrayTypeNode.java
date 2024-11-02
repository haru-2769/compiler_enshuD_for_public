package enshud.s3.checker;

public class ArrayTypeNode extends NonTerminalNode {
    public ArrayTypeNode(Context context) throws SyntaxException {
        parse(context);
    }

    protected void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SARRAY")));
        addChild(new TerminalNode(context.checkTerminalSymbol("SLBRACKET")));
        addChild(new IndexMinValueNode(context));
        addChild(new TerminalNode(context.checkTerminalSymbol("SRANGE")));
        addChild(new IndexMaxValueNode(context));
        addChild(new TerminalNode(context.checkTerminalSymbol("SRBRACKET")));
        addChild(new TerminalNode(context.checkTerminalSymbol("SOF")));
        addChild(new StandardTypeNode(context.checkTerminalSymbol("SINTEGER", "SCHAR", "SBOOLEAN")));
    }
    
    public void accept(Visitor visitor) throws SemanticException {
        visitor.visit(this);
    }
}
