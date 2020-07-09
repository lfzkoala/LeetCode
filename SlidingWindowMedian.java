/*
Sliding Window Median

        Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
        Examples:
        [2,3,4] , the median is 3
        [2,3], the median is (2 + 3) / 2 = 2.5
        Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.
        For example,
        Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
        Window position                Median
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       1
        1 [3  -1  -3] 5  3  6  7       -1
        1  3 [-1  -3  5] 3  6  7       -1
        1  3  -1 [-3  5  3] 6  7       3
        1  3  -1  -3 [5  3  6] 7       5
        1  3  -1  -3  5 [3  6  7]      6

        Therefore, return the median sliding window as [1,-1,-1,3,5,6].
        Note:
        You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
        Answers within 10^-5 of the actual value will be accepted as correct.
        方法1：using priority queue
        Almost the same idea of Find Median from Data Stream https://leetcode.com/problems/find-median-from-data-stream/

        Use two Heaps to store numbers. maxHeap for numbers smaller than current median, minHeap for numbers bigger than and equal to current median. A small trick I used is always make size of minHeap equal (when there are even numbers) or 1 element more (when there are odd numbers) than the size of maxHeap. Then it will become very easy to calculate current median.
        Keep adding number from the right side of the sliding window and remove number from left side of the sliding window. And keep adding current median to the result.
        time complexity O(nk)

        掌握这个逻辑比较重要 TreeSet的方法可能有些偏 TreeSet的调用方法不太方便

 */

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {

        int n = nums.length-k+1; //number of medians to be added
        if(n <= 0) return new double[0];
        double[] result = new double[n];


        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
                new Comparator<Integer>(){
                    public int compare(Integer a, Integer b){
                        return b.compareTo(a);
                    }
                }
        ); //define maxHeap

        for(int i=0; i<=nums.length; i++){
            if(i >= k){
                result[i-k] = getMedian(minHeap, maxHeap); //get median number
                removeNum(nums[i-k], minHeap, maxHeap); //remove the first number in the sliding window if we are going to move to next window.
            }

            if(i < nums.length){
                addNum(nums[i], minHeap, maxHeap); //add Number to heaps every round
            }

        }

        return result;
    }

    private double getMedian(PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap){
        if(minHeap.isEmpty() && maxHeap.isEmpty()) return 0;

        if(minHeap.size() == maxHeap.size()){
            return ((double)minHeap.peek() + (double)maxHeap.peek())/2.0; //dont forget (double), otherwise it returns wrong answer
        }else{
            return (double) minHeap.peek();
        }
    }

    private void removeNum(int num, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap){
        if(num < getMedian(minHeap, maxHeap)){
            maxHeap.remove(num);
        }else{
            minHeap.remove(num);
        }

        //balance minHeap and maxHeap
        if(minHeap.size() < maxHeap.size()){
            minHeap.add(maxHeap.poll());
        }

        if(minHeap.size()-maxHeap.size() > 1){
            maxHeap.add(minHeap.poll());
        }


    }

    private void addNum(int num, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap){
        if(num < getMedian(minHeap, maxHeap)){
            maxHeap.add(num);
        }else{
            minHeap.add(num);
        }

        //balance minHeap and maxHeap
        if(minHeap.size() < maxHeap.size()){
            minHeap.add(maxHeap.poll());
        }

        if(minHeap.size()-maxHeap.size() > 1){
            maxHeap.add(minHeap.poll());
        }
    }

}

/*
方法2：improve time complexity to O(nlogk) using tree sets.
        instead of using two priority queue's we use two Tree Sets as we want O(logk) for remove(element). Priority Queue would have been O(k) for remove(element) giving us an overall time complexity of O(nk) instead of O(nlogk).

        However there is an issue when it comes to duplicate values so to get around this we store the index into nums in our Tree Set. To break ties in our Tree Set comparator we compare the index.


 */
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        Comparator<Integer> comparator = new Comparator<>(){
            public int compare(Integer a, Integer b) {
                if (nums[a] != nums[b]) {
                    return Integer.compare(nums[a], nums[b]);
                }
                else {
                    return a - b;
                }
            }
        };
        TreeSet<Integer> maxHeap = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> minHeap = new TreeSet<>(comparator);
        double[] res = new double[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            maxHeap.add(i);
        }
        balance(maxHeap, minHeap);
        res[0] = getMedian(k, nums, maxHeap, minHeap);

        int r = 1;
        for (int i = k; i < nums.length; i++) {
            if(!maxHeap.remove(i - k)) {
                minHeap.remove(i - k);
            }
            minHeap.add(i);
            maxHeap.add(minHeap.pollFirst());
            balance(maxHeap, minHeap);
            res[r] = getMedian(k, nums, maxHeap, minHeap);
            r++;
        }

        return res;
    }

    private void balance(TreeSet<Integer> maxHeap, TreeSet<Integer> minHeap) {
        while (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.pollFirst());
        }
    }

    private double getMedian(int k, int[] nums, TreeSet<Integer> maxHeap, TreeSet<Integer> minHeap) {
        if (k % 2 == 0) {
            return ((double) nums[maxHeap.first()] + nums[minHeap.first()]) / 2;
        }
        else {
            return (double) nums[minHeap.first()];
        }
    }
}
