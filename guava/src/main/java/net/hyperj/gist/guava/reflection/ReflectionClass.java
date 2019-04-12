package net.hyperj.gist.guava.reflection;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Method;

public class ReflectionClass {

    public static void main(String[] args) throws NoSuchMethodException, IOException {
        // Invokable
        Method method = StringBuilder.class.getMethod("reverse");
        Invokable<StringBuilder, ?> invokable = new TypeToken<StringBuilder>() {
        }.method(method);
        System.out.println(invokable.getReturnType());
        // TypeToken
        // ClassPath
        ClassPath classpath = ClassPath.from(ClassLoader.getSystemClassLoader());
        classpath.getTopLevelClasses("java.util.stream").forEach(i -> System.out.println(i.getName()));
    }


}
