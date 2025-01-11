package enshud.s4.compiler;

public class Variable {
    String name;
    TypeEnum type;
    int address;
    int size;
    int offset;

    public Variable(String name, TypeEnum type, int address, int size, int offset) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.size = size;
        this.offset = offset;
    }

    public String getName() {
        return name;
    }

    public TypeEnum getType() {
        return type;
    }

    public int getAddress() {
        return address;
    }

    public int getSize() {
        return size;
    }

    public int getOffset() {
        return offset;
    }
}
