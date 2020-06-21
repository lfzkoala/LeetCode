/*
Minimum Knight Moves
        In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
        A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

        Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

        Example 1:
        Input: x = 2, y = 1
        Output: 1
        Explanation: [0, 0] → [2, 1]

        Example 2:
        Input: x = 5, y = 5
        Output: 4
        Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]
        答案：需要知道方法 要不很难做出来

        I manually draw this graph, although it is a lot of work and is perhaps too crazy for an intervew:

        Since it is symmetric vertically, horizontally and diagonally, we can format x and y in the following way, so that every target node falls into the region enclosesd by the black lines:
        x = Math.abs(x);
        y = Math.abs(y);
        if (x < y) {
        int tmp = x;
        x = y;
        y = tmp;
        }

        Then for all the white blocks, we can just hardcode their values, because they are sort of not regular.
        ......
private static int[][] localRegion = {
        {0, 3, 2},
        {3, 2, 1},
        {2, 1, 4}
        };

        ......
        if (x <= 2 && y <= 2)
        return localRegion[x][y];

        For the colored blocks, they are apparently grouped and each group shares a same pattern.
        Lets say that the yellow blocks are Group 2, and the green blocks are Group 3, then the blocks in Group T has values T and (T + 1) interpolated.
        For a block (x, y), its value should be:
        groupId + ((x + y + groupId) % 2)

        Then the only problem remained is how to determine the Group id of a block. The ways of computing Group id are actually different for the following two regions:

        In the vertical region, you move a blockward right two times to enter a new group, so the group id should be this (notice that Group T should have blocks with value T and (T + 1)):
        groupId = (x - 1) / 2 + 1;

        In the diagonal region, you move a block three times either upward or rightward , then you enter a new block, so the group id should be:
        groupId = (x + y - 2) / 3 + 1;

        That grey line has a slope 0.5 and pass through the block (3, 3), so to distinguish two regions, we do:
        int groupId;
        if ((x - 3) >= (y - 3) * 2)
        groupId = (x - 1) / 2 + 1;
        else
        groupId = (x + y - 2) / 3 + 1;

        Then we get the final code:

 */
class Solution {
    private static int[][] localRegion = {
            {0, 3, 2},
            {3, 2, 1},
            {2, 1, 4}
    };

    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        if (x < y) {
            int tmp = x;
            x = y;
            y = tmp;
        }
        if (x <= 2 && y <= 2)
            return localRegion[x][y];

        int groupId;
        if ((x - 3) >= (y - 3) * 2)
            groupId = (x - 1) / 2 + 1;
        else
            groupId = (x + y - 2) / 3 + 1;

        return groupId + ((x + y + groupId) % 2);
    }
}
