package enshud.s3.checker;

public class SemanticException extends Exception {
    private final String error;
    
    public SemanticException(Token token) {
        super();
        this.error = "Semantic error: line " + token.getLineCount();
    }
    
    public String getError() {
        return this.error;
    }

}
