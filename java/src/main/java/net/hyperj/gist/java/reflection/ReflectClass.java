package net.hyperj.gist.java.reflection;

import java.lang.reflect.*;

import static java.lang.System.out;

public class ReflectClass {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        out.println("\n###### Class ######\n");
        Class<?> clazz1 = Class.forName("[[Ljava.lang.String;");
        out.println(clazz1.getCanonicalName());
        Class<?> clazz2 = String[][].class;
        out.println(clazz2.getCanonicalName());
        String[][] strs = new String[2][2];
        Class<?> clazz3 = strs.getClass();
        out.println(clazz3.getCanonicalName());

        out.println("\n###### Instance ######\n");
        if (strs instanceof String[][]) {
            out.println("strs is String[][]");
        }
        if (String[][].class.isInstance(strs)) {
            out.println("strs is String[][]");
        }
        Class<?> cls = Class.forName("java.lang.String");
        Object array = Array.newInstance(cls, 25);
        Array.set(array, 0, "Array String");
        System.out.println(Array.get(array, 0));

        out.println("\n###### Constructor ######\n");
        Class<?> clazzSB = StringBuilder.class;
        Constructor<?>[] constructors = clazzSB.getDeclaredConstructors();
        for (Constructor c : constructors) {
            System.out.println(c);
        }
        StringBuilder sb1 = (StringBuilder) clazzSB.newInstance();
        sb1.append("123");
        out.println(sb1.toString());
        Constructor constructor = clazzSB.getConstructor(String.class);
        StringBuilder sb2 = (StringBuilder) constructor.newInstance("123");
        out.println(sb2);

        out.println("\n###### Field ######\n");
        Class<?> clazzS = System.class;
        Field[] fields = clazzS.getDeclaredFields();
        for (Field field : fields) {
            //field.setAccessible(true);
            out.println(field.getName() + ": " + field.getType());
        }

        out.println("\n###### Method ######\n");
        Method[] methods = clazzS.getDeclaredMethods();
        for (Method method : methods) {
            out.println(method);
        }
        Method method = clazzS.getMethod("currentTimeMillis");
        out.println(method.invoke(null));
    }
}
