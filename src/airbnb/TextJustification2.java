package airbnb;

public class TextJustification2 {

    public void printLines(String[] strs, int width) {

        int max = strs[0].length();

        for(String s : strs) {
            if (s.length()> max)
                max = s.length();
        }

        for(int i=0; i<strs.length; i++) {
            printLine(width);
            printText(width, strs[i]);
        }
        printLine(width);

    }

    private void printLine(int len) {
        System.out.print("+");
        for(int i=0; i<len;i++)
            System.out.print("-");
        System.out.println("+");
    }

    private void printText(int width, String str) {
        String[] words = str.split(" ");
        int i = 0;
        StringBuilder sb = new StringBuilder();


        while (i < words.length) {
             sb.append("|");
             int l = 0;
             while(i < words.length && l + words[i].length() <= width) {
                 sb.append(words[i]);
                 l += words[i].length();
                 i++;
                 if (l<width) {
                     sb.append(" ");
                     l++;
                 }

             }
             while(l<width) {
                sb.append(" ");
                 l++;
             }
             sb.append("|");
             System.out.println(sb.toString());
             sb.setLength(0);
        }
    }

    public static void main(String[] args) {
        TextJustification2 solution = new TextJustification2();
        String[] strs = {
                "dfds dsfdsa",
                "dfse",
                "dfds dsfds 12  11",
                "How could I do it with you?"
        };

        solution.printLines(strs, 12);
    }
}
