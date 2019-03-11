package airbnb;

public class FindMedianInLargeFile {

    private long search(int[] nums, int k, long left, long right) {
        if (left >= right) {
            return left;
        }
        long res = left;
        long guess = left + (right - left) / 2;
        int count = 0;
        for (int num : nums) {
            if (num <= guess) {
                count++;
                res = Math.max(res, num);
            }
        }
        if (count == k) {
            return res;
        } else if (count < k) {
            return search(nums, k, Math.max(res + 1, guess), right);
        } else {
            return search(nums, k, left, res);
        }
    }

    public double findMedian(int[] nums) {
        int len = 0;
        for (int num : nums) {
            len++;
        }
        if (len % 2 == 1) {
            return (double) search(nums, len / 2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
        } else {
            return (double) (search(nums, len / 2, Integer.MIN_VALUE, Integer.MAX_VALUE) +
                    search(nums, len / 2 + 1, Integer.MIN_VALUE,
                            Integer.MAX_VALUE)) / 2;
        }
    }

    public static void main(String[] args) {
        FindMedianInLargeFile solution = new FindMedianInLargeFile();
        int[] a1 = {1};
        int[] a = {1,2,3,4};
        int[] b = {1,2,3,4,8,23,89,194,-987,3,9,34,334,9493,-343,3234,35,3,4,3,4353943,1,324,19,22,33,889,97};
        System.out.println(solution.findMedian(a));
    }

}
