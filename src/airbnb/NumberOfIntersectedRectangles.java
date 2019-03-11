package airbnb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumberOfIntersectedRectangles {

    class Rectangle {
        int x1, x2, y1, y2;
        public Rectangle(int[][] a) {
            x1 = a[0][0];
            x2 = a[0][1];
            y1 = a[1][0];
            y2 = a[1][1];
        }

        private boolean isNotOverlap(Rectangle other) {
            return this.x2 < other.x1 || this.x1 > other.x2
                    || this.y2 < other.y1 || this.y1 > other.y2;
        }

        boolean isOverlap(Rectangle other) {
            return !isNotOverlap(other);
        }
    }


    public int countIntersection(int[][][] rectangles) {
        if (rectangles == null || rectangles.length == 0) return 0;
        int n = rectangles.length;
        int[] parent = new int[n];
        Rectangle[] recs = Arrays.stream(rectangles).map(r -> new Rectangle(r)).toArray(Rectangle[]::new);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (recs[i].isOverlap(recs[j])) {
                    int root = findRoot(parent, j);
                    parent[root] = i;
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        Arrays.stream(parent).forEach(i -> set.add(findRoot(parent, i)));
        return set.size();
    }

    private int findRoot(int[] parent, int i) {
        while( i != parent[i]) {
            i = parent[i];
            parent[i] = parent[parent[i]];
        }
        return i;
    }

    public static void main(String[] args) {
        NumberOfIntersectedRectangles solution = new NumberOfIntersectedRectangles();
        int[][][] rectangles = {
                {{0, 5}, {0, 5}},
                {{1, 15}, {8, 5}},
                {{3, 5}, {0, 4}},
                {{8, 12}, {2, 10}},
                {{-4, 2}, {3, 9}}
        };

        System.out.println(solution.countIntersection(rectangles));
    }
}
