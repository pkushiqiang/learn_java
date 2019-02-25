package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        /*
        for(int i=0; i<=nums.length; i++) {
            boolean[] flag = new boolean[nums.length];
            dfs(res, list, flag, nums, i, 0);
        }*/
        boolean[] flag = new boolean[nums.length];
        dfs(res, list, flag, nums, 2, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> list, boolean[] flag, int[] nums,  int len, int clen) {
        if (list.size() == len) {
            res.add(new ArrayList(list));
            return;
        }
        for(int i=clen; i<nums.length - (len - clen) + 1; i++) {
            if (!flag[i]) {
                flag[i] = true;
                list.add(nums[i]);
                dfs(res, list, flag, nums, len, i+1);
                list.remove(list.size()-1);
                flag[i] = false;
            }
        }
    }

    public static void main(String[] args){
        Subsets solution = new Subsets();
        int[] nums = {1,2,3,4};
        List<List<Integer>> res = solution.subsets(nums);
        for(List<Integer> list : res) {
            System.out.println("["+list.stream().map(Object::toString).collect(Collectors.joining(","))+"]");
        }
    }
}
