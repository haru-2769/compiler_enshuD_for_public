package enshud.s3.checker;

public class VariableInfo {
    private String type;
    private boolean isReferenced;

    public VariableInfo(String type) {
        this.type = type;
        this.isReferenced = false;
    }

    public String getType() {
        return type;
    }

    public boolean isReferenced() {
        return isReferenced;
    }

    public void setReferenced(boolean isReferenced) {
        this.isReferenced = isReferenced;
    }
}
