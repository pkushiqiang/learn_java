package airbnb;

public class TextJustification {

    public void printLines(String[] strs) {

        int max = strs[0].length();

        for(String s : strs) {
            if (s.length()> max)
                max = s.length();
        }

        for(int i=0; i<strs.length; i++) {
            printLine(max);
            printText(max, strs[i]);
        }
        printLine(max);

    }

    private void printLine(int len) {
        System.out.print("+");
        for(int i=0; i<len;i++)
            System.out.print("-");
        System.out.println("+");
    }

    private void printText(int len, String str) {
        System.out.print("|");
        System.out.print(str);
        for(int i=0; i<len-str.length();i++)
            System.out.print(" ");
        System.out.println("|");
    }

    public static void main(String[] args) {
        TextJustification solution = new TextJustification();
        String[] strs = {
                "dfds dsfdsa",
                "dfse",
                "dfds dsfds 12  11",
                "asd f fwefsd eere"
        };

        solution.printLines(strs);
    }
}
