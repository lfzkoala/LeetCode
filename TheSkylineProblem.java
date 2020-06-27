/*
The Skyline Problem

        A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

        The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
        For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
        The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
        For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
        Notes:
        The number of buildings in any input list is guaranteed to be in the range [0, 10000].
        The input list is already sorted in ascending order by the left x position Li.
        The output list must be sorted by the x position.
        There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

        答案：

        用PriorityQueue 讲解：https://www.youtube.com/watch?v=8Kd-Tn_Rz7s



 */
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<List<Integer>> result = new ArrayList<>();
        List<int[]> height = new ArrayList<>();

        for(int[] building: buildings){
            height.add(new int[]{building[0], -building[2]}); //use negative values to record height of starting point of a square.
            height.add(new int[]{building[1], building[2]}); //use positive values to record height of ending point of a square.
        }

        Collections.sort(height, (a,b)->{
            if(a[0]!=b[0]) return a[0]-b[0];
            return a[1]-b[1];
        }); //sort in increasing order based on first element. If the first element is the same, sort based on the second element.

        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> (b-a));

        int prev = 0;
        int cur = 0;

        pq.offer(0);

        for(int[] h : height){
            if(h[1]<0){
                pq.offer(-h[1]);
            }else{
                pq.remove(h[1]); //remove h[1], not removing by default.
            }

            cur = pq.peek(); //if the point is a starting point, cur will be the point with maximum height; If the point is an ending point, cur will be the second largest point

            if(prev != cur){
                List<Integer> temp = new ArrayList<>();
                temp.add(h[0]);
                temp.add(cur);
                result.add(temp);
                prev = cur;
            }
        }

        return result;





    }
}
