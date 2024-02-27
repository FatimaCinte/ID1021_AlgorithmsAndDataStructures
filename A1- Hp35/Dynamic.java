
public class Dynamic extends Stack{
   
    final int SIZE;

    public Dynamic(){
        SIZE = 4;
        stack = new int[SIZE];
        top = 0;
        size = SIZE;
    }
    public Dynamic(int s){
        SIZE = s;
        stack = new int[SIZE];
        top = 0;
        size = SIZE;
    }

    public void push(int val){

        if(top == size){ 
            int[] temparr = new int [2*size]; 
            
            for(int i = 0; i < size; i++){
                temparr[i] = stack[i];
            }
            size = 2*size;
            stack = temparr; 
        }
        stack[top] = val;
        top++;
    }

    public int pop(){

        top--;
        if(top < 0)
            System.out.println("Stack is empty");
        //the stack should always have a minimum size of 4 elements
        else if(top < (size/4) && size > SIZE){ 
            int[] temparr = new int [size/2]; 

            for(int i = 0; i < temparr.length; i++)
                temparr[i] = stack[i];
           
            size = size/2;
            stack = temparr;
        }
        return stack[top];
    }

}
