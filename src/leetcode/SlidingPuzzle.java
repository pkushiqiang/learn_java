package leetcode;

import java.util.*;

public class SlidingPuzzle {
    StringBuilder sb = new StringBuilder();

    public int slidingPuzzle(int[][] board) {
        Map<String, int[]> map = new HashMap<>();
        Queue<int[]> q1 = new LinkedList<>();
        Queue<int[]> q2 = new LinkedList<>();
        Queue<int[]> t;
        
        int[] src = new int[6];
        int k = 0;
        for(int i=0; i<2;i++)
            for(int j=0;j<3;j++) {
                src[k++] = board[i][j];
            }
        String target = "123450";
        String key = getKey(src);
        if (target.equals(key))
            return 0;

        map.put(key, src);
        q1.offer(src);
        int s=0;

        while(!q1.isEmpty()) {
            s++;
            while(!q1.isEmpty()) {
                int[] cb = q1.poll();
                List<int[]> newList = move(cb);
                for (int[] nb: newList) {
                    key = getKey(nb);
                    if (key.equals(target))
                        return s;
                    if (!map.containsKey(key)) {
                        map.put(key,nb);
                        q2.offer(nb);
                    }
                }
            }
            t = q1;
            q1 = q2;
            q2 = t;
        }
        return -1;
    }

    private String getKey(int[] b) {
        sb.setLength(0);
        for (int c:b) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    private int find0(int[] src) {
        for(int i=0; i<6; i++) {
            if (src[i] == 0)
                return i;
        }
        return -1;
    }

    private List<int[]> move(int[] src) {
        List<int[]> list = new ArrayList<>();
        int p = find0(src);
        if (p!=0 && p!=3) {
            list.add(newBoard(src, p,-1));
        }
        if (p!=2 && p!=5) {
            list.add(newBoard(src, p,1));
        }
        if (p<3) {
            list.add(newBoard(src, p,3));
        }
        if (p>2) {
            list.add(newBoard(src, p,-3));
        }
        return list;
    }

    private int[] newBoard(int[] src, int p, int m) {
        int[] dest = new int[6];
        System.arraycopy( src, 0, dest, 0, src.length );
        dest[p] = dest[p+m];
        dest[p+m] = 0;
        return dest;
    }

    public static void main(String[] args) {
        SlidingPuzzle solution = new SlidingPuzzle();
        int[][] board = {{4,1,2},{5,0,3}};
        int result = solution.slidingPuzzle(board);
        System.out.println("=========================================");
        System.out.println("result = " + result);

    }
    
}
