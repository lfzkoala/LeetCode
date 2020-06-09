/*
Flatten Nested List Iterator
        Given a nested list of integers, implement an iterator to flatten it.

        Each element is either an integer, or a list -- whose elements may also be integers or other lists.

        Example 1:

        Input: [[1,1],2,[1,1]]
        Output: [1,1,2,1,1]
        Explanation: By calling next repeatedly until hasNext returns false,
        the order of elements returned by next should be: [1,1,2,1,1].
        Example 2:

        Input: [1,[4,[6]]]
        Output: [1,4,6]
        Explanation: By calling next repeatedly until hasNext returns false,
        the order of elements returned by next should be: [1,4,6].

        方法：这道题让我们建立压平嵌套链表的迭代器，关于嵌套链表的数据结构最早出现在Nested List Weight Sum中，而那道题是用的递归的方法来解的，而迭代器一般都是用迭代的方法来解的，而递归一般都需用栈来辅助遍历，由于栈的后进先出的特性，我们在对向量遍历的时候，从后往前把对象压入栈中，那么第一个对象最后压入栈就会第一个取出来处理，我们的hasNext()函数需要遍历栈，并进行处理，如果栈顶元素是整数，直接返回true，如果不是，那么移除栈顶元素，并开始遍历这个取出的list，还是从后往前压入栈，循环停止条件是栈为空，返回false.


 */
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack = new Stack<>();

    public NestedIterator(List<NestedInteger> nestedList) {

        for(int i=nestedList.size()-1; i>=0; i--){
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();

    }

    @Override
    public boolean hasNext() {

        while(!stack.isEmpty()){
            NestedInteger cur = stack.peek();
            if(cur.isInteger()){
                return true;
            }
            stack.pop();
            for(int i = cur.getList().size()-1; i>=0; i--){
                stack.push(cur.getList().get(i));
            }
        }

        return false;
    }
}
