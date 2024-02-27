import java.util.Random;
import java.util.Arrays;


class Bench{


    public static int[] makeArr(int n){
        Random rnd = new Random();

        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = rnd.nextInt(n*10);
        }
        //SelectionSort.sort(array);
        //InsertionSort.sort(array);
        //System.out.println(Arrays.toString(array));
        
        return array;
    }


    public static void main (String[] arg){

        SelectionSort.sort(makeArr(15));

        System.out.printf("#%7s%8s%8s%8s%8s\n", "n", "    SelSort", "   InsSort",  "   Ratio", "    MergeSort");
        //int[] sizes = {1000,2000,4000,6000,8000,10000,12000,14000,16000,32000,64000,128000};//, 1000000, 500000, 250000, 125000};
        
        //for(int n : sizes){
        for (int n = 1000; n <= 51200; n *= 2) { 
        //for (int n = 1000; n <= 16000; n += 1000) { 

            System.out.printf("%8d", n);

            int k = 10;
            int loop = 10;
            int[] array = makeArr(n);

            double minS = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                for(int j = 0; j < loop; j++){
                    int[] clone = array.clone();
                    SelectionSort.sort(clone);                    
                }
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < minS)
                    minS = t;
            }
            System.out.printf("%8.0f", minS/(loop*1000));
            
            double minI = Double.POSITIVE_INFINITY;
  
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                for(int j = 0; j < loop; j++){
                    int[] clone = array.clone();
                    InsertionSort.sort(clone);                    
                }
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < minI)
                    minI = t;
            }
            System.out.printf("%8.0f", minI/(loop*1000));

           // System.out.printf("%8.2f", minI/minS);


            double minM = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                for(int j = 0; j < loop; j++){
                    int[] clone = array.clone();
                    MergeSort.sort(clone);                    
                }
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < minM)
                    minM = t;
            }
            System.out.printf("%8.0f\n", minM/(loop*1000));

        }

    }



}