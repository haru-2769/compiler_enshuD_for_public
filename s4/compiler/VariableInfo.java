package enshud.s4.compiler;

public class VariableInfo {
    private TypeEnum type;
    private Integer indexMin;
    private Integer indexMax;

    public VariableInfo(TypeEnum type, Integer indexMin, Integer indexMax) {
        this.type = type;
        this.indexMin = indexMin;
        this.indexMax = indexMax;
    }

    public TypeEnum getType() {
        return this.type;
    }

    public Integer getIndexMin() {
        return this.indexMin;
    }

    public Integer getIndexMax() {
        return this.indexMax;
    }
}
