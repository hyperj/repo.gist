package net.hyperj.gist.java.segment;

import java.util.ArrayList;
import java.util.List;

public class MaximumMatching extends AbstractSegment {

    @Override
    public List<String> segment(String text) {
        List<String> result = new ArrayList<>();
        int length = text.length();
        int current = length;
        int start = 0;
        while (start < current) {
            String temp = text.substring(start, current);
            if (mistake(temp)) {
                current--;
            } else {
                result.add(temp);
                start = current;
                current = length;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> result = new MaximumMatching().segment("MaximumMatching");
        System.out.printf(String.join(", ", result));
    }
}
