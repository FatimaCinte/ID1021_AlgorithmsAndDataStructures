
class LinkedList{ 
    Cell first;

    private class Cell{ 
        int head;
        Cell tail;

        Cell(int val, Cell tl){ 
            head = val;
            tail = tl;  
        }
    }

    public LinkedList(){
        this.first = null;
    }
    
        public void add(int item){
            Cell el = new Cell(item, null); 

            if(first != null){ 
                el.tail = first;  
                first = el;  
            }
            first = el; 
        }

        
        public boolean find(int item){
            Cell nxt = first; 
            while(nxt != null){
                if(nxt.head == item)   
                    return true;
                nxt = nxt.tail; 
            }
                return false;
        }

        public void remove(int item){
            Cell nxt = first;  
            Cell prev = null;   

            while(nxt != null){
                if(nxt.head == item){   
                    if(prev != null) 
                        prev.tail = nxt.tail;  
                    else
                        first = nxt.tail;   
                }
                prev = nxt; 
                nxt = nxt.tail;
            }
        }
        
        public int length(){
            int number = 0;
            Cell nxt = first; 
            while (nxt != null){    
                number++;  
                nxt = nxt.tail; 
            }
            return number;
        }

        public void makeList(int n) {
            Cell last = null;
            for (int i = 0; i < n; i++) {
                last = new Cell(i, last);
            }
            this.first = last;
        }

        public void appendList(LinkedList b) {
            Cell nxt = this.first;
            //Cell prev = null;
            while (nxt.tail != null) {  
               // prev = nxt; //set nxt to prev
                nxt = nxt.tail;
            }
            nxt.tail = b.first; 
            b.first = null; 
            
        }

        public void printList(){
            Cell nxt = first;
            while(nxt != null){
                System.out.print(nxt.head + ", ");
                nxt = nxt.tail;
            }
        }


}