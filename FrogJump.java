/*
Frog Jump

        A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
        Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
        If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
        Note:
        The number of stones is ≥ 2 and is < 1,100.
        Each stone's position will be a non-negative integer < 231.
        The first stone's position is always 0.
        Example 1:
        [0,1,3,5,6,8,12,17]

        There are a total of 8 stones.
        The first stone at the 0th unit, second stone at the 1st unit,
        third stone at the 3rd unit, and so on...
        The last stone at the 17th unit.

        Return true. The frog can jump to the last stone by jumping
        1 unit to the 2nd stone, then 2 units to the 3rd stone, then
        2 units to the 4th stone, then 3 units to the 6th stone,
        4 units to the 7th stone, and 5 units to the 8th stone.

        Example 2:
        [0,1,2,3,4,8,9,11]

        Return false. There is no way to jump to the last stone as
        the gap between the 5th and 6th stone is too large.

        方法1：用HashMap, 貌似这种方法超时了

        Use map to represent a mapping from the stone (not index) to the steps that can be taken from this stone.

        so this will be

        [0,1,3,5,6,8,12,17]

        {17=[], 0=[1], 1=[1, 2], 3=[1, 2, 3], 5=[1, 2, 3], 6=[1, 2, 3, 4], 8=[1, 2, 3, 4], 12=[3, 4, 5]}

        Notice that no need to calculate the last stone.

        On each step, we look if any other stone can be reached from it, if so, we update that stone's steps by adding step, step + 1, step - 1. If we can reach the final stone, we return true. No need to calculate to the last stone.


 */
class Solution {
    public boolean canCross(int[] stones) {

        if(stones.length == 0) return true;

        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        map.put(0, new HashSet<Integer>());
        map.get(0).add(1);

        for(int i=1; i<stones.length; i++){
            map.put(stones[i], new HashSet<Integer>());
        }

        for(int stone: stones){
            for(int step: map.get(stone)){
                int reach = step+stone;
                if(reach == stones[stones.length-1]) return true;
                if(map.get(reach) != null){
                    map.get(reach).add(step);
                    if(step-1>0) map.get(reach).add(step-1);
                    map.get(reach).add(step+1);
                }
            }
        }

        return false;



    }
}
/*
方法2：Dynamic Programming

        stone:          | S1 |    | S2 |        | S3 |     | S4 |
        ____|____|____|____|________|____|_____|____|____________
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        position:"         0         1             3          5             "

        jump size:         1     [0, 1, 2]     [1, 2, 3]

// Suppose we want to know if the frog can reach stone 2 (S2),
// and we know the frog must come from S1,
// dist(S1->S2) = 1 - 0 = 1, and we already know the frog is able to make a jump of size 1 at S1.
// Hence, the frog is able to reach S2, and the next jump would be 0, 1 or 2 units.


// Then, we want to know if the frog can reach stone 3 (S3),
// we know the frog must be at either S1 or S2 before reaching S3,

// If the frog comes from S1, then
// we know dist(S1->S3) = 3 - 0 = 3, and we know frog couldn't make a jump of size 3 at S1.
// So it is not possible the frog can jump from S1 to S3.

// If the frog comes from S2, then
// we know dist(S2->S3) = 3 - 1 = 2, and we know frog could make a jump of size 2 at S2.
// Hence, the frog is able to reach S3, and the next jump would be 1, 2 or 3 units.

// If we repeat doing this for the rest stones, we'll end with something like below:
        Exapme 1:

        index:        0   1   2   3   4   5   6   7
        +---+---+---+---+---+---+---+---+
        stone pos:  | 0 | 1 | 3 | 5 | 6 | 8 | 12| 17|
        +---+---+---+---+---+---+---+---+
        k:          | 1 | 0 | 1 | 1 | 0 | 1 | 3 | 5 |
        |   | 1 | 2 | 2 | 1 | 2 | 4 | 6 |
        |   | 2 | 3 | 3 | 2 | 3 | 5 | 7 |
        |   |   |   |   | 3 | 4 |   |   |
        |   |   |   |   | 4 |   |   |   |
        |   |   |   |   |   |   |   |   |

// Sub-problem and state:
        let dp(i) denote a set containing all next jump size at stone i

// Recurrence relation:
        for any j < i,
        dist = stones[i] - stones[j];
        if dist is in dp(j):
        put dist - 1, dist, dist + 1 into dp(i).

// Now lets make this approach more efficient.
// BECAUSE
// 1. The number of stones is ≥ 2 and is < 1,100.
// 2. The frog is on the first stone and assume the first jump must be 1 unit.
// 3. If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units,

// The maximum jump size the frog can make at each stone if possible is shown as followings:
// stone:      0, 1, 2, 3, 4, 5
// jump size:  1, 2, 3, 4, 5, 6 (suppose frog made jump with size k + 1 at each stone)

// So instead of creating a HashSet for lookup for each stone,
// we can create a boolean array with size of N + 1 (N is the number of stones),
// Like in the given example, at stone 2 the next jump could be 1, 2, 3,
// we can use a bool array to represent this like
// index:    0  1  2  3  4  5  6  7  ...
//          [0, 1, 1, 1, 0, 0, 0, 0, ...]
// index is jump size, boolean value represents if the frog can make this jump.

// Then, the 2D array will be something like below.

        index:        0   1   2   3   4   5   6   7
        +---+---+---+---+---+---+---+---+
        stone pos:  | 0 | 1 | 3 | 5 | 6 | 8 | 12| 17|
        +---+---+---+---+---+---+---+---+
        k:        0 | 0 | 1 | 0 | 0 | 1 | 0 | 0 | 0 |
        1 | 1 | 1 | 1 | 1 | 1 | 1 | 0 | 0 |
        2 | 0 | 1 | 1 | 1 | 1 | 1 | 0 | 0 |
        3 | 0 | 0 | 1 | 1 | 1 | 1 | 1 | 0 |
        4 | 0 | 0 | 0 | 0 | 1 | 1 | 1 | 0 |
        5 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 1 |
        6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 |
        7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 |

// Sub-problem and state:
        let dp[i][j] denote at stone i, the frog can or cannot make jump of size j

// Recurrence relation:
        for any j < i,
        dist = stones[i] - stones[j];
        if dp[j][dist]:
        dp[i][dist - 1] = ture
        dp[i][dist] = ture
        dp[i][dist + 1] = ture


 */

