import java.util.Random;

public class Queue { // implements priorityqueue
    
    Node first;

    private class Node {
        Node next;
        int prio;

        private Node(int p, Node nxt) {
            next = nxt;
            prio = p;
        }
    }

    public Queue(){
        this.first = null;
    }
    public void clear(){
        first = null;
    }

    public void add(int prio){

        Node el = new Node(prio, null);

        if(first != null){   
            el.next = first;
            first = el;
        }
        first = el;
    }

    public int removeX() {
        if (first == null) 
            return -1; // Queue is empty
        
    
        Node cur = first;
        Node prev = null;
        
        Node temp = first;
        Node tempPrev = null;
    
        while (cur != null) {
            if (cur.prio <= temp.prio) {
                temp = cur;
                tempPrev = prev;    //prev node to the temp
            }
            prev = cur;
            cur = cur.next;
        }
    
        if (tempPrev == null)  //remove first element if theres only 1 el in queue
            first = first.next;
        else 
            tempPrev.next = temp.next;  //otherwise skip
        
    
        return temp.prio;
    }
    
    public void enqueue(int prio){

        Node el = new Node(prio, null);

        if(first == null || first.prio >= el.prio){   //if added el has higher priority than head, add as head
            el.next = first;
            first = el;
        }
        else{
            Node cur = first;
            while(cur.next != null && cur.next.prio <= el.prio){   //traverse and insert
                cur = cur.next;
            }

            el.next = cur.next;
            cur.next = el;
        }
    }

    //remove item with highest priority - tar bort första O(1)
    public int dequeue(){
        if(first == null)
            return -1;
        else{
            int itm = first.prio;
            first = first.next;
            return itm;
        }

    }

    public boolean isEmpty(){
        return (first == null);
    }

    public static int[] keys(int n) {
        Random rnd = new Random();	
        int[] indx = new int[n]; //skapar en array av loop antal random element
        for (int i = 0; i < n ; i++) {
            indx[i] = rnd.nextInt(n); //upper bound är 500 om n = 100
        }

	    return indx;
    }

    public static void main(String[] args){

        int[] sizes = {100,200,400,800,1600,3200,6400,12800,25600,51200};
        //int[] sizes = {1000,2000,4000,8000,1600,3200,6400,12800,25600,51200};

        //System.out.printf("#%7s%8s%8s%8s%8s%8s%8s\n", "n", "\t add O(n)", "\t add O(n)/n", "\tremove O(1)", "  add O(1)", " remove O(n)", "\tremove O(n)/n");
        /*System.out.printf("#%7s%8s%8s%8s%8s%8s%8s\n", "n", "\t add O(1)", "\t remove O(n)", "\tadd O(n)", "  remove O(1)", " ", "\tremove O(n)/n");

        for (int n : sizes){
            System.out.printf("%8d", n);
            
            int k = 100;
            int[] keys = keys(n);

            int extra = 100;

            double min = Double.POSITIVE_INFINITY;
            Queue queue = new Queue();
            
            //queue.clear();
            int[] start = keys(n);

            for(int j = 0; j < n; j++)
                    queue.addX(j);

            for (int i = 0; i < k; i++) {  
                double t0 = System.nanoTime();

                for(int j = 0; j < extra; j++){
                    queue.add(keys[j]);
                }

                double t1 = System.nanoTime();
                double t = (t1 - t0);

                if (t < min)
                    min = t;
            }
            System.out.printf("\t%.1f", (min/(1000)));
            //System.out.printf("\t%.2f", (min/(n)));

            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                for(int j = 0; j < extra; j++)
                    queue.removeX();
                
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }
            System.out.printf("\t%8.0f", (min/(1000)));
            
            min = Double.POSITIVE_INFINITY;

            queue.clear();

            for(int j = 0; j < n; j++)
                    queue.addX(j);

            for (int i = 0; i < k; i++) {  
                double t0 = System.nanoTime();

                for(int j = 0; j < extra; j++){
                    queue.addX(keys[j]);
                }

                double t1 = System.nanoTime();
                double t = (t1 - t0);

                if (t < min)
                    min = t;
            }
            System.out.printf("\t%8.0f", (min/(1000)));

            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                for(int j = 0; j < extra; j++)
                    queue.remove();
                
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }
            System.out.printf("\t%.1f\n", (min/(1000)));
            //System.out.printf("\t%.2f\n", (min/(n)));

        }*/


    }
}