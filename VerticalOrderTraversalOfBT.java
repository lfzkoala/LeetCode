/*
Vertical Order Traversal of a Binary Tree
        Given a binary tree, return the vertical order traversal of its nodes values.
        For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).
        Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).
        If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.
        Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.

        Example 1:

        Input: [3,9,20,null,null,15,7]
        Output: [[9],[3,15],[20],[7]]
        Explanation:
        Without loss of generality, we can assume the root node is at position (0, 0):
        Then, the node with value 9 occurs at position (-1, -1);
        The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
        The node with value 20 occurs at position (1, -1);
        The node with value 7 occurs at position (2, -2).

        Example 2:

        Input: [1,2,3,4,5,6,7]
        Output: [[4],[2],[1,5,6],[3],[7]]
        Explanation:
        The node with value 5 and the node with value 6 have the same position according to the given scheme.
        However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.


        Note:
        The tree will have between 1 and 1000 nodes.
        Each node's value will be between 0 and 1000.

        方法：按照坐标方位定义每一个node的坐标 坐标由class Point定义 每个point对应的value是node value。 定义一个PriorityQueue 排序的规则根据从左到右从上到下的规则进行 从(0,0)这个node进行DFS将每个point/node加入到PriorityQueue中 最后按照PriorityQueue中定义的顺序将node加入到最后的result list中


 */

class Point{

    int x, y, val;
    Point(int x, int y, int val){
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        //define PriorityQueue, this priority queue poll the node based on the order described, such as (-1,-1), (0,0) and (1,-1).
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>(){
            public int compare(Point p1, Point p2){

                if(p1.x != p2.x){
                    return p1.x-p2.x;
                }else if(p1.y != p2.y){
                    return p2.y - p1.y;
                }else{
                    return p1.val - p2.val;
                }

            }
        });

        helper(root, 0, 0, pq);
        int prevX = Integer.MIN_VALUE;

        while(!pq.isEmpty()){
            Point p = pq.poll();
            if(p.x > prevX){
                List<Integer> list = new ArrayList<>();
                list.add(p.val);
                result.add(list);
            }else{
                List<Integer> list = result.get(result.size()-1);
                list.add(p.val);
            }

            prevX = p.x; //don't forget to update prevX.
        }

        return result;
    }

    private void helper(TreeNode root, int x, int y, PriorityQueue<Point> pq){
        if(root == null) return;
        pq.offer(new Point(x,y,root.val));
        helper(root.left, x-1, y-1, pq);
        helper(root.right, x+1, y-1, pq);
    }

}
