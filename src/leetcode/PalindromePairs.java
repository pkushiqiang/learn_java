package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PalindromePairs {

    class Trie {
        Character v;
        Map<Character, Trie> children;
        int index = -1;

        public Trie() {
            this.children = new HashMap<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if(words == null || words.length==0) {
            return result;
        }

        Trie root = buildTrie(words);
        search(root, words, result);
        return result;
    }

    private void search(final Trie root, final String[] words, final List<List<Integer>> results){
        for (int w =0; w <words.length; w++) {
            Trie node = root;
            String word = words[w];
            int i = word.length()-1;
            List<Integer> lefts = new ArrayList<>();
          //  System.out.println("word=" + word);
            for (; i >=0; i--) {
                if (node.index != -1 && isSubStringPalindromes(word, i)) {
                    lefts.add(node.index);
                }
                char c = word.charAt(i);
                if (node.children.get(c) != null) {
                    node = node.children.get(c);
                } else {
                    break;
                }
            }

            if (i==-1) {
                if (node.index != -1) {
                    lefts.add(node.index);
                }
                List<Character> str = new ArrayList<>();
                for (Trie child :  node.children.values()) {
                    getPalindromes(child, str, lefts);
                }
            }

            for (int left : lefts) {
                if (w!=left) {
                    List<Integer> pair = new ArrayList<>();
                    pair.add(left);
                    pair.add(w);
                    results.add(pair);
                }
            }
        }
    }

    private void getPalindromes(Trie node, List<Character> str, List<Integer> left) {
        if (node == null)
            return;
        str.add(node.v);
        if (node.index >=0 ) {
            if (isListPalindromes(str)) {
                left.add(node.index);
            }
        }
        for(Character c : node.children.keySet()) {
            Trie child = node.children.get(c);
            getPalindromes(child, str, left);
        }
        str.remove(str.size()-1);
    }

    private boolean isListPalindromes(List<Character> str) {
        int i=0;
        int j = str.size()-1;
        while(i<j){
            if (str.get(i) != str.get(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    private boolean isSubStringPalindromes(String str, int j) {
        int i = 0;
        while(i<j){
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    private Trie buildTrie(final String[] words) {
        Trie root = new Trie();

        for (int w =0; w <words.length; w++) {
            Trie node = root;
            String word = words[w];
            if (word.length() == 0) {
                node.index = w;
            }
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (node.children.get(c) == null) {
                    Trie t = new Trie();
                    t.v = c;
                    node.children.put(c, t);
                }
                node = node.children.get(c);
                if (i == word.length()-1) {
                    node.index = w;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        PalindromePairs palindromePairs = new PalindromePairs();
        String[] words = {"abcd","dcba","lls","s","sssll"};
        List<List<Integer>> result = palindromePairs.palindromePairs(words);
        System.out.println("=========================================");
        for (List<Integer> pair : result) {
            System.out.println(pair.get(0) + "," + pair.get(1));
        }
    }
}
