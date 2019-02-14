package leetcode;

import java.util.ArrayList;
import java.util.List;



public class GuessTheWord2 {


    int[][] H;

    public static void main(String[] args) {

        Master master = new Master("ccoyyo");
        String[] wordlist = {"wichbx","oahwep","tpulot","eqznzs","vvmplb","eywinm","dqefpt","kmjmxr","ihkovg","trbzyb","xqulhc","bcsbfw","rwzslk","abpjhw","mpubps","viyzbc","kodlta","ckfzjh","phuepp","rokoro","nxcwmo","awvqlr","uooeon","hhfuzz","sajxgr","oxgaix","fnugyu","lkxwru","mhtrvb","xxonmg","tqxlbr","euxtzg","tjwvad","uslult","rtjosi","hsygda","vyuica","mbnagm","uinqur","pikenp","szgupv","qpxmsw","vunxdn","jahhfn","kmbeok","biywow","yvgwho","hwzodo","loffxk","xavzqd","vwzpfe","uairjw","itufkt","kaklud","jjinfa","kqbttl","zocgux","ucwjig","meesxb","uysfyc","kdfvtw","vizxrv","rpbdjh","wynohw","lhqxvx","kaadty","dxxwut","vjtskm","yrdswc","byzjxm","jeomdc","saevda","himevi","ydltnu","wrrpoc","khuopg","ooxarg","vcvfry","thaawc","bssybb","ccoyyo","ajcwbj","arwfnl","nafmtm","xoaumd","vbejda","kaefne","swcrkh","reeyhj","vmcwaf","chxitv","qkwjna","vklpkp","xfnayl","ktgmfn","xrmzzm","fgtuki","zcffuv","srxuus","pydgmq"};
        GuessTheWord2 solution = new GuessTheWord2();
        solution.findSecretWord(wordlist, master);

        master = new Master("anqomr");
        String[] wordlist2 = {"pzrooh","aaakrw","vgvkxb","ilaumf","snzsrz","qymapx","hhjgwz","mymxyu","jglmrs","yycsvl","fuyzco","ivfyfx","hzlhqt","ansstc","ujkfnr","jrekmp","himbkv","tjztqw","jmcapu","gwwwmd","ffpond","ytzssw","afyjnp","ttrfzi","xkwmsz","oavtsf","krwjwb","bkgjcs","vsigmc","qhpxxt","apzrtt","posjnv","zlatkz","zynlqc","czajmi","smmbhm","rvlxav","wkytta","dzkfer","urajfh","lsroct","gihvuu","qtnlhu","ucjgio","xyycvd","fsssoo","kdtmux","bxnppe","usucra","hvsmau","gstvvg","ypueay","qdlvod","djfbgs","mcufib","prohkc","dsqgms","eoasya","kzplbv","rcuevr","iwapqf","ucqdac","anqomr","msztnf","tppefv","mplbgz","xnskvo","euhxrh","xrqxzw","wraxvn","zjhlou","xwdvvl","dkbiys","zbtnuv","gxrhjh","ctrczk","iwylwn","wefuhr","enlcrg","eimtep","xzvntq","zvygyf","tbzmzk","xjptby","uxyacb","mbalze","bjosah","ckojzr","ihboso","ogxylw","cfnatk","zijwnl","eczmmc","uazfyo","apywnl","jiraqa","yjksyd","mrpczo","qqmhnb","xxvsbx"};
        solution.findSecretWord(wordlist2, master);
    }

        public void findSecretWord(String[] wordlist, Master master) {
            int N = wordlist.length;
            H = new int[N][N];
            for (int i = 0; i < N; ++i)
                for (int j = i; j < N; ++j) {
                    int match = 0;
                    for (int k = 0; k < 6; ++k)
                        if (wordlist[i].charAt(k) == wordlist[j].charAt(k))
                            match++;
                    H[i][j] = H[j][i] = match;
                }

            List<Integer> possible = new ArrayList();
            List<Integer> path = new ArrayList();
            for (int i = 0; i < N; ++i) possible.add(i);

            while (!possible.isEmpty()) {
                int guess = solve(possible, path);
                int matches = master.guess(wordlist[guess]);
                if (matches == wordlist[0].length()) return;
                List<Integer> possible2 = new ArrayList();
                for (Integer j: possible) if (H[guess][j] == matches) possible2.add(j);
                possible = possible2;
                path.add(guess);
            }

        }

        public int solve(List<Integer> possible, List<Integer> path) {
            if (possible.size() <= 2) return possible.get(0);
            List<Integer> ansgrp = possible;
            int ansguess = -1;

            for (int guess = 0; guess < H.length; ++guess) {
                if (!path.contains(guess)) {
                    ArrayList<Integer>[] groups = new ArrayList[7];
                    for (int i = 0; i < 7; ++i) groups[i] = new ArrayList<Integer>();
                    for (Integer j : possible)
                        if (j != guess) {
                            groups[H[guess][j]].add(j);
                        }

                    ArrayList<Integer> maxgroup = groups[0];
                    for (int i = 0; i < 7; ++i)
                        if (groups[i].size() > maxgroup.size())
                            maxgroup = groups[i];

                    if (maxgroup.size() < ansgrp.size()) {
                        ansgrp = maxgroup;
                        ansguess = guess;
                    }
                }
            }

            return ansguess;
        }

}
