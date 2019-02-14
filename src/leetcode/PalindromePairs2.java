package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs2 {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if(words == null || words.length==0) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        Map<String, Integer> reverseMap = new HashMap<>();
        for (int i=0; i<words.length; i++) {
            String re = sb.append(words[i]).reverse().toString();
            reverseMap.put(re, i);
            sb.setLength(0);
        }

        for (int i=0; i<words.length; i++) {
            String  word = words[i];
            if (word.length() == 0) {
                for (int k=0; k<words.length; k++) {
                    if (isPalindrome(words[k])) {
                        add2Result(result, i,k);
                    }
                }
            }
            for (int j = 0; j< word.length(); j++) {
                String left  = word.substring(0,j);
                String right = word.substring(j,word.length());
                if (reverseMap.containsKey(left) &&
                       isPalindrome(right) ) {
                    add2Result(result, i,reverseMap.get(left) );
                }
                if (reverseMap.containsKey(right) &&
                        isPalindrome(left) ) {
                    add2Result(result, reverseMap.get(right), i);
                }
            }
        }
        return result;
    }

    private void add2Result(List<List<Integer>> result, int left, int right) {
        if (left != right) {
            List<Integer> pair = new ArrayList<>();
            pair.add(left);
            pair.add(right);
            result.add(pair);
        }
    }

    private boolean isPalindrome(String str) {
         int i = 0, j= str.length()-1;
         while (i<j) {
             if (str.charAt(i) != str.charAt(j)) {
                 return false;
             } else {
                 i++;
                 j--;
             }
         }
         return true;
    }

    public static void main(String[] args) {
        PalindromePairs2 palindromePairs2 = new PalindromePairs2();
        String[] words = {"a",""};
        List<List<Integer>> result = palindromePairs2.palindromePairs(words);
        System.out.println("=========================================");
        for (List<Integer> pair : result) {
            System.out.println(pair.get(0) + "," + pair.get(1));
        }
    }
}
