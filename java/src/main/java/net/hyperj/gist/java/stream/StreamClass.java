package net.hyperj.gist.java.stream;

import com.google.common.collect.Lists;

import java.util.*;

import static java.lang.System.out;

public class StreamClass {

    public static void main(String[] args) {
        Integer[] ints = {3, 6, 4, 8, 5, 7, 2, 9, 1, 0};
        ArrayList<Integer> integers = Lists.newArrayList(ints);
        int size = ints.length;
        pretty("Count");
        println(size);
        pretty("Sum");
        println(integers.stream().reduce((x, y) -> x + y).get());
        pretty("Sort");
        integers.stream().sorted().forEach(out::println);
        pretty("Top");
        integers.stream().sorted((x, y) -> x > y ? -1 : 1).limit(3).forEach(out::println);
        pretty("Min/Max");
        println(integers.stream().min((x, y) -> x > y ? 1 : -1).get());
        println(integers.stream().max((x, y) -> x > y ? 1 : -1).get());
        pretty("Mean");
        println(integers.stream().reduce((x, y) -> x + y).get() * 1.0 / size);
        pretty("Median");
        int r = size % 2 == 0 ? 2 : 1;
        integers.stream().sorted().skip((size - 1) / 2).limit(r).forEach(out::println);
    }

    public static void println(Object o) {
        out.println(o);
    }

    public static void pretty(Object o) {
        println("\n------ " + o + " ------\n");
    }
}
