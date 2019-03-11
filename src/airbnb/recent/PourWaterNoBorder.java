package airbnb.recent;


import java.io.*;
import java.util.*;

public class PourWaterNoBorder {

    public int[] pourWaterNoBorder(int[] heights, int V, int K) {
        if (K==0) {
            return heights;
        }
        int[] newHeights = Arrays.copyOf(heights, heights.length);

        while(V>0) {
            System.out.println("v=" + V);
            int p = K;
            int left = K-1;
            while( left>=0 && newHeights[left] <= newHeights[p] ) {
                if (newHeights[left] < newHeights[p]) {
                    p = left;
                }
                left--;
            }
            if (p<K) {
                V--;
                if(left >= 0) {
                    newHeights[p]++;
                }
            } else {
                int right = K+1;
                while(right<heights.length && newHeights[p] >= newHeights[right]) {
                    if (newHeights[right] < newHeights[p]) {
                        p = right;
                    }
                    right++;
                }
                V--;
                if (right < heights.length) {
                    newHeights[p]++;
                }
            }
            printShape(newHeights, heights);
        }
        return newHeights;
    }

    private void printShape(int[] newHeights, int[] heights) {

        int max = 0;
        for (int n: newHeights) {
            max = Math.max(max, n);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=max; i>0; i--) {
            sb.setLength(0);
            for (int j=0; j<heights.length; j++) {
                if (i>newHeights[j]) {
                    sb.append(' ');
                } else if (i>heights[j]) {
                    sb.append('W');
                } else {
                    sb.append('H');
                }
            }
            System.out.println(sb.toString());
        }

        sb.setLength(0);
        for (int j=0; j<heights.length; j++) {
            sb.append('-');
        }
        System.out.println(sb.toString());
        System.out.println( );

    }


    public static void main(String[] args) {
        int[] heights = {3,1,1,2,0,2,5};

        PourWaterNoBorder solution = new PourWaterNoBorder();
        solution.pourWaterNoBorder(heights, 6, 3);
    }
}
