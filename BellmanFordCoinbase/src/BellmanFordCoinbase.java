
import java.util.*;
import java.lang.String;
import java.net.http.HttpResponse;
import org.json.*;
import java.net.URI;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;



public class BellmanFordCoinbase {

    /*
    improvements:
    1. how to return shortest path value
     */

    static class Edge{
        String startVertex;
        String endVertex;
        double value;
    }

    private static LinkedList<String> vertices;
    private static LinkedList<Edge> edgeList;
    private static Map<String, Double> vertexDistance;
    private static Map<String, String> vertexParent;
    public static boolean containsNegativeCycle = false;
    private static HttpURLConnection connection;

    public static void main(String[] args){
       BellmanFordCoinbase P = new BellmanFordCoinbase();
        P.Initialize();
        //Method1: java.net.HttpURLConnection to get response
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try{
            URL url = new URL("https://openexchangerates.org/api/latest.json?app_id=525a4af18ebb41b6b1535a187e804d3a");
            connection = (HttpURLConnection) url.openConnection();

            //Request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            //System.out.println(status);
            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }
            //System.out.println(responseContent.toString());

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }
        //parse the object and construct the graph;
        parse(responseContent.toString());

        P.getShortestPath("USD");
        if(containsNegativeCycle){
            System.out.println("The Graph contains negative weight cycle, that is, arbitrage exists");
            return;
        }
        P.printShortestPath();
        P.printPath("ZWL");

    }

    private static void Initialize(){
        vertices = new LinkedList<String>();
        edgeList = new LinkedList<Edge>();
    }

    private static void parse(String responseBody){
        JSONObject obj = new JSONObject(responseBody);
        String source = obj.getString("base");
        JSONObject arr = obj.getJSONObject("rates");
        Object[] a = arr.keySet().toArray();
        insertVertex(source);
        for(int i=0; i<a.length; i++){
            String vertex = a[i].toString();
            double weight = arr.getDouble(vertex);
            insertVertex(vertex);
            insertEdge(source, vertex, toTransfer(weight));
            //insertEdge(vertex, source, toTransfer(1.0/weight));
        }



    }

    private static void insertVertex(String vertex){
        vertices.add(vertex);
    }

    private static void insertEdge(String vertex1, String vertex2, double edgeVal){
        Edge edge = new Edge();
        edge.startVertex = vertex1;
        edge.endVertex = vertex2;
        edge.value = edgeVal;
        edgeList.add(edge);
    }

    public static void getShortestPath(String sourceVertex){
        vertexDistance = new HashMap<String, Double>();
        vertexParent = new HashMap<String, String>();

        //Set the initial distance of every vertex to infinity
        for(String vertex: vertices){
            vertexDistance.put(vertex, Double.MAX_VALUE);
            vertexParent.put(vertex, null);
        }

        //Initialize the distance of source vertex to be 0
        vertexDistance.put(sourceVertex, 0.0);

        int V = vertices.size();
        for(int i=0; i<V-1; i++){
            for(Edge edge: edgeList){
                String u = edge.startVertex;
                String v = edge.endVertex;

                //relax the edge
                if(vertexDistance.get(u) + edge.value < vertexDistance.get(v)){
                    vertexDistance.put(v, vertexDistance.get(u)+edge.value);
                    vertexParent.put(v, u);
                }


            }
        }

        //Relax all the edges again. If we are still getting a lesser distance then
        //there is negative weight cycle in the graph.
        for(Edge edge: edgeList){
            String u = edge.startVertex;
            String v = edge.endVertex;
            if(vertexDistance.get(u) + edge.value < vertexDistance.get(v)){
                //System.out.println("The Graph contains negative weight cycle");
                containsNegativeCycle = true;
                return;
            }
        }


    }

    public static void printShortestPath(){
        for(Map.Entry<String, Double> entry: vertexDistance.entrySet()){
            if(entry.getValue() == Double.MAX_VALUE){
                System.out.println(entry.getKey()+" is not reachable from the source vertex");
            }else{
                System.out.println("The shortest distance between the source vertex and "+entry.getKey()+" is "+String.format("%.10f", backTransfer(entry.getValue())));
            }
        }
    }

    private static void printPath(String vertex){
        if(vertexDistance.containsKey(vertex) && vertexDistance.get(vertex) == Double.MAX_VALUE){
            List<String> result = new ArrayList<>();
            System.out.println(result);
            return;
        }
        List<String> result = new ArrayList<>();
        result.add(vertex);
        while(vertexParent.containsKey(vertex)){
            if(vertexParent.get(vertex) == null) break;
            result.add(vertexParent.get(vertex));
            vertex = vertexParent.get(vertex);
        }
        Collections.reverse(result);
        System.out.println(result);

    }

    private static double toTransfer(double num){
        return Math.log(1.0/num);
    }

    private static double backTransfer(double num){
        return 1.0/(Math.exp(num));
    }


}
