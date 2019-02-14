package leetcode;

public class FindTheCelebrity {

    public boolean knows(int i, int j) {
        return false;
    }

    public int findCelebrity(int n) {
        if (n<2)
            return -1;

        boolean[]  v = new boolean[n];
        int[][] rels = new int[n][n];

        for(int i=0; i<n;i++) {
            if ( !v[i]) {
                if(isCelebrity(n, v, rels,  i))
                    return i;
            }
        }
        return -1;
    }

    private boolean isCelebrity(int n, boolean[] v, int[][] rels, int i){
        for (int j=0; j<n; j++) {
            if (i!=j) {
                if(getRelation(n, rels,i, j)) {
                    v[i] = true;
                    return false;
                }
            }
        }

        for (int j=0; j<n; j++) {
            if (i!=j) {
                if(getRelation(n, rels,j, i)) {
                    v[j] = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean getRelation(int n, int[][] rels, int i, int j) {
        int r = rels[i][j];
        if (r == 0) {
            if(knows(i,j)) {
                rels[i][j] = 1;
                return true;
            } else {
                rels[i][j] = -1;
                return false;
            }
        }
        return r==1;
    }
}
