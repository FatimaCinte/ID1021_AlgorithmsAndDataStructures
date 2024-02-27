class ArrayQ{

    Integer [] queue ;
    int first, last;
    int size;


    public ArrayQ() {
        size = 4;

        queue = new Integer[size];

        first = 0;
        last = 0;
    }
    
    public boolean empty () {
        return ( first == last );
    }

    public void enqueue(Integer itm){
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
    }

}