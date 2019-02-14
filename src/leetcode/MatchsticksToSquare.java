package leetcode;

import java.util.Arrays;

public class MatchsticksToSquare {

    public static boolean makesquare(int[] nums) {
        if (nums == null || nums.length < 4)
            return false;
        Arrays.sort(nums);
        for (int i=0, j = nums.length-1; i<j ; i++, j--) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % 4 != 0)
            return false;
        int len = sum / 4;
        if (nums[0] > len)
            return false;
        boolean[] flag = new boolean[nums.length];
        return dfs(nums, new int[4], 0,  len);
    }

    private static boolean dfs(int[] nums, int[] sums, int index, int target) {
        if (index == nums.length){
            if(sums[0] == target && sums[1] == target && sums[2] == target )
                return true;
            return true;
        }
        for(int i=0;i<4;i++){
            if(sums[i] + nums[index] <= target){
                sums[i] += nums[index];
                if(dfs(nums,sums,index+1,target)) return true;
                sums[i] -= nums[index];
            }
        }
        return false;
    }

    private static boolean cal(int[] nums, boolean[] flag, int val, int depth, int len){
        if (val == 0 ) {
            return true;
        }
        for (int i=0; i<nums.length; i++) {
            if (!flag[i]) {
                if (nums[i] <= val) {
                    flag[i] = true;
                    int newDepth = nums[i] == val ? depth+1 : depth;
                    int newValue = nums[i] == val ? len : val - nums[i];
                    if(cal(nums, flag, newValue, newDepth, len)) {
                        return true;
                    } else {
                        flag[i] = false;
                    }
                }
            }
        }
        return false;
    }

    public static void main(final String[] args) {
        int[] nums = {5,5,5,5,16,4,4,4,4,4,3,3,3,3,4};
         System.out.println(makesquare(nums));
    }
}
