/*
Sliding Window Maximum

        Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
        Follow up:
        Could you solve it in linear time?
        Example:
        Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
        Output: [3,3,5,5,6,7]
        Explanation:

        Window position                Max
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
        1 [3  -1  -3] 5  3  6  7       3
        1  3 [-1  -3  5] 3  6  7      5
        1  3  -1 [-3  5  3] 6  7       5
        1  3  -1  -3 [5  3  6] 7       6
        1  3  -1  -3  5 [3  6  7]      7


        Constraints:
        1 <= nums.length <= 10^5
        -10^4 <= nums[i] <= 10^4
        1 <= k <= nums.length

        方法1：Brute Force

        time complexity O(Nk)
        space complexity O(1)

        check every sliding window and each maximum value

 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // assume nums is not null
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[0];
        }

        int numOfWindow = n - k + 1;
        int[] result = new int[numOfWindow]; // number of windows

        for (int start = 0; start < numOfWindow; ++start) {
            int end = start + k - 1;
            int maxVal = nums[start];
            for (int i = start + 1; i <= end; ++i) {
                if (nums[i] > maxVal) { // update
                    maxVal = nums[i];
                }
            }
            result[start] = maxVal;
        }

        return result;
    }
}
/*
方法2：Priority Queue

        time complexity: O(Nk), if we implement PQ by ourselves, the complexity is O(N/logk)
        space complexity: O(k)


 */
// Use indices since they are unique

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            // assume nums is not null
            if (nums.length == 0 || k == 0) {
                return new int[0];
            }
            int n = nums.length;
            int[] result = new int[n - k + 1]; // number of windows

            PriorityQueue<Integer> maxPQ = new PriorityQueue<>((i1, i2) -> (nums[i2] - nums[i1])); // stores values

            for (int i = 0; i < n; ++i) {
                int start = i - k;
                if (start >= 0) {
                    maxPQ.remove(start); // remove the out-of-bound value by index
                }
                maxPQ.offer(i);
                if (maxPQ.size() == k) {
                    result[i - k + 1] = nums[maxPQ.peek()];
                }
            }
            return result;
        }
    }

    /*
    方法3：Deque (The best one)


        time complexity O(N)
        space complexity O(k)



        If we can add and remove elements from both sides of the sliding window, we can solve this problem in linear time. It turns out that we can use Deque to achieve the goal. In the Deque, we add and remove indices.



        Basically, for each element nums[i] in the array that we are about to insert, we first check whether the leftmost index in the sliding window is out of bound. If so, we remove it by pollFirst() in constant time.

        Then we continuously remove the rightmost indices if their corresponding elements are less than nums[i] (the element we are about to insert). The idea is that the elements that are less than the element we'll insert won't have any contributions to the maximum element of the sliding window. So it is safe to remove them.

        After removal pollLast() and insertion offerLast(i) (the element nums[i]), we can say that the leftmost element in the window is maximum. Think about it why. Notice that the maximum element could be the one we just insert.

        Last but not least, adding the maximum elements to the result array when necessary.

     */

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        // assume nums is not null
        int n = nums.length;
        if (n == 0 || k == 0) {
            return new int[0];
        }
        int[] result = new int[n - k + 1]; // number of windows
        Deque<Integer> win = new ArrayDeque<>(); // stores indices

        for (int i = 0; i < n; ++i) {
            // remove indices that are out of bound
            while (win.size() > 0 && win.peekFirst() <= i - k) {
                win.pollFirst();
            }
            // remove indices whose corresponding values are less than nums[i]
            while (win.size() > 0 && nums[win.peekLast()] < nums[i]) {
                win.pollLast();
            }
            // add nums[i]
            win.offerLast(i);
            // add to result
            if (i >= k - 1) {
                result[i - k + 1] = nums[win.peekFirst()];
            }
        }
        return result;
    }
}

//        方法4：DP this method is very hacky….omit
