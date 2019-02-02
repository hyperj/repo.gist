package net.hyperj.gist.common.kit;

import static com.google.common.base.Strings.repeat;
import static net.hyperj.gist.common.dict.StringDict.*;

public class StringKit {

    public static void printf(String str) {
        println(new StringBuilder(repeat(HYPHEN, 1 << 6)).append(LINE_BREAK)
                .append(EMPTY).append(LINE_BREAK)
                .append(str).append(LINE_BREAK)
                .append(EMPTY).toString());
    }

    public static void println(String str) {
        System.out.println(str);
    }

    public static void print(String str) {
        System.out.print(str);
    }
}
