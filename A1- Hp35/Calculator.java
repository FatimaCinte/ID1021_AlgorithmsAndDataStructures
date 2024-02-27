

public class Calculator {
    
    Item[] expr; //en array av typen Item (objekt som antingen har type eller siffra)
    int ip; //pointer
    Stack stack; //STATIC

    public Calculator(Item[] expr) {    //får arrayen som parameter 
        this.expr = expr;   //pekar på arrayen
        this.ip = 0;        //if empty stack???
        this.stack = new Static(2);  //STATIC 
    }


    public void step() { //kollar vilken type det är (för item) och switchar till rätt operation
                            //om type är ADD, then utför vi addition
        
        Item nxt = expr[ip++]; //temporär Item som pekar på nästa objekt/index på arrayen
        
        switch(nxt.type()) { //om den expr[ip++] är ADD, then gå till case ADD etc.
            case ADD : {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x + y);
                break;
            }
            case SUB : {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x - y);
                break;
            }
            case MUL : {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x * y);
                break;
            }
            case DIV : {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x/y);
                break;
            }
            case VALUE : {
                stack.push(nxt.getvalue());
                break;
            }
        }
    }

    public int run() { //exekverar en gång på taget (step at a atime)
        while ( ip < expr.length ) {    //så länge pointer inte är på sista index på arrayen (senaste poppade item)
        step(); //step
        }
        return stack.pop(); //poppa stacken
    }


}
