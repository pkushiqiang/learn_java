package leetcode;



import java.util.*;

public class RemoveBoxes {

    StringBuilder sb = new StringBuilder();
    Map<String, Node> map = new HashMap<>();

    class Node  implements Comparable<Node> {
        int[] line;
        int point;
        int maxPoint;
        Node parent;
        String key;

        public int compareTo(Node other){
           // return other.point - this.point;

            if (other.point - this.point != 0) {
                return other.point - this.point;
            } else {
                return this.line.length - other.line.length;
            }
        }

        public String getKey() {
            if (key == null) {
                sb.setLength(0);
                for (int i : line) {
                    sb.append(i);
                }
                key = sb.toString();
            }
            return key;
        }
    }

    private Node createNewNode(Node node, int l, int r) {
        int[] line = node.line;
        int diff = r - l;
        int len = line.length - diff;
        int[] newLine = new int[len];
        int i=0;
        for(; i<l; i++) {
            newLine[i] = line[i];
        }
        for( int j=r; j<line.length; j++, i++) {
            newLine[i] = line[j];
        }
        Node newNode = new Node();
        newNode.line = newLine;
        newNode.point = node.point + diff * diff;
        newNode.maxPoint =  newNode.point +  len*len;
        newNode.parent  = node;
        newNode.getKey();
        return newNode;
    }

    public int removeBoxes(int[] boxes) {

        /*
        PriorityQueue<String> queue = new PriorityQueue<>( new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return  map.get(o1).compareTo(map.get(o2));
            }
        });
        */

   //   PriorityQueue<String> queue = new PriorityQueue<>( (s1, s2) -> map.get(s1).compareTo(map.get(s2)));
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparing(map::get));

        int max = 0;
        Node maxNode = null;

        Node node = new Node();
        node.line = boxes;
        node.maxPoint = boxes.length * boxes.length;
        queue.offer(node.getKey());
        map.put(node.getKey(), node);

        int cnt =0;
        while(!queue.isEmpty()) {
            System.out.println("queue size=" + queue.size());
            node = map.get(queue.poll());
            cnt++;
            if ( node.maxPoint > max) {
                int[] line = node.line;
                int i=0;
                while(i<line.length) {
                    int j=i;
                    while( j< line.length && line[i] == line[j]) {
                        j++;
                    }
                    if ( i==0 && j==line.length) {
                        int point =  line.length * line.length + node.point;
                        if (max < point) {
                            max = point;
                            maxNode = node;
                        }
                    } else {
                        Node newNode = createNewNode(node, i, j);
                        String key = newNode.getKey();
                        if (!map.containsKey(key)) {
                            map.put(key, newNode);
                            queue.offer(newNode.key);
                        } else {
                            Node oldNode = map.get(key);
                            if (oldNode.point < newNode.point) {
                                map.put(key, newNode);
                                queue.remove(oldNode);
                                queue.offer(newNode.getKey());
                            }
                        }

                        if (max < newNode.point) {
                            max = newNode.point;
                            maxNode = node;
                        }
                    }
                    i = j;
                } //for
            }// if
        }
        System.out.println("cnt=" + cnt);
        return max;
    }

    public static void main(String[] args) {
        RemoveBoxes solution = new RemoveBoxes();
      //  int[] boxes = {1, 3, 2, 2, 2, 3, 4, 3, 1};
      //   int[] boxes = {1, 3, 3, 4, 3, 1};
        int[] boxes = {3, 8, 8, 5, 5, 3, 9, 2, 4, 4, 6, 5, 8, 4, 8, 6, 9, 6, 2, 8, 6, 4, 1, 9, 5, 3, 10, 5, 3, 3, 9, 8, 8, 6, 5, 3, 7, 4, 9, 6, 3, 9, 4, 3, 5, 10, 7, 6, 10, 7};
 
        System.out.println("max=" + solution.removeBoxes(boxes));
    }
}
