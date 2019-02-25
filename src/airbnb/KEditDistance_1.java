package airbnb;

import leetcode.EditDistance;

import java.util.ArrayList;
import java.util.List;

public class KEditDistance_1 {

    EditDistance editDistance = new EditDistance();

    public List<String> getKEditDistance(String[] words, String target, int k) {
        List<String> res = new ArrayList<>();
        for(String word : words) {
            int dis = editDistance.minDistance(word, target);
            if (dis == k) {
                res.add(word);
            }
        }
        return res;
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

        KEditDistance_1 solution = new KEditDistance_1();
        List<String> res = solution.getKEditDistance(words, target, k);
        res.forEach(str -> System.out.println(str));
    }
}
