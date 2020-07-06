/*
Course Schedule II

        There are a total of n courses you have to take, labeled from 0 to n-1.
        Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
        Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
        There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
        Example 1:
        Input: 2, [[1,0]]
        Output: [0,1]
        Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
        course 0. So the correct course order is [0,1] .
        Example 2:
        Input: 4, [[1,0],[2,0],[3,1],[3,2]]
        Output: [0,1,2,3] or [0,2,1,3]
        Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
        courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
        So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
        Note:
        The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
        You may assume that there are no duplicate edges in the input prerequisites.
        方法1：DFS

 */

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        //if(numCourses == 0 || prerequisites == null || prerequisites.length == 0) return new int[0];

        List<List<Integer>> graph = new ArrayList<List<Integer>>(numCourses);

        // create the number of courses
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<Integer>());
        }

        //populate the courses, say example --> [1,0][2,0][3,0][4,0][4,3] numCourses--> 5
        // courses should be populated as [1,2,3,4][][][4][]
        // the above list sugnifies that to take course 0, you have a prerequist to take courses 1,2,3,4

        for(int i = 0; i < prerequisites.length; i++){
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        int[] visited = new int[numCourses];
        int[] res = new int[numCourses];
        List<Integer> result = new ArrayList<>();

        for(int i = 0; i < graph.size(); i++){
            if(!dfs(i, graph, visited, result)) return new int[0];
        }

        for(int i = 0; i < result.size(); i++){
            res[i] = result.get(numCourses - i - 1);
        }
        return res;

    }
    public boolean dfs(int course, List<List<Integer>> graph, int[] visited, List<Integer> res){
        if(visited[course] == 2){
            return true;
        }
        if(visited[course] == 1){
            return false;
        }

        visited[course] = 1;

        List<Integer> neighbors = graph.get(course);

        for(int i = 0; i < neighbors.size(); i++){
            int currentCourse = neighbors.get(i); //(int)
            //found a cycle
            if(visited[currentCourse] == 1){
                return false;
            }else if(visited[currentCourse] == 0){
                if(!dfs(currentCourse, graph, visited, res)) return false;
            }
        }
        visited[course] = 2;
        res.add(course);
        return true;
    }
}
/*
方法2：BFS 不太好写 建议用DFS的方法


 */
public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return null;
        // Convert graph presentation from edges to indegree of adjacent list.
        int indegree[] = new int[numCourses], order[] = new int[numCourses], index = 0;
        for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
        indegree[prerequisites[i][0]]++;

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++)
        if (indegree[i] == 0) {
        // Add the course to the order because it has no prerequisites.
        order[index++] = i;
        queue.offer(i);
        }

        // How many courses don't need prerequisites.
        while (!queue.isEmpty()) {
        int prerequisite = queue.poll(); // Already finished this prerequisite course.
        for (int i = 0; i < prerequisites.length; i++)  {
        if (prerequisites[i][1] == prerequisite) {
        indegree[prerequisites[i][0]]--;
        if (indegree[prerequisites[i][0]] == 0) {
        // If indegree is zero, then add the course to the order.
        order[index++] = prerequisites[i][0];
        queue.offer(prerequisites[i][0]);
        }
        }
        }
        }

        return (index == numCourses) ? order : new int[0];
        }
