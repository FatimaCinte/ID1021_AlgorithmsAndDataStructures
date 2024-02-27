import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

class ListQuickSort{
    Node first;
    Node last;

    private class Node{
        Node next;
        int value;

        public Node(int val, Node nxt){
            this.value = val;
            this.next = nxt;

        }
    }
    public ListQuickSort(){
        this.first = null;
        this.last = null;
    }

    public static void sort(ListQuickSort list){

        if(list.first == null || list.first.next == list.last)
            return;


        ListQuickSort S = new ListQuickSort();
        ListQuickSort L = new ListQuickSort();
        Node pivot = partition(S, L, list.first);

        sort(S);
        sort(L);

        list.first = null;
        list.last = null;
        list.appendList(S);
        list.insertLast(pivot);
        list.appendList(L);
        
    }

    //lägg till pivot el sist i S array, pivot är första i S
    public static Node partition(ListQuickSort smaller,
                                    ListQuickSort larger, Node min){
        Node cur = min.next;    
        Node pivot = min;

        while(cur != null){
            Node nxt = cur.next;
            cur.next = null;

            if(cur.value <= pivot.value) 
                smaller.insertLast(cur);
            else
                larger.insertLast(cur);

            cur = nxt;
        }
        return pivot;
    }

    public void appendList(ListQuickSort b) {
        if(b.first != null){
            if(first == null){
                first = b.first;
                last = b.last;
            }
            else{
                last.next = b.first;
                last = b.last;
            }
            b.first = null;
            b.last = null;
        }
    }

    public void add(int item){
        Node el = new Node(item, null); 

        if(first == null){
            first = el;
            last = first;
        }
        else{
            last.next = el;
            last = last.next;
        }

    }
    
    public void insertLast(Node el){
        if(first == null){
            first = el;
            last = first;
        }
        else{
            last.next = el;
            last = last.next;
        }
    }
    
    public void makeList(int[] keys, int n){
        for (int i = 0; i < n; i++){
            Node el = new Node(keys[i], null); 
        
        if(first == null){
            first = el;
            last = first;
        }
        else{
            last.next = el;
            last = last.next;
            }
        }
    }
    
    /*public void makeList(int[] keys, int n){
        for (int i = 0; i < n; i++){
            Node el = new Node(keys[i], null); 
            if(first != last){  
                el.next = first;   
                first = el;    
                }
            first = el;    
        }
    }*/

    /*public void printList() {
        System.out.print("  [");
        Node cur = this.first;
        if (cur != null) {
            System.out.print(cur.value);
            cur = cur.next;
        }
        while (cur != null) {
            System.out.print(", " + cur.value);
            cur = cur.next;
        }
        System.out.println("]");
    }*/
    
    public void printList(int size) {
      System.out.print("  [");

        Node c = first;
        for(int i = 0; i < size; i++){
            System.out.print(", " + c.value);
            c = c.next;
        }
        System.out.println("]");

    }
    /*public void printList(int size, Node c) {
      System.out.print("  [");

        while(c != null){
            System.out.print(", " + c.value);
            c = c.next;
        }
        System.out.println("]");

    }*/

    /*public ListQuickSort copyList(Node f){
        if(f.next == null)
            return null;
  
        ListQuickSort clone = new ListQuickSort();
        Node cur = f;
        clone.first = cur;
        while(f != null){
            cur = new Node(f.value, null);
       
            //System.out.print(", " + cur.value);
            cur = cur.next;
            f = f.next;
        }
        //clone.last = new Node(last.value, null);

        return clone;
    }*/
    
    /*public ListQuickSort copyList(ListQuickSort l){
        if(l.first.next == null)
            return null;
        
        ListQuickSort clone = new ListQuickSort();
        Node cur = l.first;
        while(cur != null){
            cur = new Node(cur.value, null);
            cur = cur.next;
        }

        return clone;
    }*/

    public ListQuickSort copyList(ListQuickSort list){
        ListQuickSort copy = new ListQuickSort();
        Node curr = list.first;
        while(curr != null){
            copy.add(curr.value);
            curr = curr.next;
        }
        return copy;
    }


}