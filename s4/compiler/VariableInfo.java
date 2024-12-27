package enshud.s4.compiler;

public class VariableInfo {
    private Type type;
    private Integer indexMin;
    private Integer indexMax;
    private int address;

    public VariableInfo(Type type, Integer indexMin, Integer indexMax, int address) {
        this.type = type;
        this.indexMin = indexMin;
        this.indexMax = indexMax;
        this.address = address;
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

    public int getAddress() {
        return this.address;
    }
}
