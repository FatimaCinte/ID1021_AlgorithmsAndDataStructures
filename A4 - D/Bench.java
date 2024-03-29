import java.util.Random;

public class Bench{

    public static Cell[] cellarrayDLL(int n, DoublyList list){
        Cell[] cellarray = new Cell[n];
        Cell nxt = list.first;
        int i = 0;
        while(nxt != null){
            cellarray[i] = nxt;
            nxt = nxt.next;
            i++;
        }

        return cellarray;
    }

    public static Cell[] cellarraySLL(int n, SingleList list){
        Cell[] cellarray = new Cell[n];
        Cell nxt = list.first;
        int i = 0;
        while(nxt != null){
            cellarray[i] = nxt;
            nxt = nxt.next;
            i++;
        }

        return cellarray;
    }

    //random k keys between 0 and n-1
    public static int[] keys(int k, int n) {
        Random rnd = new Random();	
        int[] indx = new int[k]; 
        for (int i = 0; i < k ; i++) {
            indx[i] = rnd.nextInt(n-1); 
        }

	    return indx;
    }


    public static void main(String[] args){
        
        /*DoublyList listDLL = new DoublyList();
        Cell s = new Cell(22);
        Cell e = new Cell(321);

        listDLL.insertFirst(s);
                listDLL.insertFirst(e);

    

        listDLL.printList();*/


        //int[] sizes = {100,200,400,800,1000,1600,3200,6400,12800, 25600, 51200, 102400};
       int[] sizes = {100,200,400,800,1000,1600,3200,6400,12800, 25600, 51200, 102400};

        System.out.printf("#%7s%8s%8s\n", "n", "  SLL unlink&insert", " DLL unlink&insert");

        for (int n : sizes){
            System.out.printf("%8d", n);
            
            int k = 1000;
            int tries = 100;
            int[] keys = keys(k, n); //indices of cells to unlink and insert

            //////Single Linked List
            double min = Double.POSITIVE_INFINITY;

            SingleList listSLL = new SingleList();
            listSLL.makeList(n);
            Cell[] cellarray = cellarraySLL(n, listSLL);

            for (int i = 0; i < tries; i++) {
                double t0 = System.nanoTime();

                for(int j = 0; j < k; j++){
                    listSLL.unlink(cellarray[keys[j]]);
                    listSLL.insertFirst(cellarray[keys[j]]);
                }

                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.2f", (min/(1000)));

            ////////Doubly Linked List
            min = Double.POSITIVE_INFINITY;

            DoublyList listDLL = new DoublyList();
            listDLL.makeList(n);
            Cell[] cellarrayD = cellarrayDLL(n, listDLL);
            //Cell elD = listDLL.first;

            for (int i = 0; i < tries; i++) {
                double t0 = System.nanoTime();

                for(int j = 0; j < k; j++){
                    //elD = cellarrayD[keys[j]];
                    listDLL.unlink(cellarrayD[keys[j]]);
                    listDLL.insertFirst(cellarrayD[keys[j]]);
                }
                double t1 = System.nanoTime();
                double t = (t1 - t0);
                if (t < min)
                    min = t;
            }

            System.out.printf("\t%.2f\n", (min/(1000)));

        }

    }
    
}
