package enshud.s4.compiler;

import java.util.List;
import java.util.ArrayList;

public class ReadlnNode extends StmtNode {
	final private List<VariableNode> variableNodes;

	public ReadlnNode() {
		this.variableNodes = new ArrayList<>();
	}

	@Override
	public void parse(Context context) throws SyntaxException {	
		context.checkTerminalSymbol("SREADLN");
		if (context.equalsAny(0, "SLPAREN")) {
			VariableNode variableNode;
			context.checkTerminalSymbol("SLPAREN");
			while (true) {
				variableNode = new VariableNode();
				variableNode.parse(context);
				this.variableNodes.add(variableNode);
				if (context.equalsAny(0, "SCOMMA")) {
					context.checkTerminalSymbol("SCOMMA");
				} else {
					break;
				}
			}
			context.checkTerminalSymbol("SRPAREN");
		}
	}

	@Override
	public void accept(Visitor visitor) throws SemanticException {
		visitor.visit(this);
	}

	public List<VariableNode> getVariableNodes() {
		return this.variableNodes;
	}
}
