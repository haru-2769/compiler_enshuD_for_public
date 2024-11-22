package enshud.s3.checker;

public enum Type {
	INTEGER,
    CHAR,
    BOOLEAN,
    ARRAY_OF_INTEGER,
    ARRAY_OF_CHAR,
    ARRAY_OF_BOOLEAN;

    public boolean isArrayType() {
        return this == ARRAY_OF_INTEGER || this == ARRAY_OF_CHAR || this == ARRAY_OF_BOOLEAN;
    }

    public boolean isArgumentType() {
        return this != INTEGER && this != CHAR && this != ARRAY_OF_CHAR;
    }
}
