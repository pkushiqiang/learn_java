package leetcode;


import java.util.*;

class Interval {
   int start;
   int end;
   Interval() { start = 0; end = 0; }
   Interval(int s, int e) { start = s; end = e; }
}

public class EmployeeFreeTime {

    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> all = new ArrayList<>();
        for (List<Interval> list: schedule) {
            for(Interval  interval: list) {
                all.add(interval);
            }
        }

        Collections.sort(all, (Interval in1, Interval in2) -> in1.start - in2.start);
        List<Interval> merged = new ArrayList<>();
        merged.add(all.get(0));
        int p = 0;
        for(int i=1; i<all.size(); i++) {
            Interval last = merged.get(p);
            Interval next = all.get(i);
            if (last.end < next.start) {
                merged.add(next);
                p++;
            } else {
                last.end = Math.max(last.end, next.end);
            }
        }

        List<Interval> res = new  ArrayList<>();
        for(int i=0; i<merged.size()-1; i++) {
            res.add(new Interval(merged.get(i).end, merged.get(i+1).start));
        }
        return res;
    }
}
