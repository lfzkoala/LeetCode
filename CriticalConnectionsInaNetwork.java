/*
Critical Connections in a Network

        There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
        A critical connection is a connection that, if removed, will make some server unable to reach some other server.
        Return all critical connections in the network in any order.

        Example 1:

        Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
        Output: [[1,3]]
        Explanation: [[3,1]] is also accepted.


        Constraints:
        1 <= n <= 10^5
        n-1 <= connections.length <= 10^5
        connections[i][0] != connections[i][1]
        There are no repeated connections.

        方法：We record the timestamp that we visit each node. For each node, we check every neighbor except its parent and return a smallest timestamp in all its neighbors. If this timestamp is strictly less than the node's timestamp, we know that this node is somehow in a cycle. Otherwise, this edge from the parent to this node is a critical connection


 */
class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        List<Integer>[] graph = new ArrayList[n]; //注意这种声明用法

        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();
        }

        //construct the graph
        for(List<Integer> temp: connections){
            graph[temp.get(0)].add(temp.get(1));
            graph[temp.get(1)].add(temp.get(0));
        }

        List<List<Integer>> results = new ArrayList<>();
        int timer = 0;
        boolean[] visited = new boolean[n];
        int[] nodeTime = new int[n];

        DFS(graph, -1, 0, visited, timer, nodeTime, results);
        return results;
    }

    private void DFS(List<Integer>[] graph, int parent, int node, boolean[] visited, int timer, int[] nodeTime, List<List<Integer>> results){

        visited[node] = true;
        nodeTime[node] = timer;
        timer++;
        int currentTime = nodeTime[node];

        for(int neighbor: graph[node]){

            if(neighbor == parent) continue;
            if(!visited[neighbor]) DFS(graph, node, neighbor, visited, timer, nodeTime, results);

            nodeTime[node] = Math.min(nodeTime[node], nodeTime[neighbor]);

            if(currentTime<nodeTime[neighbor]) results.add(Arrays.asList(node, neighbor));
        }

    }
}

