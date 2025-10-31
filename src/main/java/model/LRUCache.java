package model;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private int capacity;
    private Map<Integer,Node> cacheMap;
    private Node head;
    private Node tail;

    public LRUCache(int capacity){
        this.capacity=capacity;
        this.cacheMap=new HashMap<>();
        this.head=new Node(-1,-1);
        this.tail=new Node(-1,-1);
        this.head.next=tail;
        this.tail.prev=this.head;
    }

    public int get(int key){
        if(! cacheMap.containsKey(key)){
            return -1;
        }
        Node node=cacheMap.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value){
        if(cacheMap.containsKey(key)){
            Node oldNode=cacheMap.get(key);
            remove(oldNode);
        }

        Node node=new Node(key,value);
        cacheMap.put(key,node);
        add(node);

        if (cacheMap.size() > capacity) {
            Node nodeToDelete = tail.prev;
            remove(nodeToDelete);
            cacheMap.remove(nodeToDelete.key);
        }
    }

    public void remove(Node node){
        Node prevNode=node.prev;
        Node nextNode=node.next;
        prevNode.next=nextNode;
        nextNode.prev=prevNode;
    }

    public void add(Node node){
        Node nextNode=head.next;
        head.next=node;
        node.prev=head;
        node.next=nextNode;
        nextNode.prev=node;
    }

}
