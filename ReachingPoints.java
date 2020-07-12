/*
Reaching Points

        A move consists of taking a point (x, y) and transforming it to either (x, x+y) or (x+y, y).
        Given a starting point (sx, sy) and a target point (tx, ty), return True if and only if a sequence of moves exists to transform the point (sx, sy) to (tx, ty). Otherwise, return False.
        Examples:
        Input: sx = 1, sy = 1, tx = 3, ty = 5
        Output: True
        Explanation:
        One series of moves that transforms the starting point to the target is:
        (1, 1) -> (1, 2)
        (1, 2) -> (3, 2)
        (3, 2) -> (3, 5)

        Input: sx = 1, sy = 1, tx = 2, ty = 2
        Output: False

        Input: sx = 1, sy = 1, tx = 1, ty = 1
        Output: True


        Note:
        sx, sy, tx, ty will all be integers in the range [1, 10^9].
        方法1：Naive thinking

        If you observe that you have two actions to take at any moment for (x,y)
        (x , x+y)
        (x+y, y)
        That means, we can have two choices at (x,y) which forms it like a binary tree. And one of the leaf node probably consist the (tx, ty)
        Top Down:
        Do same as ask, run from top to bottom of binary tree. Find the leaf node which satisfy the condition.
        sx=1, sy=1, tx=3, ty=5
        Visulatization

        Complexity: The height of binary tree depends on tx,ty. All the values which are either greater then tx or ty will be discarded as from that we can't reach the tx,ty.
        Hence, the height of tree would be Max(tx,ty)= N ..total complexity O(2^N)

 */
public static boolean reachingPoints(int sx, int sy, int tx, int ty) {

        if (sx == tx && sy == ty)
        return true;

        if (sx > tx || sy > ty)
        return false;

        return (reachingPoints(sx + sy, sy, tx, ty) || reachingPoints(sx, sx + sy, tx, ty));
        }

        /*
        方法2：
        Optimization : Bottom up
        In above approach, we need to drill down till the tx or ty. If you see, for each child, there is only 1 way to reach parent (eventually root) in binary tree.
        Which means, instead of starting from (sx,sy) and go down, we can start from (tx,ty) and go up till you hit one of the condition like sx >= tx or sy>= ty {revers of top down condition}
        then you from that point you can simply check does it is possible to reach or not.
        or sx=1, sy=1, tx=3, ty=5

        now we met the base condition, [1,2] can only be translate for [1,1] when ty >= sy {2>1} and (ty-sy)%sx == 0 { (2-1)%1 == 0}
        why (ty-sy)%sx == 0?
        since
        sy will translate to ty only by (sx+sy), if they translate then(sx, sy+k*sx) = ty for some k
        sy+k*sx = ty => (ty-sy) / sx = k.
        Since sx,sy,tx,ty are all integer, then k has to be a integer which means, there must be a integer k that help us to reach ty. Which makes reminder to be 0
        Hence
        (ty-sy) % sx == 0
        Complexity : O(log(n)) where n = Max(tx,ty)

         */
class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {

        while(sx < tx && sy < ty){
            if(tx < ty){
                ty %= tx;
            }else{
                tx %= ty;
            }
        }

        if(sx == tx && sy <= ty && (ty-sy)%sx == 0) return true;

        if(sy == ty && sx <= tx && (tx-sx)%sy == 0) return true;

        return false;
    }
}
