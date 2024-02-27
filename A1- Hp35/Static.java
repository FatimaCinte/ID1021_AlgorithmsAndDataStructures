
public class Static extends Stack{
    
    //int[] arr = new int[1024]; //allocate size 
   // int top = 0; //pointer initiated to the bottom of the stack (empty stack start)
    
    /*public Static(int size) {    //får arrayen som parameter 
        this.size = size;   //pekar på arrayen
    }*/
    public Static (int Size){
        stack = new int[Size];
        top = 0;
        //size = 3300;
    }

    public void push(int val){
        stack[top] = val;
        top++;

        if(top == size)
            System.out.println("Stack is full"); //throw exception?
    
    }

    public int pop(){
        top--;

        if(top < 0){ // == -1
            System.out.println("Stack is empty");
            return 0;
        }
        else
            return stack[top]; //pop the element
    }

 
}