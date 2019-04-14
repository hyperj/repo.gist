package net.hyperj.gist.java.designpattern.visitor;

public class ComputerDeviceTest {

    public static void main(String[] args) {
        ComputerDevice device = new Keyboard();
        device.accept(new DeviceBaseVisitor());
        device = new Mouse();
        device.accept(new DeviceBaseVisitor());
    }
}
