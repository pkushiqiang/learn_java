package airbnb.recent;

import java.util.*;

public class AlienDictionary {

    class Node {
        char c;
        int in;
        List<Node> out = new ArrayList<>();
        public Node(char c) {
            this.c = c;
        }
    }

    Map<Character, Node> map = new HashMap<>();

    public String alienOrder(String[] words) {
        for (String word : words) {
            for (int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                map.put(c, new Node(c));
            }
        }

        for(int i=0; i<words.length-1; i++) {
            String word1 = words[i];
            String word2 = words[i+1];

            int len = Math.min(word1.length(), word2.length());
            for (int j=0; j<len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    map.get(c2).in++;
                    map.get(c1).out.add(map.get(c2));
                    break;
                }
            }
        }

        PriorityQueue<Node> queue = new PriorityQueue<>((x, y) -> x.in - y.in);
        map.values().stream().forEach(node -> queue.offer(node));
        List<Node> res = new ArrayList<>();
        while(!queue.isEmpty()) {
            Node node =  queue.poll();
            if (node.in != 0) {
                System.out.println("node.c = " + node.c + " " + node.in);
                return "";
            }
            res.add(node);
            node.out.stream().forEach( n -> {
                n.in--;
                queue.remove(n);
                queue.offer(n);
            } );
        }
        char[] ar = new char[res.size()];
        for(int i=0; i<res.size(); i++) {
            ar[i] = res.get(i).c;
        }

        return String.valueOf(ar);
    }

    public static void main(String[] args) {
        AlienDictionary sol = new AlienDictionary();
        String[] words = { "za","zb","ca","cb" };
        String res = sol.alienOrder(words);
        System.out.println("res=" + res);
    }
}
