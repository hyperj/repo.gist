package net.hyperj.gist.java.designpattern.visitor;

public interface ComputerDevice {

    void accept(ComputerDeviceVisitor visitor);
}
