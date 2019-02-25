package airbnb;

import leetcode.EditDistance;

import java.util.ArrayList;
import java.util.List;

public class KEditDistance_4 {

    class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public boolean isWord = false;
    }

    class Trie {
        public void insert(TrieNode root, String str) {
            TrieNode cur = root;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (cur.children[c - 'a'] == null) cur.children[c - 'a'] = new TrieNode();
                cur = cur.children[c - 'a'];
            }
            cur.isWord = true;
        }

        public TrieNode buildTrie(String[] strs) {
            TrieNode root = new TrieNode();
            for (String str : strs) insert(root, str);
            return root;
        }
    }

    public List<String> findKEditDistance(String[] strs, String target, int k) {
        List<String> res = new ArrayList<>();
        if (strs.length == 0) return res;
        Trie trie = new Trie();
        TrieNode root = trie.buildTrie(strs);
        int[] distance = new int[target.length() + 1];
        for (int i = 0; i < distance.length; i++) distance[i] = i;
        helper(target, res, root, distance, k, "");
        return res;
    }

    private void helper(String target, List<String> res, TrieNode root,
                        int[] distance, int k, String tmp) {
        if (root.isWord) {
            if (distance[target.length()] <= k) res.add(tmp);
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] == null) continue;
            int[] curdistance = new int[target.length() + 1];
            //this +1 is to add the processing character, which is (char)(i+'a')
            curdistance[0] = tmp.length() + 1;
            //use curdistance and distance to represent edit distance for tmp and target
            //distance[j] means edit distance between tmp and target[0,j)
            for (int j = 1; j < distance.length; j++) {
                if (target.charAt(j - 1) == (char) (i + 'a')) curdistance[j] = distance[j - 1];
                    // if character matches, no insert, delete or replace. so no increasing edit distance
                    //current edit distance for tmp and target[0,j) is distance[j-1]
                else {
                    //not match, either delete, insert or replace character on tmp
                    curdistance[j] = Math.min(Math.min(distance[j - 1], distance[j]), curdistance[j - 1])+1;
                    //distance[j-1] means replace tmp.charAt(j-1) with target.charAt(j-1)
                    //distance[j] means delete tmp.charAt(j-1)
                    //curdistance[j-1] means add target.charAt(j-1) after tmp.charAt(j-1)
                }
            }
            helper(target, res, root.children[i], curdistance, k, tmp + (char) (i + 'a'));
        }
    }

    public static void main(String[] args) {
        String[] strs = {"abc", "abd", "abcd", "ad"};
        String[] words = {
                "same",
                "dsafedsf",
                "sddsfe",
                "abdferda",
                "a",
                "b",
                "ade"
        };

        String target = "abcd";
        int k = 4;
        KEditDistance_4 ked = new KEditDistance_4();
        List<String> res = ked.findKEditDistance(words, target, k);
        System.out.println(res);
    }
}





