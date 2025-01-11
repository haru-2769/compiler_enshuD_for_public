package enshud.s4.compiler;

public class SemanticException extends Exception {
    private final String error;
    
    public SemanticException(String lineCount) {
        this.error = "Semantic error: line " + lineCount;
    }
    
    @Override
    public String getMessage() {
        return this.error;
    }

}
