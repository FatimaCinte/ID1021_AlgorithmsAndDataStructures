import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

class Bench{

    
    private static void print (BinaryTree tree, Integer[] keys){
        for (Integer k : keys){
            System.out.println("key is " + k + ", value at key is " + tree.lookup(k));

        }
    }
    
    public static Integer[] randKeys(int n) {
        Random rnd = new Random();	
        Integer[] indx = new Integer[n]; //skapar en array av loop antal random element
        for (int i = 0; i < n; i++) {
            indx[i] = rnd.nextInt(n*100); 
        }
	    return indx;
    }
    

    public static void BenchLookup(){

        //int[] sizes = {1000,2000,4000,8000,16000,32000,64000};
        int[] sizes = {1000,2000,4000,8000,16000,32000,64000,128000,256000};

        System.out.printf("#%7s%8s%8s%8s\n", "n", "   add/1000  ","  lookup/1000", " lookup/log(n)");

        for (int n : sizes){
            System.out.printf("%8d", n);
            
            int tries = 100;

            Integer[] keys = randKeys(n); 
            //BinaryTree tree = makeTree(n, keys);
            
            BinaryTree tree = new BinaryTree();
            
            double min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < tries; i++) {
                double t0 = System.nanoTime();
                
                for(Integer j = 0; j < n; j++)
                    tree.add(keys[j], j);    


                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%8.0f", (min/1000));

            min = Double.POSITIVE_INFINITY;
            int lookups = 1000;

            for (int i = 0; i < tries; i++) {
                double t0 = System.nanoTime();
                
                for(int j = 0; j < lookups; j++)
                    tree.lookup(keys[j]);

                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%8.0f", (min/1000));
            System.out.printf("\t%8.0f\n", (min/((Math.log(n)))));


        }
    }


    public static void main(String[] args){

        /*BinaryTree tree = new BinaryTree();

        int n = 10;
        Integer[] keys = randKeys(n);

        for(Integer i = 0; i < n; i++){
            tree.add_rec(keys[i], i);
            System.out.println("key " + keys[i] + " value " + tree.lookup(keys[i]));
        }
        System.out.println();
        print(tree, keys);*/

        /*BinaryTree tree = new BinaryTree();
        int n = 10;
        Integer[] keys = randKeys(n);

        for(Integer i = 0; i < n; i++){
            tree.add_rec(keys[i],i);
        }

        System.out.println();
        print(tree, keys);

        BenchLookup();*/

        /*BinaryTree tree = new BinaryTree();
        tree.add(5,105);
        tree.add(2,102);
        tree.add(7,107);
        tree.add(1,101);
        tree.add(8,108);
        tree.add(6,106);
        tree.add(3,103);

        for (Integer i : tree)
            System.out.println("next value " + i);*/
        
        BenchLookup();


    }
}