import java.util.Arrays;

class Bench{
    
    public static void BenchTASKONE(){
        
        //int[] sizes = {100,200,400,800,1600,3200,6400,12800,25600, 51200};
        int[] sizes = {100,200,400,800,1000,1600,3200,6400,12800,25600};

        System.out.printf("a.append(b) in microseconds (min/1000), fixed size is 1000\n");

        System.out.printf("#%7s%8s%8s\n", "n", "    a varying", "\ta fixed");

        for (int n : sizes){
            System.out.printf("%8d", n);
            
            int fixed = 1000;

            LinkedList a = new LinkedList();
            LinkedList b = new LinkedList();
            a.makeList(n);  //vary size
            b.makeList(fixed); //fixed size

            int k = 100;
            double min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                    b.appendList(a); //b will be b+a full,a tom
                        
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.2f", (min/1000));

            min = Double.POSITIVE_INFINITY;
    
            a.makeList(fixed);  //vary size
            b.makeList(n); //fixed size
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                    b.appendList(a); 

                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.2f\n" , (min/1000));
            	
        }

    }

    public static void BenchLLvsAL(){
        
        int[] sizes = {100,200,400,800,1000,1600,3200,6400,12800, 25600, 51200, 102400};

        System.out.printf("#%7s%8s%8s%8s%8s\n", "n", "  LL b.append(a)", " Arr append(a, b)", "  LL make", "  Arr make");

        for (int n : sizes){
            System.out.printf("%8d", n);
            
            int fixed = 1000;

            LinkedList a = new LinkedList();
            LinkedList b = new LinkedList();
            a.makeList(n);  //vary size
            b.makeList(fixed); //fixed size

            int k = 1000;

            //////////////////////Linked List append
            double min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                b.appendList(a); 
                        
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f", (min/1000));


            /////////////////////////Array list append
            int[] arra = ArrayList.makeArray(n); //vary size
            int[] arrb = ArrayList.makeArray(fixed); //fixed size

            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                ArrayList.appendArray(arra, arrb); 
                
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f" , (min/1000));

            /////make list
            min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                a.makeList(n); 
                        
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f", (min/1000));

            //make array
            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                ArrayList.makeArray(n);

                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f\n" , (min/1000));
            	
        }

    }

    public static void lastAppend(){
        
        int[] sizes = {100,200,400,800,1000,1600,3200,6400,12800, 25600, 51200, 102400};

        System.out.printf("#%7s%8s%8s%8s%8s%8s%8s\n", "n", "  LL b fixed",  "  LL b varying", " Arr b fixed", " Arr b varying", "makeList", "makeArr");

        for (int n : sizes){
            System.out.printf("%8d", n);
            
            int fixed = 1000;

            LinkedList a = new LinkedList();
            LinkedList b = new LinkedList();
            a.makeList(n);  //vary size
            b.makeList(fixed); //fixed size

            int k = 1000;

            //////////////////////Linked List append
            double min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                b.appendList(a); 
                        
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f", (min/1000));
            a.makeList(fixed);  //vary size
            b.makeList(n); //fixed size
            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                b.appendList(a); 
                        
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f", (min/1000));


            /////////////////////////Array list append
            int[] arra = ArrayList.makeArray(n); //vary size
            int[] arrb = ArrayList.makeArray(fixed); //fixed size

            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                ArrayList.appendArray(arra, arrb); 
                
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f" , (min/1000));

            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                ArrayList.appendArray(arra, arrb); 
                
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f" , (min/1000));

            
            /////make list
            min = Double.POSITIVE_INFINITY;

            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                a.makeList(n); 
                        
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f", (min/1000));

            //make array
            min = Double.POSITIVE_INFINITY;
            
            for (int i = 0; i < k; i++) {
                double t0 = System.nanoTime();
                
                ArrayList.makeArray(n);

                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.1f\n" , (min/1000));
            	
        }

    }

    public static void main(String[ ] args){

        //BenchTASKONE();
        //BenchLLvsAL();
        lastAppend();

       // System.out.println(array[5]);


        /*int[] a = {1,2,3,4,5};
        int[] b = {11,22,33,44,55,66,77,88,99};
        int[] a = ArrayList.makeArray(10);
        int[] b = ArrayList.makeArray(15);
        System.out.println(Arrays.toString(ArrayList.appendArray(a,b)));*/


        //////////// linked list
        /*LinkedList LL = new LinkedList();
        LL.add(11);
        LL.add(22);
        LL.add(33);
        LL.add(44);
        LL.add(55);
        LL.add(66);
        LL.remove(55);
        LL.remove(11);

        LL.printList();
        System.out.println();

        LinkedList LT = new LinkedList();
        LT.add(111);
        LT.add(222);
        LT.add(333);
        LT.add(444);
        LT.add(555);
        LT.printList();
        System.out.println();

        LT.appendList(LL);

        LT.printList();
        System.out.println();
        
        LL.printList();
        System.out.println();

        //System.out.printf("\nfound? " + LL.find(11) + " length: " + LL.length() + "\n");*/


    }

}