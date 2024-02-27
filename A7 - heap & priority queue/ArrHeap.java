import java.util.Random;

class ArrHeap{

    int[] heap;
    int size;
    int curSize; //k

    public ArrHeap(int n){
        size = n;
        curSize = 0;
        heap = new int[n];
    }

    public void clear(int n){
        curSize = 0;
        heap = new int[n];
    }

    public void swap (int i, int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void enqueue(int itm){
        if(curSize == size)
            return;

        heap[curSize] = itm; //add new item at the end of array, then heapify it
        bubble(curSize); //heapify the last item
        curSize++;

    }

    public int dequeue(){
        if(curSize == 0)
            return -1;

        int ret = heap[0];
        curSize--;
        heap[0] = heap[curSize]; //curSize-1
        heap[curSize] = 0; //-1
        sink(0, 0);

        return ret;

    }

    public void bubble(int indx){
        if(indx == 0)
            return;
        /*int newVal = heap[curSize];

        while(indx > 0 && newVal < heap[(indx-1)/2]){
            swap(curSize, (indx-1)/2);
            indx = (indx -1)/2;
        }
        heap[indx] = newVal;*/


        int parent = (indx-1)/2;
        if(heap[indx] < heap[parent])
            swap(indx, parent);
        
        bubble(parent);
    }

    public int sink(int indx, int depth){
        if(curSize == 1)
            return depth;

        int left = indx*2 + 1;
        int right = indx*2 + 2;

        int smallest = indx;
        if(left < curSize && heap[left] < heap[smallest])
            smallest = left;
        
        if(right < curSize && heap[right] < heap[smallest])
            smallest = right;
        
        if(smallest != indx){
            swap(indx, smallest);
            depth++;
            depth = sink(smallest, depth);
        }

        return depth;
    }

    public int push(int incr){
        int depth = 0;
        heap[0] += incr;
        depth = sink(0, depth);
        return depth;
    }

    public static int[] keys(int n) {
        Random rnd = new Random();	
        int[] indx = new int[n]; //skapar en array av loop antal random element
        for (int i = 0; i < n ; i++) {
            indx[i] = rnd.nextInt(n); //upper bound är 500 om n = 100
        }

	    return indx;
    }
    public static void ArrDepth(){
        ArrHeap h = new ArrHeap(100);

        int[] keys = keys(10000);
                
        for(int j = 0; j < 1023; j++){
            h.enqueue(j);
        }

        int[] incr = keys(1023);

        for (int i = 0; i < 100; i++) {
            System.out.println("root is: " + h.heap[0]);

            System.out.println("random incr val: " + incr[i] + " depth: " + h.push(incr[i]));


        }
    }

    
    public static void LinkedDepth(){
        LinkedHeap heap = new LinkedHeap();
        ArrHeap h = new ArrHeap(100);

        int[] incr = keys(1023);
            
            for(int j = 0; j < 1023; j++){
                heap.enqueue(j);
                h.enqueue(j);
            }
        for (int i = 0; i < 100; i++) {

            System.out.println("arr root is: " + h.heap[0]);
            System.out.println("arr random incr val: " + incr[i] + " depth: " + h.push(incr[i]));
            System.out.println();

            System.out.println("linked root is: " + heap.root.prio);
            System.out.println("linked random incr val: " + incr[i] + " depth: " + heap.push(incr[i]));
        }

    }

    //long bench...
    public static void benchHeap(){
        //int[] sizes = {100,200,400,800,1600,3200,6400,12800, 25600,51200,102400};
        int[] sizes = {1000,2000,4000,8000,16000,32000,64000,128000, 256000,512000,1024000};

        System.out.printf("#%7s%8s%8s%8s%8s\n", "n", "\t arr heap not push", " ", "    arr heap push", "  ");

        for (int n : sizes){
            System.out.printf("%8d", n);
            
            int k = 1000;
            int[] incr = keys(n);
            int ex = 500;

            ArrHeap heap = new ArrHeap(n);
            for(int j = 0; j < n; j++)
                heap.enqueue(j);

            double min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                for(int c = 0; c < ex; c++){
                    int q = heap.dequeue();
                    heap.enqueue(q + incr[c]);
                }

                double t1 = System.nanoTime();
                double t = (t1 - t0);

                if (t < min)
                    min = t;
            }
            System.out.printf("\t%8.0f", (min/(1000)));

            heap.clear(n);
            for(int j = 0; j < n; j++)
                heap.enqueue(j);

            min = Double.POSITIVE_INFINITY;
            
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
    
    //lol
    public static void benchboth(){
        int[] sizes = {1000,2000,4000,8000,16000,32000,64000,128000, 256000,512000,1024000};

        System.out.printf("#%7s%8s%8s%8s%8s\n", "n", "\t linked deq/enq", "   linked push ", "    arr deq/enq", "  arr push");

        for (int n : sizes){
            System.out.printf("%8d", n);
            
            int k = 1000;
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
            System.out.printf("\t%8.0f", (min/(1000)));


            ArrHeap heapa = new ArrHeap(n);
            for(int j = 0; j < n; j++)
                heapa.enqueue(j);

             min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                for(int c = 0; c < ex; c++){
                    int q = heapa.dequeue();
                    heapa.enqueue(q + incr[c]);
                }

                double t1 = System.nanoTime();
                double t = (t1 - t0);

                if (t < min)
                    min = t;
            }
            System.out.printf("\t%8.0f", (min/(1000)));

            heapa.clear(n);
            for(int j = 0; j < n; j++)
                heapa.enqueue(j);

            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                for(int j = 0; j < ex; j++)
                    heapa.push(incr[j]);
                
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }
            System.out.printf("\t%8.0f\n", (min/(1000)));

        }
    }
 
 
    public static void main(String args[])
    {
        benchboth();
    }
}