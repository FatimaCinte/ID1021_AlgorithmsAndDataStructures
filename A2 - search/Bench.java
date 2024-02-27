import java.util.Random;
import java.util.*;

class Bench {

    public static int[] unsorted(int n){
        Random rnd = new Random();	
	    int[] arr = new int[n];
        
        for(int i = 0; i < n; i++){
            arr[i] = rnd.nextInt(n);
        }

        return arr;
    }
      
    private static void linear(int[] array, int[] indx) {
        for (int i = 0; i < indx.length ; i++) {
            Linear.search(array, indx[i]);
            }
    }
    
    private static void linearUns(int[] array, int[] indx) {
        for (int i = 0; i < indx.length ; i++) {
            Linear.search_unsorted(array, indx[i]);
            }
    }

    private static void binary(int[] array, int[] indx) {
        for (int i = 0; i < indx.length ; i++) {
            Binary.search(array, indx[i]);
            }
    }

    private static int[] sorted(int n) {
        Random rnd = new Random();	
        int[] array = new int[n];
        int nxt = rnd.nextInt(10);

        for (int i = 0; i < n ; i++) {
            array[i] = nxt;
            nxt += rnd.nextInt(10) + 1 ;
        }	

        return array;
    }


    private static int[] keys(int loop, int n) {
        Random rnd = new Random();	
        int[] indx = new int[loop]; 
        for (int i = 0; i < loop ; i++) {
            indx[i] = rnd.nextInt(n*5); 
        }

	    return indx;
    }

    
    private static void binary_dupsearch(int[] arrayOne, int[] arrayTwo, int runs) {
        for (int i = 0; i < runs; i++) {
            Binary.binary_dup(arrayOne, arrayTwo);
            }
    }
    
    private static void unsorted_dup(int[] arrayOne, int[] arrayTwo, int runs) {
        for (int i = 0; i < runs; i++) {
            Linear.unsorted_dup(arrayOne, arrayTwo);
            }
    }
        
    private static void twop_dup(int[] arrayOne, int[] arrayTwo, int runs) {
        for (int i = 0; i < runs; i++) {
            Linear.twopointer_dup(arrayOne, arrayTwo);
            }
    }

    
    public static void main(String[] arg) {

	int[] sizes = {100,200,400,800,1600,3200,6400,12800,25600,51200, 1000000, 64000000};//, 1000000, 500000, 250000, 125000};

	System.out.printf("# searching through an array of length n, time in ns\n");
    System.out.printf("#%7s%8s%8s%8s\n", "n", "      usorted dup", "       binary dup", "         two pointer dup");

    for (int n : sizes) { //iterates sizes[n], for each size, do this

	    int loop = 10000;
	    int[] array = sorted(n);
        int[] unsortarrOne = unsorted(n); //unsorted
        int[] unsortarrTwo = unsorted(n); //unsorted


	    int[] indx = keys(loop, n); 

	    System.out.printf("%8d", n);

	    int k = 100;
	    
	    double min = Double.POSITIVE_INFINITY;

	    /*for (int i = 0; i < k; i++) {
            long t0 = System.nanoTime();
            linear(array, indx);    //linear search sorted array
            long t1 = System.nanoTime();
            double t = (t1 - t0);
            if (t < min)
                min = t;
	    }

	    System.out.printf("%8.0f", (min/loop));	
             
        //Uppgift 1 bench unsorted
        int[] unsortarr = unsorted(n); //unsorted

        min = Double.POSITIVE_INFINITY;

	     for (int i = 0; i < k; i++) { //min tiderna för k = 1000 försök
            long t0 = System.nanoTime();
            linearUns(unsortarr, indx); //arayy av size n, hitta elementet indx[n] i arrayen
            long t1 = System.nanoTime();
            double t = (t1 - t0);
            if (t < min)
                min = t;
	    }*/

        System.out.printf("%8.0f" , (min/loop));	

	    min = Double.POSITIVE_INFINITY;
	    
	    for (int i = 0; i < k; i++) {
            long t0 = System.nanoTime();
            binary(array, indx);
            long t1 = System.nanoTime();
            double t = (t1 - t0);
            if (t < min)
                min = t;
	    }

	    System.out.printf("%8.0f\n" , (min/loop));
   

        //*******************************************************sorted duplicates**************************************************

        /*int[] arrayOne = sorted(n); 
        int[] arrayTwo = sorted(n);

        int runs = 10;

        min = Double.POSITIVE_INFINITY;

	    for (int i = 0; i < k; i++) {
            System.gc();
            long t0 = System.nanoTime();
            unsorted_dup(unsortarrOne, unsortarrTwo, runs);   
            long t1 = System.nanoTime();
            double t = (t1 - t0);
            if (t < min)
                min = t;
	    }
        System.out.printf("\t%8.0f" , (min/runs));	

        min = Double.POSITIVE_INFINITY;

	    for (int i = 0; i < k; i++) {
            System.gc();

            long t0 = System.nanoTime();
            binary_dupsearch(arrayOne, arrayTwo, runs);   
            long t1 = System.nanoTime();
            double t = (t1 - t0);
            if (t < min)
                min = t;
	    }
        System.out.printf("\t%8.0f" , (min/runs));	

        min = Double.POSITIVE_INFINITY;

	    for (int i = 0; i < k; i++) {
            System.gc();

            long t0 = System.nanoTime();
            twop_dup(arrayOne, arrayTwo, runs);
            long t1 = System.nanoTime();
            double t = (t1 - t0);
            if (t < min)
                min = t;
	    }
        System.out.printf("\t%8.0f\n" , (min/runs));	*/

	    }
    
    }

}
