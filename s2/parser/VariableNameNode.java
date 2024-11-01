package enshud.s2.parser;
// 変数名
public class VariableNameNode extends NonTerminalNode {
    public void parse(Context context) throws SyntaxException {
        addChild(new TerminalNode(context.checkTerminalSymbol("SIDENTIFIER")));
    }
    
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
