import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.w3c.dom.Node;



class Bench{

    public static int[] keys(int n) {
        Random rnd = new Random();
        int[] indx = new int[n];
        for (int i = 0; i < n; i++) {
            indx[i] = rnd.nextInt(n*1000); //upper bound är 500 om n = 100
        }
	    return indx;
    }

    public static int[] makeArr(int[] keys, int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = keys[i];

        return arr;
    }

    public static void BenchOne(){

        /************************warmup */
        int N = 1000;
        int K = 1000;

        for(int i =0; i < K; i++){
            ArrayQuickSort copy = new ArrayQuickSort();
            copy.sort(keys(N), 0, N-1);
        }
        /*************************** */
        
        //int[] sizes = {100,200,400,800,1600,3200,6400,12800, 25600};
        int[] sizes = {1000,2000,4000,8000,16000,32000,64000,128000, 256000};

        System.out.printf("#%7s%8s%8s%8s%8s%8s\n", "n", "\t array QS", "\tarr Qs/n*log(n)", "\t list QS", "\t list/n*log(n)", "array/list");

        for (int n : sizes){
            System.out.printf("%8d", n);

            int tries = 100;
            int k = 10; //hur många ggr vi ska sortera
            int[] keys = keys(n);

            double minl = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < tries; i++) {
                int[] array = makeArr(keys, n);
                double t0 = System.nanoTime();
                
                ArrayQuickSort.sort(array, 0, n-1);
                
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < minl)
                    minl = t;
            }

            System.out.printf("\t%8.0f" , (minl/1000));
            System.out.printf("\t%.2f" , (minl/(n*Math.log(n))));

            
            double min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < tries; i++) {
                ListQuickSort list = new ListQuickSort();
                list.makeList(keys, n);
                double t0 = System.nanoTime();

                ListQuickSort.sort(list);
                
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }
            System.out.printf("\t %8.0f" , (min/1000));
            System.out.printf("\t%.2f" , (min/(n*Math.log(n))));

            System.out.printf("\t%.2f\n" , (min/(minl)));


        }


    }
    

    public static void main(String[] args){

        BenchOne();

        //**************************testa printa RANDOM ARRAYS OCH LISTOR
        /*int size = 10;
        int[] keys = keys(size);
                
        int[] arr = makeArr(keys, size);
        System.out.println("Unsorted array:" + Arrays.toString(arr));
        ArrayQuickSort.sort(arr, 0, size-1);

        System.out.println("Sorted array: " + Arrays.toString(arr));

        System.out.println("orig list");
        ListQuickSort list = new ListQuickSort();
        list.makeList(keys, size);
        list.printList(size);

        System.out.println("copy list");
        ListQuickSort clone = list.copyList(list);
        clone.printList(size);
        
        System.out.println("sorted list");
        ListQuickSort.sort(clone);
        clone.printList(size);
        
        System.out.println("orig list");
        list.printList(size);*/

        
    
        //****************************************

        /*int[] sizes = {100,200,400,800,1600,3200,6400,12800, 25600};

        System.out.printf("#%7s%8s%8s%8s%8s\n", "n", "\t array QS", "\tarr Qs/nlog(n)", "\t list QS", "\t list/n*log(n)");

        for (int n : sizes){
            System.out.printf("%8d", n);

            int tries = 100;
            int k = 10; //hur många ggr vi ska sortera
            int[] keys = keys(n);

            int[] array = makeArr(keys, n);


               
            double min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < tries; i++) {
                double t0 = System.nanoTime();
                
                for(int j = 0; j < k; j++){
                    int[] clone = array.clone();
                    ArrayQuickSort.sort(clone, 0, clone.length-1);
                }
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%8.0f" , (min/1000));
            System.out.printf("\t%.3f" , (min/(1000*n*Math.log(n))));

            ListQuickSort list = new ListQuickSort();
            list.makeList(keys, n);
            
            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < tries; i++) {
                double t0 = System.nanoTime();
                    
                for(int j = 0; j < k; j++){
                    ListQuickSort.sort(list.copyList(list));
                }
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }
            System.out.printf("\t %8.0f" , (min/1000));
            System.out.printf("\t%.3f\n" , (min/(1000*n*Math.log(n))));


        }*/


        
    }

}


/*public static void main(String[] args){
    
        //ARRAY QUICK SORT
       /*int[] data = { 8, 7, 2, 1, 0, 9, 6 };
        System.out.println("Unsorted Array");
        System.out.println(Arrays.toString(data));

        int size = data.length;

        ArrayQuickSort.sort(data, 0, size -1);

        System.out.println("Sorted Array in Ascending Order: ");
        System.out.println(Arrays.toString(data));*/


        //LINKED LIST QUICK SORT
        

        
        /*int[] sizes = {100,200,400,800,1000,1600,3200,6400,12800, 25600, 51200, 102400};

        System.out.printf("#%7s%8s%8s%8s%8s%8s%8s\n", "n", "  LL b fixed",  "  LL b varying", " Arr b fixed", " Arr b varying", "makeList", "makeArr");

        for (int n : sizes){
            System.out.printf("%8d", n);

            int k = 1000;

            double min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                

                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f\n" , (min/1000));
        }*/

        //MAKE ARRAYS AND LISTS
        /*int n = 10;
        int[] keys = keys(n);

        ListQuickSort list = new ListQuickSort();
        //for (int i = 0; i < n; i++) 
         //   list.add(keys[i]);
        list.makeList(keys, n);
        list.printList();

        System.out.println("\n" + Arrays.toString(makeArr(keys, n)));*/




        //AUX LISTS TEST
        /*int[] keys = keys(20);
        ListQuickSort list = new ListQuickSort();

        ListQuickSort S = new ListQuickSort();
        for (int i = 0; i < 10; i++) 
            S.add(keys[i]);

        ListQuickSort L = new ListQuickSort();
        for (int i = 10; i < 20; i++) 
            L.add(keys[i]);

        S.printList();
        L.printList();
        System.out.println();
        list.partitiontest(S, L);
        
        
        
        
        
        int[] keys = keys(10);
        ListQuickSort list = new ListQuickSort();
        list.makeList(keys,10);

        for (int i = 0; i < 8; i++){
            Node curr = list.first;
        }

        list.printList();
        System.out.println();

        System.out.println("sorted list");
        list.sort(list);
        list.printList();

        //list.partition(S, L, S.first, L.findLast(L));
        
    }*/
