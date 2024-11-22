package enshud.s3.checker;

import java.util.List;
import java.util.ArrayList;

public class ProcedureInfo {
    private List<Type> types;
    private boolean isReferenced;

    public ProcedureInfo() {
        this.isReferenced = false;
    }

    public List<Type> getType() {
        return types;
    }

    public void setType(List<Type> types) {
        this.types = new ArrayList<>(types);
    }

    public boolean isReferenced() {
        return isReferenced;
    }

    public void setReferenced(boolean isReferenced) {
        this.isReferenced = isReferenced;
    }
}
