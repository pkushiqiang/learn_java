package leetcode;

import java.util.Arrays;
import java.util.List;

public class WiggleSortII {

    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length < 2)
            return;

        Arrays.sort(nums);
        int[] nums2 = new int[nums.length];
        int i =  nums2.length/2-1;
        int j= nums2.length-1;
        int c = 0;
        while(i>=0) {
            nums2[c++] = nums[i--];
            nums2[c++] = nums[j--];
        }

        if(nums2.length%2==1) {
            nums2[c] = nums[j];
        }

        for(  i=0; i<nums.length; i++) {
            nums[i] = nums2[i];
        }
    }


    public static void main(String[] args) {
        WiggleSortII solution = new WiggleSortII();

        //  int[] nums = {1,1,2,1,2,2,1};

        int[] nums = {5,3,1,2,6,7,8,5,5};
         solution.wiggleSort(nums);
        System.out.println("=========================================");
        for (int i : nums) {
            System.out.print(i + ", ");
        }
    }
}