class Solution {
    public boolean canCross(int[] stones) {

        if(stones.length == 0) return true;

        int N = stones.length;
        boolean[][] dp = new boolean[N][N+1];
        dp[0][1] = true;

        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){
                int diff = stones[i]-stones[j];
                if(diff > N || diff < 0|| !dp[j][diff]) continue;
                dp[i][diff] = true;
                if(diff-1>=0) dp[i][diff-1] = true;
                if(diff+1<=N) dp[i][diff+1] = true;
                if(i == N-1) return true;
            }
        }

        return false;

    }
}

/*
方法3：DFS/BFS 用stack分别记录position和jump 初始都是0 然后循环计算jump-1, jump, jump+1的情况 如果到达终点 则返回true 如果存在nextPosition 则将nextPosition加入stack中再次计算nextPosition知道最终达到终点 如果没有终点 返回false

 */

class Solution {
    public boolean canCross(int[] stones) {

        if(stones.length == 0) return true;

        for(int i=3; i<stones.length; i++){
            if(stones[i] > stones[i-1]*2) return false;
        }

        Stack<Integer> positions = new Stack<>();
        Stack<Integer> jumps = new Stack<>();

        positions.add(0);
        jumps.add(0);

        HashSet<Integer> set = new HashSet<>();
        for(int stone: stones){
            set.add(stone);
        }


        while(!positions.isEmpty()){
            int currentPosition = positions.pop();
            int currentJump = jumps.pop();
            for(int i=currentJump-1; i<= currentJump+1; i++){
                if(i <= 0) continue;
                int nextPosition = currentPosition+i;
                if(nextPosition == stones[stones.length-1]){
                    return true;
                }else if(set.contains(nextPosition)){
                    positions.add(nextPosition);
                    jumps.add(i);
                }

            }
        }
        return false;

    }
}
