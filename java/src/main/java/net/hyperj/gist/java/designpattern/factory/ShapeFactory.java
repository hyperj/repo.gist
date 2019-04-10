package net.hyperj.gist.java.designpattern.factory;

import org.apache.commons.lang3.StringUtils;

public class ShapeFactory {

    public static Shape getShape(ShapeType type) {
        if (type != null) {
            switch (type) {
                case CIRCLE:
                    return new Circle();
                case SQUARE:
                    return new Square();
            }
        }
        return null;
    }

    public static Shape getShape(String name) {
        if (StringUtils.isNotBlank(name)) {
            if (name.equalsIgnoreCase(ShapeType.SQUARE.getName())) {
                return new Square();
            } else if (name.equalsIgnoreCase(ShapeType.CIRCLE.getName())) {
                return new Circle();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ShapeFactory.getShape(ShapeType.SQUARE.getName()).draw();
        ShapeFactory.getShape(ShapeType.CIRCLE).draw();

    }
}
