/*
Courses may have prerequisites, e.g., to take courses 0 you have to first take course 1,
which expressed as a pair [0,1].
Given the total number of courses and a list of prerequisite pairs, is it possible for you to
finish all courses? There could be multiple ways to take these courses, please return one of them.

e.g., Input: numCourses=2, prerequisites = [[1,0], [0,1]], output false;
Input: numCourses = 2, prerequisites = [[1,0]]
 */


import java.util.*;
public class CourseSchedule {

    public static void main(String[] args){

        CourseSchedule P = new CourseSchedule();
        int numCourses = 4;
        int[][] prerequisites = {{1,0}, {2,0}, {3,1}, {3,2}};
        System.out.println(P.canFinish(numCourses, prerequisites));
        int[] result = P.findOrder(numCourses, prerequisites);
        for(int i=0; i<result.length; i++){
            System.out.print(result[i]+" ");
        }
        System.out.println(P.findAllDependencies(numCourses, prerequisites, 3));
    }

    //if we can take all courses, return true, otherwise return false;
    public boolean canFinish(int numCourses, int[][] prerequisites){
        int[] indegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<numCourses; i++) graph.add(new ArrayList<>());

        for(int[] prerequisite: prerequisites){
            graph.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<indegree.length; i++){
            if(indegree[i] == 0) queue.offer(i);
        }

        int j=0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            j++;
            for(int adj: graph.get(cur)){
                indegree[adj]--;
                if(indegree[adj] == 0) queue.offer(adj);
            }
        }
        if(j != numCourses) return false;
        return true;


    }

    //return a course schedule, there could be multiple course schedule, just return one of them
    public int[] findOrder(int numCourses, int[][] prerequisites){
        int[] indegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<numCourses; i++) graph.add(new ArrayList<>());

        for(int[] prerequisite: prerequisites){
            graph.get(prerequisite[1]).add(prerequisite[0]);
            indegree[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<indegree.length; i++){
            if(indegree[i] == 0) queue.offer(i);
        }

        int[] result = new int[numCourses];
        int j =0;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            result[j] = cur;
            j++;
            for(int adj: graph.get(cur)){
                indegree[adj]--;
                if(indegree[adj] == 0) queue.offer(adj);
            }
        }

        if(j != numCourses) return new int[0];
        return result;
    }

    public List<Integer> findAllDependencies(int numCourses, int[][] prerequisites, int course){
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0; i<numCourses; i++) graph.add(new ArrayList<>());

        for(int[] prerequisite: prerequisites){
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }

        return graph.get(course);


    }


}
