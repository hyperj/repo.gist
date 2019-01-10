package net.hyperj.gist.common.kit;

import static com.google.common.base.Strings.repeat;
import static net.hyperj.gist.common.dict.StringDict.*;

public class StringKit {

    public static void println(String str) {
        System.out.println(repeat(HYPHEN, 1 << 6));
        System.out.println(EMPTY);
        System.out.println(str);
        System.out.println(EMPTY);
    }
}
