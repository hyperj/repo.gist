package net.hyperj.gist.java.segment;

import org.languagetool.JLanguageTool;
import org.languagetool.ResultCache;
import org.languagetool.UserConfig;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.language.Chinese;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.en.MorfologikAmericanSpellerRule;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public abstract class AbstractSegment implements Segment {

    private static List<String> words = Arrays.asList();

    private static final ThreadLocal<JLanguageTool> jlt = ThreadLocal.withInitial(() ->
            new JLanguageTool(new AmericanEnglish(), new Chinese(), new ResultCache(10240), new UserConfig(words)));

    public static Boolean mistake(String text) {
        Boolean flag = FALSE;
        try {
            List<RuleMatch> rules = jlt.get().check(text);
            for (RuleMatch rule : rules) {
                if (rule.getRule() instanceof MorfologikAmericanSpellerRule) {
                    flag = TRUE;
                }
            }
        } catch (IOException e) {
            flag = TRUE;
            e.printStackTrace();
        }
        return flag;
    }
}
