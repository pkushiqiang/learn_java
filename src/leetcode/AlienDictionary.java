package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlienDictionary {

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

        public void removeMe() {
            for (Node node: outList) {
                node.in--;
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

        StringBuilder sb = new StringBuilder();
        while (nodeMap.size()>0) {
            boolean findOne = false;
            for (Character c : nodeMap.keySet()) {
                if (nodeMap.get(c).in == 0) {
                    sb.append(c);
                    nodeMap.get(c).removeMe();
                    nodeMap.remove(c);
                    findOne = true;
                    break;
                }
            }
            if (!findOne) {
                return "";
            }
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
        AlienDictionary solution = new AlienDictionary();
        String[] words = {
                "z", "z"
        };

        String result = solution.alienOrder(words);
        System.out.println("=========================================");
        System.out.println("result = " + result);

    }
}
