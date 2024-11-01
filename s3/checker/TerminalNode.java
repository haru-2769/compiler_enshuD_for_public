package enshud.s3.checker;

public class TerminalNode implements AstNode {
 private final Token token;

 public TerminalNode(Token token) {
     this.token = token;
 }

 public Token getToken() {
     return this.token;
 }

 @Override
 public void accept(Visitor visitor) throws SemanticException {
     visitor.visit(this);
 }
}

