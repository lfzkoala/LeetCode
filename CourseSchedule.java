/*
Course Schedule

        There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
        Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

        Example 1:
        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: true
        Explanation: There are a total of 2 courses to take.
        To take course 1 you should have finished course 0. So it is possible.

        Example 2:
        Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
        Output: false
        Explanation: There are a total of 2 courses to take.
        To take course 1 you should have finished course 0, and to take course 0 you should
        also have finished course 1. So it is impossible.


        Constraints:
        The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
        You may assume that there are no duplicate edges in the input prerequisites.
        1 <= numCourses <= 10^5
        方法1：DFS


 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        boolean[] visited = new boolean[numCourses];

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<numCourses; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<prerequisites.length; i++){
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        for(int i=0; i<numCourses; i++){
            if(!DFS(graph, visited, i)) return false;
        }

        return true;

    }

    private boolean DFS(List<List<Integer>> graph, boolean[] visited, int i){
        if(visited[i]){
            return false;
        }else{
            visited[i] = true;
        }

        List<Integer> neighbor = graph.get(i);

        for(int j=0; neighbor != null && j<neighbor.size(); j++){
            if(!DFS(graph, visited, neighbor.get(j))) return false;
        }
        visited[i] = false;
        return true;

    }



}

/*
方法2：BFS 速度更快 但是逻辑比较奇怪 同样用graph记录course之间的关系 但是记录的方式变为graph.get(prerequisites[i][0]).add(prerequisites[i][1]而不是graph.get(prerequisites[i][1]).add(prerequisites[i][0] 而且要记录每个course 的indegree, 也就是图中的入度 然后一旦degree==0, 则将这个course加入queue中，并且count++ 最后如果count==numCourses, 则返回true 否则返回false


 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        int count = 0;
        Queue<Integer> queue = new LinkedList<>();

        List<List<Integer>> graph = new ArrayList<>();
        int[] degree = new int[numCourses];

        for(int i=0; i<numCourses; i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<prerequisites.length; i++){
            degree[prerequisites[i][1]]++;
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        for(int i=0; i<degree.length; i++){
            if(degree[i] == 0){
                queue.add(i);
                count++;
            }
        }

        while(queue.size() != 0){
            int course = (int)queue.poll();
            for(int i=0; i<graph.get(course).size();i++){
                int pointer = graph.get(course).get(i);
                degree[pointer]--;
                if(degree[pointer] == 0){
                    queue.add(pointer);
                    count++;
                }
            }
        }

        if(count == numCourses){
            return true;
        }else{
            return false;
        }

    }
}
