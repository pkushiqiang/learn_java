package airbnb.recent;

import java.util.*;

public class SlidingPuzzle {
    
    StringBuilder sb = new StringBuilder();
    Map<String, Node> map = new HashMap<>();
    String finalState = "012345678";

    class Node {
        String key;
        Node parent;
        int dist;
        int step;
        String direction;

        public Node(int[][] state) {
            this.key = getKey(state);
            this.dist = getDist();
        }

        public Node(String key, Node parent, String direction) {
            this.key = key;
            this.dist = getDist();
            this.parent = parent;
            this.step = parent.step + 1;
            this.direction = direction;
        }

        private String getKey(int[][] matrix) {
            sb.setLength(0);
            for(int[] line : matrix) {
                for(int i : line) {
                    sb.append(i);
                }
            }
            return sb.toString();
        }

        private int getDist() {
            int d = 0;
            for(int i=0; i<9; i++) {
                if (key.charAt(i) != finalState.charAt(i)) {
                    d++;
                }
            }
            return d;
        }

        public void print() {
            System.out.println("==== Step " + this.step + " ====");
            for(int i=0; i<3; i++) {
                System.out.println( key.charAt(i*3) + " "  + key.charAt(i*3+1) + " " + key.charAt(i*3+2) );
            }
            System.out.println();
        }
    } // class

    private List<Node> move(Node node, Map<String, Node> map) {
        List<Node> res = new ArrayList<>();
        int p = node.key.indexOf('0');
        char[] key =  node.key.toCharArray();
        if ( p % 3 != 0) {
            createNode(node, swap(node.key, p, p-1), "Left", map, res);
        }
        if ( p % 3 != 2) {
            createNode(node, swap(node.key, p, p+1), "Right", map, res);
        }
        if ( p > 2 ) {
            createNode(node, swap(node.key, p, p-3), "Up", map, res);
        }
        if ( p < 6 ) {
            createNode(node, swap(node.key, p, p+3), "Down", map, res);
        }
        return res;
    }

    private void createNode(Node parent,  String key, String direction, Map<String, Node> map, List<Node> res) {
            Node node = new Node(key, parent, direction);
            res.add(node);
    }

    private String swap(String str, int i, int j) {
        char[] key = str.toCharArray();
        char t = key[i];
        key[i] = key[j];
        key[j] = t;

        return String.valueOf(key);
    }

    public List<String> getSolution(int[][] matrix) {
        Node root = new Node(matrix);
        Map<String, Node> map = new HashMap<>();
        map.put(root.key, root);
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> (n1.dist - n2.dist));
        queue.offer(root);

        int minStep = Integer.MAX_VALUE;
        Node resultNode = null;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            List<Node> list = move(node, map);
            for(Node newNode : list) {
                if (newNode.key.equals(finalState)) {
                    if ( minStep > newNode.step ) {
                        minStep = newNode.step;
                        resultNode = newNode;
                    }
                } else if(newNode.dist + newNode.step < minStep ) {
                    if (map.containsKey(newNode.key)) {
                        if (map.get(newNode.key).step <= newNode.step ) {
                            continue;
                        } else {
                            queue.remove(map.get(newNode.key));
                        }
                    }
                    map.put(newNode.key, newNode);
                    queue.offer(newNode);
                }
            } // for
        } // while

        List<Node> res = new ArrayList<>();
        List<String> resList = new ArrayList<>();
        Node node = resultNode;
        while(node != null) {
            res.add(node);
            node = node.parent;
        }
        Collections.reverse(res);
        res.forEach(n -> {
            n.print();
            resList.add(n.direction);
        } );
        return resList;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 1, 4},
                {6, 2, 0},
                {7, 5, 8}
        };

        SlidingPuzzle solution = new SlidingPuzzle();
        List<String> res = solution.getSolution(matrix);
        res.remove(0);
        System.out.println(res);
    }
}
