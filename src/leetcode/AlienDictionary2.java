package leetcode;

import java.util.*;

public class AlienDictionary2 {

    class Node {
        int in;
        char c;
        List<Node> outList;

        public Node(char c) {
            this.c = c;
            this.outList = new ArrayList();
        }

        public void addOut(Node out) {
            this.outList.add(out);
            out.in++;
        }

        public void removeMe(PriorityQueue<Node> pQueue) {
            for (Node node: outList) {
                node.in--;
                pQueue.remove(node);
                pQueue.offer(node);
            }
        }
    }

    Map<Character, Node> nodeMap = new HashMap<>();

    public String alienOrder(String[] words) {

        for (String word: words) {
            for(int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (nodeMap.get(c) == null) {
                    nodeMap.put(c, new Node(c));
                }
            }
        }

        for(int i=1; i<words.length; i++) {
            buildNode(words[i-1],words[i]);
        }

        PriorityQueue<Node> pQueue =
                new PriorityQueue<>((x,y) -> x.in - y.in );
        for (Node node : nodeMap.values()) {
            pQueue.add(node);
        }

        StringBuilder sb = new StringBuilder();
        while (!pQueue.isEmpty()) {
            Node node =  pQueue.poll();
            if (node.in!=0) {
                return "";
            }
            sb.append(node.c);
            node.removeMe(pQueue);
        }
        return sb.toString();
    }

    private void buildNode(String str1, String str2) {
        int len = Math.min(str1.length(), str2.length());
        for (int i=0; i<len; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                char c1 = str1.charAt(i), c2 = str2.charAt(i);
                nodeMap.get(c1).addOut(nodeMap.get(c2));
                break;
            }
        }
    }

    public static void main(String[] args) {
        AlienDictionary2 solution = new AlienDictionary2();
        String[] words = {
                "wrt",
                "wrf",
                "er",
                "ett",
                "rftt"
        };

        String result = solution.alienOrder(words);
        System.out.println("================= 2 ========================");
        System.out.println("result = " + result);

    }
}
