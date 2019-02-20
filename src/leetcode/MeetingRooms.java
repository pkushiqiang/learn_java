package leetcode;



public class MeetingRooms {

    public class Interval {
       int start;
       int end;
       Interval() { start = 0; end = 0; }
       Interval(int s, int e) { start = s; end = e; }
   }

    class Node {

        Interval interval;
        Node left, right;
        public Node(Interval interval){
            this.interval = interval;
        }

        public void insert(Interval interval2) {
            if (overlap(this.interval, interval2)) {
                throw new RuntimeException("over lap");
            } else {
                if(interval2.start < this.interval.start) {
                    if (this.left != null) {
                        this.left.insert(interval2);
                    } else {
                        this.left = new Node(interval2);
                    }
                } else {
                    if (this.right != null) {
                        this.right.insert(interval2);
                    } else {
                        this.right = new Node(interval2);
                    }
                }
            }
        }

        private boolean overlap(Interval i1, Interval i2) {
            return ((i1.start >= i2.start && i1.start < i2.end)
                    || (i2.start >= i1.start && i2.start < i1.end));
        }
    }

    public boolean canAttendMeetings(Interval[] intervals) {
         Node root = new Node(intervals[0]);
         try {
             for (int i = 1; i < intervals.length; i++) {
                 root.insert(intervals[i]);
             }
         } catch(Exception e) {
             return false;
         }

         return true;
    }

}
