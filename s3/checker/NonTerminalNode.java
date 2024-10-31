package enshud.s3.checker;

import java.util.ArrayList;
import java.util.List;

public class NonTerminalNode implements AstNode {
 private final List<AstNode> children = new ArrayList<>();

 public void addChild(AstNode child) {
     children.add(child);
 }

 public List<AstNode> getChildren() {
     return children;
 }

 @Override
 public void accept(Visitor visitor) {
     visitor.visit(this);
 }
}

