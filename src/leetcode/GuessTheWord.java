package leetcode;

import java.util.ArrayList;
import java.util.List;

class Master {
    String key;
    public Master(String key) {
        this.key = key;
    }

    public int guess(String str1) {
        int a =0;
        for(int i=0; i<key.length(); i++) {
            if(str1.charAt(i) == key.charAt(i)) {
                a++;
            }
        }
        return a;
    }
}

public class GuessTheWord {

    List<String> list;
    public void findSecretWord(String[] wordlist, Master master) {
        list = new ArrayList<>();
        for(int i=0; i<wordlist.length; i++) {
            list.add(wordlist[i]);
        }

        for(int i=0;i<10;i++) {
            if(filter(master))
                return;
        }
    }

    private boolean filter(Master master) {
        String str = list.get(0);
        List<String> newlist = new ArrayList<>();
        int v = master.guess(str);
        if (v==6)
            return true;

        for(int i=1; i<list.size();i++) {
            if(match(str, list.get(i)) == v) {
                newlist.add(list.get(i));
            }
        }
        list = newlist;
        return false;
    }

    private int match(String str1, String str2) {
        int a =0;
        for(int i=0; i<str1.length(); i++) {
            if(str1.charAt(i) == str2.charAt(i)) {
                a++;
            }
        }
        return a;
    }

    public static void main(String[] args) {

        Master master = new Master("ccoyyo");
        String[] wordlist = {"wichbx","oahwep","tpulot","eqznzs","vvmplb","eywinm","dqefpt","kmjmxr","ihkovg","trbzyb","xqulhc","bcsbfw","rwzslk","abpjhw","mpubps","viyzbc","kodlta","ckfzjh","phuepp","rokoro","nxcwmo","awvqlr","uooeon","hhfuzz","sajxgr","oxgaix","fnugyu","lkxwru","mhtrvb","xxonmg","tqxlbr","euxtzg","tjwvad","uslult","rtjosi","hsygda","vyuica","mbnagm","uinqur","pikenp","szgupv","qpxmsw","vunxdn","jahhfn","kmbeok","biywow","yvgwho","hwzodo","loffxk","xavzqd","vwzpfe","uairjw","itufkt","kaklud","jjinfa","kqbttl","zocgux","ucwjig","meesxb","uysfyc","kdfvtw","vizxrv","rpbdjh","wynohw","lhqxvx","kaadty","dxxwut","vjtskm","yrdswc","byzjxm","jeomdc","saevda","himevi","ydltnu","wrrpoc","khuopg","ooxarg","vcvfry","thaawc","bssybb","ccoyyo","ajcwbj","arwfnl","nafmtm","xoaumd","vbejda","kaefne","swcrkh","reeyhj","vmcwaf","chxitv","qkwjna","vklpkp","xfnayl","ktgmfn","xrmzzm","fgtuki","zcffuv","srxuus","pydgmq"};
        GuessTheWord solution = new GuessTheWord();
        solution.findSecretWord(wordlist, master);

        master = new Master("anqomr");
        String[] wordlist2 = {"pzrooh","aaakrw","vgvkxb","ilaumf","snzsrz","qymapx","hhjgwz","mymxyu","jglmrs","yycsvl","fuyzco","ivfyfx","hzlhqt","ansstc","ujkfnr","jrekmp","himbkv","tjztqw","jmcapu","gwwwmd","ffpond","ytzssw","afyjnp","ttrfzi","xkwmsz","oavtsf","krwjwb","bkgjcs","vsigmc","qhpxxt","apzrtt","posjnv","zlatkz","zynlqc","czajmi","smmbhm","rvlxav","wkytta","dzkfer","urajfh","lsroct","gihvuu","qtnlhu","ucjgio","xyycvd","fsssoo","kdtmux","bxnppe","usucra","hvsmau","gstvvg","ypueay","qdlvod","djfbgs","mcufib","prohkc","dsqgms","eoasya","kzplbv","rcuevr","iwapqf","ucqdac","anqomr","msztnf","tppefv","mplbgz","xnskvo","euhxrh","xrqxzw","wraxvn","zjhlou","xwdvvl","dkbiys","zbtnuv","gxrhjh","ctrczk","iwylwn","wefuhr","enlcrg","eimtep","xzvntq","zvygyf","tbzmzk","xjptby","uxyacb","mbalze","bjosah","ckojzr","ihboso","ogxylw","cfnatk","zijwnl","eczmmc","uazfyo","apywnl","jiraqa","yjksyd","mrpczo","qqmhnb","xxvsbx"};
        solution.findSecretWord(wordlist2, master);
    }

}
