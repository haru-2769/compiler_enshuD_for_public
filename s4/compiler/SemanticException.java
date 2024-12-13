package enshud.s4.compiler;

public class SemanticException extends Exception {
    private final String error;
    
    public SemanticException(Token token) {
        super();
        this.error = "Semantic error: line " + token.getLineCount();
    }
    
    @Override
    public String getMessage() {
        return this.error;
    }

}
