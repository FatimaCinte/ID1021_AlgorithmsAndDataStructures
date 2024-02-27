
class LinkedList{ 
    Cell first;

    private class Cell{ 
        int head;
        Cell tail; //next

        Cell(int val, Cell tl){ //egentligen behöver inte tl
            head = val;
            tail = tl;  //tail = null
        }
    }

    public LinkedList(){
        this.first = null;
    }
    
        public void add(int item){
            Cell el = new Cell(item, null); //skapar en ny cell med head = item, tail är null

            if(first != null){  //om first inte är null (om det finns element i listan)
                el.tail = first;   //sätt tail pekar på det som first pekar på
                first = el;    //sätt att first pekar endast på nya elementet nu
            }
            first = el;    //om listan är tom, sätt att first pekar på nya el direkt
        }

        
        public boolean find(int item){
            Cell nxt = first;   //börja i första cellen
            while(nxt != null){
                if(nxt.head == item)    //om item hittad, return true
                    return true;
                nxt = nxt.tail; //iterate to next cell
            }
                return false;
        }

        public void remove(int item){
            Cell nxt = first;   //börja i första cellen
            Cell prev = null;   //prev börjar innan first så den är null

            while(nxt != null){ //när det finns element i listan
                if(nxt.head == item){   //om item hittad
                    if(prev != null)    //inte första cellen
                        prev.tail = nxt.tail;   //prev hoppar över item o pekar på nästa tail
                    else
                        first = nxt.tail;   //item är först i listan, first kmr hoppa över o peka på nxt.tail
                }
                prev = nxt; //iterate till nästa cell, prev till nxt
                nxt = nxt.tail; //iterate till nästa cell, nxt till nästa nxt
            }
        }
        
        public int length(){
            int number = 0;
            Cell nxt = first; //första elementet i listan
            while (nxt != null){    //om första elementet pekar till en annan element
                number++;   //increment counter
                nxt = nxt.tail; //gå till nästa cell med sin nya tail being nxt.tail 
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

        //a.append(b), b läggs till i slutet av a, b blir tom, a är a+b
        public void appendList(LinkedList b) {
            Cell nxt = this.first;
            //Cell prev = null;
            while (nxt.tail != null) {  //go through the list until we find the last cell with its tail == null
               // prev = nxt; //set nxt to prev
                nxt = nxt.tail; //go to next cell
            }
            nxt.tail = b.first; //nxt.tail is == null aka its the last cell, set it to b.first to add the entire next list
            b.first = null; //töm listan
            
        }

        public void printList(){
            Cell nxt = first;
            while(nxt != null){
                System.out.print(nxt.head + ", ");
                nxt = nxt.tail;
            }
        }


}