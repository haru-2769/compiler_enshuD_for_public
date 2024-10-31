package enshud.s2.parser;

public class AstPrint implements Visitor {
 private int level = 0;

 @Override
 public void visit(TerminalNode terminalNode) {
     printIndent(level);
     System.out.println("TerminalNode(" + terminalNode.getToken().getLexical() + ")");
 }

 @Override
 public void visit(NonTerminalNode nonTerminalNode) {
     printIndent(level);
     System.out.println("NonTerminalNode(" + nonTerminalNode.getType() + ")");
     level++;
     for (AstNode child : nonTerminalNode.getChildren()) {
         child.accept(this);
     }
     level--;
 }

 private void printIndent(int level) {
     for (int i = 0; i < level; i++) System.out.print("  ");
 }
}

