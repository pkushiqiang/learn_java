package airbnb;

public class FindMedianInLargeFile2 {

    public double findMedian(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int n: nums) {
            min = Math.min(n, min);
            max = Math.max(n, max);
        }

        while(true) {
            double med = ((double)(min + max))/2;
            int left = 0, right =0;
            int leftMax = Integer.MIN_VALUE;
            int rightMin = Integer.MAX_VALUE;
            for(int n: nums) {
                 if (n <= med) {
                     leftMax = Math.max(leftMax, n);
                     left++;
                 }
                 if (n >= med) {
                     rightMin = Math.min(rightMin, n);
                     right++;
                 }
            }

            if (left == right) {
                return ((double)(leftMax + rightMin))/2;
            }

            if (left - right == 1 ) {
                return leftMax;
            }

            if (right - left == 1) {
                return rightMin;
            }

            if (left > right) {
                max = leftMax;
            } else {
                min = rightMin;
            }
        }

    }

    public static void main(String[] args) {
        FindMedianInLargeFile  solution = new FindMedianInLargeFile();
        FindMedianInLargeFile2 solution2 = new FindMedianInLargeFile2();
        int[] a1 = {1};
        int[] a = {1,2,3,4};
        int[] b = {1,2,3,4,8,23,89,194,-987,3,9,34,334,9493,-343,3234,35,3,4,3,4353943,1,324,19,22,33,889,97};
        int[] b2 = {1,2,3,3,3,3, 4,5,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6};
        int[] nums = b2;
        System.out.println(solution.findMedian(nums));
        System.out.println(solution2.findMedian(nums));
    }
}
