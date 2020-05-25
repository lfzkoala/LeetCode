/*
Find the Celebrity
        Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
        Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
        You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.

        Example 1:

        Input: graph = [
        [1,1,0],
        [0,1,0],
        [1,1,1]
        ]
        Output: 1
        Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.

        Example 2:

        Input: graph = [
        [1,0,1],
        [1,1,0],
        [0,1,1]
        ]
        Output: -1
        Explanation: There is no celebrity.


        Note:
        The directed graph is represented as an adjacency matrix, which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
        Remember that you won't have direct access to the adjacency matrix.
        方法1：

 */
public class Solution extends Relation {
    public int findCelebrity(int n) {

        int candidate = 0;
        //Find the candidate. Once there is nobody he knows, the candidate is fixed. Then we get a candidate.
        for(int i=1; i<n; i++){
            if(knows(candidate, i)) candidate = i;
        }

        //check whether the candidate is valid
        for(int i=0; i<n; i++){
            //a candidate should be that all the other n-1 people know him but he does not know any of them. Thus if we find the candidate knows some other node, then return -1, then if some other people does not know him, return -1.
            if(i != candidate && knows(candidate, i)) return -1;
            if(i != candidate && !knows(i, candidate)) return -1;
        }

        return candidate;

    }
}

/*
方法2：使用stack

 */
public int findCelebrity(int n) {
        // base case
        if (n <= 0) return -1;
        if (n == 1) return 0;

        Stack<Integer> stack = new Stack<>();

        // put all people to the stack
        for (int i = 0; i < n; i++) stack.push(i);

        int a = 0, b = 0;

        while (stack.size() > 1) {
        a = stack.pop(); b = stack.pop();

        if (knows(a, b))
        // a knows b, so a is not the celebrity, but b may be
        stack.push(b);
        else
        // a doesn't know b, so b is not the celebrity, but a may be
        stack.push(a);
        }

        // double check the potential celebrity
        int c = stack.pop();

        for (int i = 0; i < n; i++)
        // c should not know anyone else
        if (i != c && (knows(c, i) || !knows(i, c)))
        return -1;

        return c;
        }
