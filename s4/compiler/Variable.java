package enshud.s4.compiler;

public class Variable {
    String name;
    TypeEnum type;
    int address;
    int size;
    int offset;
    String line;
    boolean isGlobal;
    boolean isAssigned = false;
    boolean isReferenced = false;

    public Variable(String name, TypeEnum type, int address, int size, int offset, String line, boolean isGlobal) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.size = size;
        this.offset = offset;
        this.line = line;
        this.isGlobal = isGlobal;
   }

   public Variable(String name, TypeEnum type, int address, int size, int offset, String line, boolean isGlobal, boolean isAssigned) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.size = size;
        this.offset = offset;
        this.line = line;
        this.isGlobal = isGlobal;
        this.isAssigned = isAssigned;
    }

    public void setReferenced() {
        this.isReferenced = true;
    }

    public void setAssigned() {
        this.isAssigned = true;
    }

    public String getName() {
        return this.name;
    }

    public TypeEnum getType() {
        return this.type;
    }

    public int getAddress() {
        return this.address;
    }

    public int getSize() {
        return this.size;
    }

    public int getOffset() {
        return this.offset;
    }

    public String getLine() {
        return this.line;
    }

    public boolean isGlobal() {
        return this.isGlobal;
    }

    public boolean isReferenced() {
        return this.isReferenced;
    }

    public boolean isAssigned() {
        return this.isAssigned;
    }
}
