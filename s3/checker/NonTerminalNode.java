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

    public void pruneEmptyNodes() {
        List<AstNode> childrenCopy = new ArrayList<>(getChildren());
        for (AstNode child : childrenCopy) {
            if (child instanceof NonTerminalNode) {
                NonTerminalNode nonTerminalChild = (NonTerminalNode) child;
                nonTerminalChild.pruneEmptyNodes();
                if (nonTerminalChild.isEmpty()) {
                    removeChild(nonTerminalChild);
                }
            }
        }
    }

    public void removeChild(AstNode child) {
        this.children.remove(child);
    }

    public boolean isEmpty() {
        return getChildren().isEmpty();
    }
}
