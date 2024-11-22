package enshud.s3.checker;

public class VariableInfo {
    private Type type;
    private boolean isReferenced;

    public VariableInfo(Type type) {
        this.type = type;
        this.isReferenced = false;
    }

    public Type getType() {
        return type;
    }

    public boolean isReferenced() {
        return isReferenced;
    }

    public void setReferenced(boolean isReferenced) {
        this.isReferenced = isReferenced;
    }
}
