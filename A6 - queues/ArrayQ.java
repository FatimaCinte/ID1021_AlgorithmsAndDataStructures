class ArrayQ{
    Integer [] queue ;
    int i, k;
    int size;


    public ArrayQ(int n) {
        size = n;

        queue = new Integer[size];

        i = 0;
        k = 0;
    }
    
    public boolean empty () {
        return ( i == k );
    }

    /*Integer [] queue ;
    int first, last;
    int size;


    public ArrayQ(int n) {
        size = n;

        queue = new Integer[size];

        first = 0;
        last = 0;
    }
    
    public boolean empty () {
        return ( first == last );
    }*/

    public void enqueue(Integer itm){
        queue[k] = itm;
        k = (k + 1) % size;

        if(k == i){
            Integer[] newarr = new Integer[2*size];
            int counter = 0;

            for(int j = i; j < size; j++){
                newarr[counter] = queue[j];
                counter++;
            }
            for(int j = 0; j < k; j++){
                newarr[counter] = queue[j];
                counter++;
            }
            size = 2*size;
            i = 0;
            k = counter;
            queue = newarr;
        }
    }

    public Integer dequeue(){
        if(i == k)
            return null;
        Integer ret = queue[i];
        queue[i] = null;
        i = (i + 1)%size;
        return ret;
    }


    /*public void enqueue(Integer itm){
        queue[last] = itm;
        last = (last + 1) % size;
        if(last == first){
            Integer[] copy = new Integer[size*2];
            int c = 0;
            for(int i = first; i < size; i++){
                copy[c] = queue[i];
                c++;
            }
            for(int i = 0; i < last; i++){
                copy[c] = queue[i];
                c++;
            }
            size = size*2;
            first = 0;
            last = c;
            queue = copy;
        }
            

    }

    public Integer dequeue(){
        if(first == last)
            return null;
        Integer ret = queue[first];
        first = (first + 1)%size;
        return ret;
    }*/

    public static void main(String[] args){
        ArrayQ q = new ArrayQ(1);

        q.enqueue(10);
        q.enqueue(11);
        q.enqueue(12);

        System.out.println("item removed " + q.dequeue());
        System.out.println("item removed " + q.dequeue());
                System.out.println("item removed " + q.dequeue());

        
        q.enqueue(13);
        System.out.println("item removed " + q.dequeue());
  
    }

}