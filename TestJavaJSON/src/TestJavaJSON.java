
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
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
import java.lang.String;

public class TestJavaJSON {

    private static HttpURLConnection connection;

    public static void main(String[] args){
        //Method1: java.net.HttpURLConnection
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
        parse(responseContent.toString());

        /*
        //Method 2: java.net.http.HttpClient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/albums")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .join();

        */
    }

    private static String parse(String responseBody){
        JSONObject obj = new JSONObject(responseBody);
        String source = obj.getString("base");
        System.out.println(source);
        JSONObject arr = obj.getJSONObject("rates");
        Object[] a = arr.keySet().toArray();
        double x = arr.getDouble(a[0].toString());
        System.out.println(a[0].toString());
        return null;

    }




}
