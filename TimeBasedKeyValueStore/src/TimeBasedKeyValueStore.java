import java.util.*;
public class TimeBasedKeyValueStore {

    class Node{
        Node prev;
        Node next;
        String value;
        int timestamp;
        public Node(String v, int ts){
            value = v;
            timestamp = ts;
        }
    }

    Map<String, Node> map;

    public static void main(String[] args){
        TimeBasedKeyValueStore P = new TimeBasedKeyValueStore();
        P.set("foo", "bar", 1);
        System.out.println(P.get("foo", 1));
        System.out.println(P.get("foo",3));
        P.set("foo", "bar2",4);
        System.out.println(P.get("foo", 4));
        System.out.println(P.get("foo", 5));
    }

    public TimeBasedKeyValueStore(){
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp){
        Node node = new Node(value, timestamp);
        Node oldNode = map.get(key);
        if(oldNode != null){
           node.next = oldNode;
           oldNode.prev = node;
        }
        map.put(key, node);

    }

    public String get(String key, int timestamp){
        Node node = map.get(key);
        //if(node == null) return "";
        while(node != null && node.timestamp > timestamp) node = node.next;
        return node == null? "": node.value;


    }


}
