package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        if(nums == null) {
            return 0;
        }

        if (nums.length < 2) {
            return nums.length;
        }

        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        for (int i=1; i<nums.length; i++) {
            int s= 0, e = list.size()-1;
            if(nums[i] > list.get(e)) {
                list.add(nums[i]);
            } else {
                int m = 0;
                while(s<e) {
                    m = (s+e)/2;
                    if (list.get(m) < nums[i] ) {
                        s = m+1;
                    }  else {
                        e = m;
                    }
                }
                list.set(e, nums[i]);
            }
        }
        return list.size();
    }

    public static void main(String[] args) {
        int[] nums = {4,10,4,3,8,9};
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        System.out.println("sdfds");
        System.out.println(solution.lengthOfLIS(nums));
    }
}
