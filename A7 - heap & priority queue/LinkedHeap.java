import java.util.Random;

class LinkedHeap{ //implements PriorityQueue
    Node root;

    public class Node{
        //T item;
        int size;
        int prio;
        Node left, right;

        private Node(int p){
            left = null;
            right = null;
            prio = p;
            size = 1;
        }

        public void add(int pr){
            size++;

            if(pr < prio){
                int temp = prio;
                prio = pr;
                pr = temp;
            }
            if(right != null){
                if(left != null){
                    if(left.size < right.size)
                        left.add(pr);
                    else
                        right.add(pr);
                }
                else
                    left = new Node(pr);
                    
            }
            else
                right = new Node(pr);

                    /*if(pr < prio){
                        int temp = prio;
                        prio = pr;
                        pr = temp;
                    }
                    if(right != null){
                        Node tmp = left;
                        right.add(pr);
                        left = right;
                        right = tmp;

                    }
                    else{
                        right = new Node(pr);
                    }*/
            }

            private Node remove(){  
                size--;
    
                if(right == null)
                    return left;
                
                if(left == null)
                    return right;
                    
                if (left.prio < right.prio){
                    prio = left.prio;
                    //left.size--;
                    left = left.remove();
                }
                else{
                    prio = right.prio;
                    //right.size--;
                    right = right.remove();
                }
                 return this;
                    
            }
                
        }
    

    public LinkedHeap(){
        root = null;
    }

    public void clear(){
        root=null;
    }

    public void enqueue(int pr){
        if(root == null)
            root = new Node(pr);
        else
            root.add(pr);
    }

    public int dequeue(){
        if(root == null)
            return -1;

        int p = root.prio;
        root = root.remove();
        return p;
    }


    public int push(Integer incr){
        int depth = 0;
        int incrValue = root.prio + incr;
        root.prio += incr;

        Node cur = root;

        while (cur != null) {
            Node tempcur = null;

            if (cur.left != null && (cur.right == null || cur.left.prio < cur.right.prio)) 
                tempcur = cur.left;    
            else if(cur.right != null)  
                tempcur = cur.right;   
            
            if (tempcur != null && tempcur.prio < incrValue) {
                int temp = cur.prio;
                cur.prio = tempcur.prio;
                tempcur.prio = temp;
              
                cur = tempcur;
                depth++;
            }
            else 
                break; 
        }

        return depth;
    
    }
    
    public static int[] keys(int n) {
        Random rnd = new Random();	
        int[] indx = new int[n]; 
        for (int i = 0; i < n ; i++) {
            indx[i] = rnd.nextInt(n); 
        }

	    return indx;
    }

    public static void benchpush(){
        LinkedHeap heap = new LinkedHeap();

        int[] keys = keys(10000);
                
        for(int j = 0; j < 1023; j++){
            heap.enqueue(keys[j]);

        }

        for(int j = 0; j < 1023; j++){
            System.out.printf(", " + heap.dequeue());

        }

        for(int j = 0; j < 1023; j++){
            heap.enqueue(keys[j]);

        }

        int[] incr = keys(100);

        for (int i = 0; i < 100; i++) {
            
            System.out.println("root is: " + heap.root.prio);
            //System.out.println("root.left: " + heap.root.left.prio + " root.right: " + heap.root.right.prio);

            System.out.println("random incr val: " + incr[i] + " depth: " + heap.push(incr[i]));
        }
    }

    public static void benchHeap(){
        int[] sizes = {1000,2000,4000,8000,16000,32000,64000,128000, 256000,512000,1024000};
        //int[] sizes = {100,200,400,800,1600,3200,6400,12800, 25600,51200,102400};

        System.out.printf("#%7s%8s%8s%8s%8s\n", "n", " linked heap (deq & enq(incr)", "", " linked heap push", "  ");

        for (int n : sizes){
            System.out.printf("%8d", n);
            
            int k = 100;
            int[] incr = keys(n);
            int ex = 500;

            LinkedHeap heap = new LinkedHeap();
            for(int j = 0; j < n; j++)
                heap.enqueue(j);

            double min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                for(int c = 0; c < ex; c++){
                    int root = heap.dequeue();
                    heap.enqueue(root + incr[c]);
                }

                double t1 = System.nanoTime();
                double t = (t1 - t0);

                if (t < min)
                    min = t;
            }
            System.out.printf("\t%8.0f", (min/(1000)));

            
            min = Double.POSITIVE_INFINITY;

            heap.clear();
            for(int j = 0; j < n; j++)
                heap.enqueue(j);
            
            for (int i = 0; i < k; i++) {

                double t0 = System.nanoTime();
                
                for(int j = 0; j < ex; j++)
                    heap.push(incr[j]);
                
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }
            System.out.printf("\t%8.0f\n", (min/(1000)));

        }
    }

    public static void main(String[] args){

        benchpush();


    }

}