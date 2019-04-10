package net.hyperj.gist.java.designpattern.factory;

public enum ShapeType {

    SQUARE("SQUARE"), CIRCLE("CIRCLE");

    private String name;

    ShapeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
