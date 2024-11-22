package enshud.s3.checker;

public class VariableInfo {
    private Type type;
    private Integer indexMin;
    private Integer indexMax;

    public VariableInfo(Type type, Integer indexMin, Integer indexMax) {
        this.type = type;
        this.indexMin = indexMin;
        this.indexMax = indexMax;
    }

    public Type getType() {
        return this.type;
    }

    public Integer getIndexMin() {
        return this.indexMin;
    }

    public Integer getIndexMax() {
        return this.indexMax;
    }
}
