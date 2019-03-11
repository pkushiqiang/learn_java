package airbnb.recent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Wizards {

    class Node implements Comparable<Node> {

        int id;
        int dist;
        int parent;

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }

        public Node(int id, int dist) {
            this.id = id;
            this.dist = dist;
            this.parent = -1;
        }

    }

    public List<Integer> getShortestPath(List<List<Integer>> wizards, int source, int target) {
        List<Node> nodeList = new ArrayList<>();
        for(int i=0; i<wizards.size(); i++) {
            nodeList.add(new Node(i, Integer.MAX_VALUE));
        }
        Node sNode = nodeList.get(source);
        sNode.dist = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(sNode);

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            for(int i : wizards.get(now.id)) {
                Node node =  nodeList.get(i);
                int newDist = (i-now.id) * (i-now.id) + now.dist;
                if (newDist < node.dist) {
                    node.dist = newDist;
                    node.parent = now.id;
                    queue.remove(node);
                }
                queue.offer(node);
            }
        }

        List<Integer> path = new ArrayList<>();
        Node node = nodeList.get(target);
        path.add(target);
        while(node.parent != -1) {
            path.add(node.parent);
            node = nodeList.get(node.parent);
        }

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        Wizards sol =  new Wizards();
        int[][] ids = {{1, 4, 9}, {2, 3, 9}, {4}, {}, {7}, {9}, {}, {9}, {}, {}};
        List<List<Integer>> wizards = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            List<Integer> wizard = new ArrayList<>();
            for (int j = 0; j < ids[i].length; j++) {
                wizard.add(ids[i][j]);
            }
            wizards.add(wizard);
        }
        List<Integer> res = sol.getShortestPath(wizards, 0, 9);

        System.out.println(res);
    }
}
