package enshud.s3.checker;

import java.util.ArrayList;
import java.util.List;

public abstract class NonTerminalNode implements AstNode {
    private final List<AstNode> children = new ArrayList<>();

    protected abstract void parse(Context context) throws SyntaxException;

    public void addChild(AstNode child) {
        this.children.add(child);
    }

    public List<AstNode> getChildren() {
        return this.children;
    }
}
