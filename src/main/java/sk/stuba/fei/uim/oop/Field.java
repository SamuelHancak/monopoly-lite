package sk.stuba.fei.uim.oop;

public abstract class Field {
    protected String fieldName = "_Pole";

    protected Field() {
    }

    protected Field(String name) {
        this.fieldName = name;
    }

    protected abstract FieldType getFieldType();

    protected abstract void fieldFunction(Player player);
}