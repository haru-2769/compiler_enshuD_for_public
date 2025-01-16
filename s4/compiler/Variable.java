package enshud.s4.compiler;

public class Variable {
    String name;
    TypeEnum type;
    int address;
    int size;
    int offset;
    boolean isGlobal;
    boolean isReferenced = false;

    public Variable(String name, TypeEnum type, int address, int size, int offset, boolean isGlobal) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.size = size;
        this.offset = offset;
        this.isGlobal = isGlobal;
   }

   public void setReferenced() {
        isReferenced = true;
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

    public boolean isGlobal() {
        return isGlobal;
    }

    public boolean isReferenced() {
        return isReferenced;
    }
}
