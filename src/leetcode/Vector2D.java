package leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Vector2D implements Iterator<Integer> {
    List<List<Integer>> vec2d;

    private int ni=0, nj=0;
    boolean hasNext =false;
    public Vector2D(List<List<Integer>> vec2d) {
        this.vec2d = vec2d;
        findNext();
    }

    @Override
    public Integer next() {
        if (this.hasNext) {
            Integer res = vec2d.get(ni).get(nj);
            nj++;
            findNext();
            return res;
        } else {
            return null;
        }
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    private void findNext(){
        while(ni < vec2d.size()) {
            while (nj < vec2d.get(ni).size()) {
                if (vec2d.get(ni).get(nj) != null) {
                    this.hasNext = true;
                    return;
                }
                nj++;
            }
            ni++;
            nj=0;
        }
        this.hasNext = false;
    }

    public static void main(String[] args) {
        List<List<Integer>> vec2d = new ArrayList<>();
        Integer[] a1 = {1,2};
        Integer[] a2 = {3};
        Integer[] a3 = {4,5,6};

        vec2d.add(Arrays.asList(a1));
        vec2d.add(Arrays.asList(a2));
        vec2d.add(Arrays.asList(a3));

        Vector2D solution = new Vector2D(vec2d);

        System.out.println("=========================================");
        while (solution.hasNext()) {
            System.out.println(solution.next() + "," );
        }


    }
}

