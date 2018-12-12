package net.hyperj.gist.common.utils;

import static com.google.common.base.Strings.repeat;

public class StringUtils {

    public static final String BLANK = "";
    public static final String HYPHEN = "-";

    public static void println(String str) {
        System.out.println(repeat(HYPHEN, 1 << 6));
        System.out.println(BLANK);
        System.out.println(str);
        System.out.println(BLANK);
    }
}
