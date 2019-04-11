package net.hyperj.gist.java.segment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseMaximumMatching extends AbstractSegment {

    @Override
    public List<String> segment(String text) {
        List<String> result = new ArrayList<>();
        int length = text.length();
        int current = length;
        int start = 0;
        while (start < current) {
            String temp = text.substring(start, current);
            if (mistake(temp)) {
                start++;
            } else {
                result.add(temp);
                current = start;
                start = 0;
            }
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        List<String> result = new ReverseMaximumMatching().segment("ReverseMaximumMatching");
        System.out.printf(String.join(", ", result));
    }
}
