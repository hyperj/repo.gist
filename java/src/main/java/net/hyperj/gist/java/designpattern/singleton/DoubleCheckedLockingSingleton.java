package net.hyperj.gist.java.designpattern.singleton;

public class DoubleCheckedLockingSingleton {

    private DoubleCheckedLockingSingleton() {
    }

    private static volatile DoubleCheckedLockingSingleton instance;

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
