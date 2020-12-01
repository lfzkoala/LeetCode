import java.util.*;

public class DesignHashMap {

    class Node{
        Node next;
        int key;
        int value;
        public Node(int k, int v){
            key = k;
            value = v;
        }
    }

    Node[] array;
    public static int maxSize = 10001;


    public static void main(String[] args){
        DesignHashMap P = new DesignHashMap();
        P.MyHashMap();
        P.put(1,1);
        P.put(2,2);
        System.out.println(P.get(1));
        System.out.println(P.get(3));
        P.put(2,1);
        System.out.println(P.get(2));
        P.remove(2);
        System.out.println(P.get(2));
    }

    //Initialize hashmap
    public void MyHashMap(){
        array = new Node[maxSize];
    }

    //put value into hashmap
    public void put(int key, int value){
        int index = getIndex(key);
        Node node = array[index];
        Node cur = node;
        while(cur != null){
            if(cur.key == key){
                cur.value = value;
                return;
            }
            cur = cur.next;
        }
        Node newNode = new Node(key, value);
        newNode.next = node;
        array[index] = newNode;
    }

    //get value using key
    public int get(int key){
        int index = getIndex(key);
        Node node = array[index];
        while(node != null){
            if(node.key == key) return node.value;
            node = node.next;
        }
        return -1;
    }

    //remove the mapping of the specified value key if it contains
    public void remove(int key){
        int index = getIndex(key);
        Node node = array[index];
        while(node != null){
            if(node.key == key){
                node.value = -1;
                return;
            }
            node = node.next;
        }
    }

    //check contains key
    public boolean containsKey(int key){
        int index = getIndex(key);
        Node node = array[index];
        while(node != null){
            if(node.key == key) return true;
            node = node.next;
        }
        return false;
    }

    //get Index
    public int getIndex(int key){
        return Integer.hashCode(key)%maxSize;
    }





}
