/*
Minimum Cost Tree From Leaf Values
        Given an array arr of positive integers, consider all binary trees such that:
        Each node has either 0 or 2 children;
        The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
        The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
        Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

        Example 1:
        Input: arr = [6,2,4]
        Output: 32
        Explanation:
        There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

        24            24
        /  \          /  \
        12   4        6    8
        /  \               / \
        6    2             2   4


        Constraints:
        2 <= arr.length <= 40
        1 <= arr[i] <= 15
        It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).
        答案：
        题目要求使乘积的和最小，贪心算法为选择节点两端的最小的节点合并为一颗子树，构建价值为两者的乘积，使用单调递减栈来进行遍历
        根据题意我们知道，每次两个相邻元素组成一颗子树后，会将较小的元素删去，留下较大的元素，所以我们的目标就是每次删除局部最小的那个元素，比如[6,2,4]中2就是局部最小。我们将局部最小的元素和两边较小的元素相乘加入答案，同时将这个局部最小的元素抹去。

        [6,2,4,8] res = 0
        [6,4,8] res = 8
        [6,8] res = 8+24
        [8] res = 8+24+48

        考虑使用一个单调递减栈来存储数组元素，如果当前元素小于等于栈顶元素直接入栈；否则，说明当前栈顶元素是一个局部最小值，我们用这个局部最小值的两端较小与栈顶元素相乘，同时将栈顶元素出栈，重复上述操作，直至栈顶元素不是局部最小值，再将当前元素入栈，处理完整个数组后，如果栈中还有多的元素，我们从栈顶依次往下乘加入答案即可

        为了处理边界情况，先在栈中插入一个Integer.MAX_VALUE


 */
class Solution {
    public int mctFromLeafValues(int[] arr) {
        //用一个单调递减栈先处理两个最小值使得他们生成一个node
        Stack<Integer> stack = new Stack<>();

        stack.add(Integer.MAX_VALUE);
        int ans = 0;


        for(int i=0; i<arr.length; i++){
            while(stack.peek() < arr[i]){//注意这里是while不是if
                int t = stack.pop();
                ans += t*Math.min(stack.peek(), arr[i]); //保证第一小乘以第二小，第二小乘以第三小。。。。最后把这些乘法结果相加
            }

            stack.push(arr[i]);
        }

        while(stack.size()>2){
            int t = stack.pop();
            ans += t*stack.peek();
        }

        return ans;
    }
}
