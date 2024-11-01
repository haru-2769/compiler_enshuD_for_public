package enshud.s2.parser;

import java.util.ArrayList;
import java.util.List;


public abstract class NonTerminalNode implements AstNode {
    private final List<AstNode> children = new ArrayList<>();

    public void addChild(AstNode child) {
        this.children.add(child);
    }

    public List<AstNode> getChildren() {
        return this.children;
    }
    public abstract void parse(Context context) throws SyntaxException;
}