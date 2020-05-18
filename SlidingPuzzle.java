/*
Sliding Puzzle
        On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.

        A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

        The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].

        Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.

        Examples:

        Input: board = [[1,2,3],[4,0,5]]
        Output: 1
        Explanation: Swap the 0 and the 5 in one move.
        Input: board = [[1,2,3],[5,4,0]]
        Output: -1
        Explanation: No number of moves will make the board solved.
        Input: board = [[4,1,2],[5,0,3]]
        Output: 5
        Explanation: 5 is the smallest number of moves that solves the board.
        An example path:
        After move 0: [[4,1,2],[5,0,3]]
        After move 1: [[4,1,2],[0,5,3]]
        After move 2: [[0,1,2],[4,5,3]]
        After move 3: [[1,0,2],[4,5,3]]
        After move 4: [[1,2,0],[4,5,3]]
        After move 5: [[1,2,3],[4,5,0]]
        Input: board = [[3,2,4],[1,5,0]]
        Output: 14
        Note:

        board will be a 2 x 3 array as described above.
        board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].


        方法：我们的目标是把矩阵变换成[[1,2,3],[4,5,0]], 我们可以把矩阵表示成一个string “123450”，我们的目标是对string中0的位置进行变换知道和“123450”相同为止 所以用BFS从0的位置开始对四个方向一个一个进行对换知道找到123450为止 注意这里的四个方向要表示成数组dirs = {1,-1,3,-3}, 1为友移，-1为左移，3为上移（相当于string中右移三位），-3为下移，注意有特殊情况 如果0在第index=2和3时时不能右移和左移的，所以在循环中的判断时应该考虑这两种特殊情况 比如string=320145或者321045

*/

class Solution {


    int[] dirs = {1,-1,3,-3};

    public int slidingPuzzle(int[][] board) {

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<2; i++){
            for(int j=0; j<3; j++){
                sb.append(board[i][j]);
            }
        }

        queue.offer(sb.toString());
        visited.add(sb.toString());
        return BFS(queue, visited);
    }

    private int BFS(Queue<String> queue, Set<String> visited){

        int move = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                String s1 = queue.poll();
                if(s1.equals("123450")) return move;
                int idx = s1.indexOf("0");
                for(int dir:dirs){
                    int pos = idx+dir;
                    if(pos<0 || pos>5 || (idx == 2 && dir == 1) || (idx == 3 && dir==-1)) continue;
                    String s2 = exchange(s1, idx, pos);
                    if(!visited.contains(s2)){
                        visited.add(s2);
                        queue.offer(s2);
                    }

                }
            }
            move++;
        }

        return -1;
    }

    private String exchange(String str, int idx, int pos){
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(idx, str.charAt(pos));
        sb.setCharAt(pos, str.charAt(idx));
        return sb.toString();
    }


}

