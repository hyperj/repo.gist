package net.hyperj.gist.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Q448FindAlNnumbersDisappearedInAnArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> appearedNumbers = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            appearedNumbers.add(nums[i]);
        }
        List<Integer> disappearedNumbers = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!appearedNumbers.contains(i)) {
                disappearedNumbers.add(i);
            }
        }
        return disappearedNumbers;
    }

}
