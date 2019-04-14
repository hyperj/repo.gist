package net.hyperj.gist.java.designpattern.visitor;

public class DeviceBaseVisitor implements ComputerDeviceVisitor {

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Keyboard");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Mouse");
    }
}
