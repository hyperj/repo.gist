package net.hyperj.gist.java.designpattern.singleton;

public class StaticClassSingleton {

    private StaticClassSingleton() {
    }

    public static final StaticClassSingleton getInstance() {
        return StaticClassSingletonHelper.INSTANCE;
    }

    private static class StaticClassSingletonHelper {
        private static final StaticClassSingleton INSTANCE = new StaticClassSingleton();
    }

}
