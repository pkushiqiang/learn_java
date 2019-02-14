package airbnb;


class CSVParser {
    public static void main(String[] args) {
        CSVParser s = new CSVParser();
        System.out.println(s.parseCSV("John,Smith,john.smith@gmail.com,Los Angeles,1"));
        System.out.println(s.parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0"));
        System.out.println(s.parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1\"Alexandra \"\"Alex,jk\"\"\""));
    }

    private String parseCSV(String str) {
        StringBuilder sb = new StringBuilder();
        boolean quote = false;
        int i = 0;

        while (i < str.length()) {
            char c = str.charAt(i);

            if (c == '"') {
                if (i + 1 < str.length() && str.charAt(i + 1) == '"') {
                    sb.append('"');
                    i++;
                } else {
                    quote = !quote;
                }

            } else if (c == ',') {
                if (quote) {
                    sb.append(',');
                } else {
                    sb.append('|');
                }
            } else {
                sb.append(c);
            }

            i++;
        }

        return sb.toString();
    }
}
