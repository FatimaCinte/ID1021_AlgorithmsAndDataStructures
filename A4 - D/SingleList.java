import java.util.Random;

class SingleList{ 
    Cell first;

    public SingleList(){
        this.first = null;
    }
    
        public void add(int item){
            Cell el = new Cell(item); 

            if(first != null){  
                el.next = first;  
                first = el;    
            }
            first = el;   
        }

        
        public boolean find(int item){
            Cell nxt = first;  
            while(nxt != null){
                if(nxt.head == item)    
                    return true;
                nxt = nxt.next; 
            }
                return false;
        }

        public void remove(int item){
            Cell nxt = first;  
            Cell prev = null;   

            while(nxt != null){ 
                if(nxt.head == item){  
                    if(prev != null)   
                        prev.next = nxt.next;  
                    else
                        first = nxt.next;   
                }
                prev = nxt; 
                nxt = nxt.next; 
            }
        }
        
        //same as remove but with a cell as argument?
        public void unlink(Cell del){
            Cell nxt = first;
            Cell prev = null;
             while(nxt != null){ 
                if(nxt == del){   
                    if(prev != null){  
                        prev.next = nxt.next;  
                        return;
                    }
                    else{
                        first = nxt.next;   
                        return; 
                    }
                }
                prev = nxt;
                nxt = nxt.next; 
            }
            return;

        }
        
        public int length(){
            int number = 0;
            Cell nxt = first; 
            while (nxt != null){   
                number++;  
                nxt = nxt.next; 
            }
            return number;
        }

        public void makeList(int n) {
            Cell last = null;
            for(int i = 0; i < n; i++){
                last = new Cell(i);
                last.next = first;
                first = last;
            }
            this.first = last;
        }

        public void appendList(SingleList b) {
            Cell nxt = this.first;
            Cell prev = null;
            while (nxt.next != null) { 
                prev = nxt; 
                nxt = nxt.next; 
            }
            nxt.next = b.first; 
            b.first = null; 
            
        }

        public void printList(){
            Cell nxt = first;
            while(nxt != null){
                System.out.print(nxt.head + ", ");
                nxt = nxt.next;
            }
        }


        public void insertFirst(Cell key){
            if(first != null){  
                key.next = first;  
                first = key;   
            }
            first = key;    
        }


}