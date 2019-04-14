package net.hyperj.gist.java.designpattern.visitor;

public interface ComputerDeviceVisitor {
    void visit(Keyboard keyboard);

    void visit(Mouse mouse);
}
