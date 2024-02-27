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
        
    }

}