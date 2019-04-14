package net.hyperj.gist.java.designpattern.visitor;

public class Keyboard implements ComputerDevice {
    @Override
    public void accept(ComputerDeviceVisitor visitor) {
        visitor.visit(this);
    }
}
