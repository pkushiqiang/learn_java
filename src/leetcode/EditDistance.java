package leetcode;

import airbnb.KEditDistance_1;

import java.util.List;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1==null || word1.length() == 0 ) {
            if (word2==null)
                return 0;
            else
                return word2.length();
        }

        if (word2 == null || word2.length() == 0)
            return word1.length();

        int[][] dis = new int[word1.length()+1][word2.length()+1];
        for (int i=0; i<=word2.length(); i++){
            dis[0][i] = i;
        }
        for (int i=0; i<=word1.length(); i++)
            dis[i][0] = i;

        for (int i=0; i<word1.length(); i++ ){
            for (int j=0; j<word2.length(); j++){

                if (word1.charAt(i) == word2.charAt(j))
                    dis[i+1][j+1] = dis[i][j];
                else {
                    int a1 = dis[i][j]+1;
                    int a2 = dis[i][j+1]+1;
                    int a3 = dis[i+1][j]+1;
                    dis[i+1][j+1] = Math.min(a1, Math.min(a2,a3));
                }
            }
        }

        return dis[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        String[] words = {
                "same",
                "dsafedsf",
                "sddsfe",
                "abdferda",
                "a",
                "b",
                "ade"
        };

        String target = "abcd";
        int k =3;

        EditDistance solution = new EditDistance();
        for(String word : words) {
             System.out.println(word + "->" + solution.minDistance(word, target));
        }
    }
}
