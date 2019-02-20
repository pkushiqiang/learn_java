package airbnb;

public class RegularExperssion {

    public boolean regMatch(String source, String pattern) {
        if (pattern.length() == 0) return source.length() == 0;
        if (pattern.length() == 1) {
            if (source.length() > 1 || source.length() == 0) return false;
            return source.charAt(0) == pattern.charAt(0);
        }
        if (source.length() != 0 && (pattern.charAt(0) == '.' || pattern.charAt(0)
                == source.charAt(0))) {
            if (pattern.charAt(1) == '*') {
                return regMatch(source.substring(1), pattern) || regMatch(source,
                        pattern.substring(2));
            } else if (pattern.charAt(1) == '+') {
                return regMatch(source.substring(1), pattern.substring(2)) ||
                        regMatch(source.substring(1), pattern.substring(2));
            } else {
                return regMatch(source.substring(1), pattern.substring(1));
            }
        }
        return pattern.charAt(1) == '*' && regMatch(source, pattern.substring(2));
    }

    public static void main(String[] args) {
        RegularExperssion solution = new RegularExperssion();
        System.out.println(solution.regMatch("1", "1"));
        System.out.println(solution.regMatch("12", "1."));
        System.out.println(solution.regMatch("12", "*"));
    }

}
