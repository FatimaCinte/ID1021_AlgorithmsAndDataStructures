public class Stack<T>{
    Cell stack;

    private class Cell{
        T value;
        Cell next;

        private Cell(T val, Cell nxt){
            value = val;
            next = nxt;
        }

    }

    public Stack(){
        stack = null;
    }


    public void push (T item) {
        stack = new Cell (item, stack);
    }

    public T pop () {

        if (stack == null)
            return null;
        
        T ret = stack.value;
        stack = stack.next;
        return ret;
        
    }

}

 
