
import java.util.*;
public class AllO1DataStructure {
    public static void main(String[] args){
        AllO1DataStructure P = new AllO1DataStructure();
        P.inc("xxx");
        P.inc("yyy");
        P.inc("zzz");
        String s = P.getMaxKey();
        System.out.println(s);
        P.dec("yyy");
        P.dec("xxx");
        System.out.println(P.getMinKey());
    }

    class Node{
        Node prev;
        Node next;
        Set<String> set;
        int value;

        public Node(int val){
            value = val;
            set = new HashSet<String>();
        }

        public void Add(Node node){
            node.next = this.next;
            this.next.prev = node;
            node.prev = this;
            this.next = node;
        }

        public void Remove(String key){
            this.set.remove(key);

            if(this.set.isEmpty()){
                this.prev.next = this.next;
                this.next.prev = this.prev;
            }
        }
    }

    Map<String, Node> map;
    Node head, tail;

    public AllO1DataStructure(){
        map = new HashMap<String, Node>();
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    public void inc(String key){
        if(!map.containsKey(key)){
            Node newNode = head.next.value == 1? head.next : new Node(1);
            newNode.set.add(key);
            if(head.next.value != 1) head.Add(newNode);
            map.put(key, newNode);
        }else{
            Node node = map.get(key);
            Node newNode = node.value+1 == node.next.value? node.next : new Node(node.value+1);
            newNode.set.add(key);
            if(node.value+1 != node.next.value) node.Add(newNode);
            node.Remove(key);
            map.put(key, newNode);
        }
    }

    public void dec(String key){
        if(!map.containsKey(key)) return;
        Node node = map.get(key);
        if(node.value == 1){
            map.remove(key);
            node.Remove(key);
        }else{
            Node newNode = node.prev.value == node.value-1? node.prev:new Node(node.value-1);
            newNode.set.add(key);
            if(node.prev.value != node.value-1) node.prev.Add(newNode);
            map.put(key, newNode);
            node.Remove(key);
        }
    }

    public String getMaxKey(){
        return tail.prev == head? "":tail.prev.set.iterator().next();
    }

    public String getMinKey(){
        return head.next == tail? "":head.next.set.iterator().next();
    }





}
