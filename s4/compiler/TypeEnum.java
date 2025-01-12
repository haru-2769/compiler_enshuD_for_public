package enshud.s4.compiler;

public enum TypeEnum {
	INTEGER,
    CHAR,
    BOOLEAN,
    ARRAY_OF_INTEGER,
    ARRAY_OF_CHAR,
    ARRAY_OF_BOOLEAN;

    public boolean isInteger() {
        return this == INTEGER;
    }

    public boolean isChar() {
        return this == CHAR;
    }
    
    public boolean isBoolean() {
        return this == BOOLEAN;
    }

    public boolean isArray() {
        return this == ARRAY_OF_INTEGER || this == ARRAY_OF_CHAR || this == ARRAY_OF_BOOLEAN;
    }

    public boolean isArgument() {
        return this != INTEGER && this != CHAR && this != ARRAY_OF_CHAR;
    }
}
