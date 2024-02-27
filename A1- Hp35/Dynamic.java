
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

        //om stacken är full och vi vill lägga till element --> skapa en större array och kopierar alla previous values till den
        if(top == size){ //arr.length == size
            int[] temparr = new int [2*size]; //dubbel så stro storlek
            
            for(int i = 0; i < size; i++){
                temparr[i] = stack[i];
            }
            size = 2*size;
            stack = temparr; 
        }
        stack[top] = val;
        top++;
    }


    //decrease the stack
    public int pop(){

        top--;
        //om stacken är full och vi vill lägga till element --> skapa en större array och kopierar alla previous values till den
        if(top < 0)
            System.out.println("Stack is empty");
        //the stack should always have a minimum size of 4 elements
        else if(top < (size/4) && size > SIZE){ //vi kortar ner arrayen så fort top är på index > 4, behåller arrayen size 4 
            int[] temparr = new int [size/2]; //dubbel så stor storlek?

            for(int i = 0; i < temparr.length; i++)
                temparr[i] = stack[i];
           
            size = size/2;
            stack = temparr;
        }
        return stack[top];
    }

}
