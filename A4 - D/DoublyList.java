
class DoublyList{
    Cell first;

    public DoublyList(){
        this.first = null;
    }
    
    public void add(int item){
        Cell el = new Cell(item); //skapar en ny cell med head = item, tail är null

        if(first != null){  //om first inte är null (om det finns element i listan)            
            el.next = first;   //sätt tail pekar på det som first pekar på
            first.prev = el;    //sätt att first pekar endast på nya elementet nu
            
            el.prev = null;
            first = el;    //om listan är tom, sätt att first pekar på nya el direkt

        }
        
        first = el;    //om listan är tom, sätt att first pekar på nya el direkt
    }


    public void makeList(int n) {
        Cell el = first;
        for(int i = 0; i < n; i++){
            el = new Cell(i);
            if(first != null){  //om first inte är null (om det finns element i listan)
                el.next = first;   //sätt tail pekar på det som first pekar på
                first.prev = el;    //sätt att first pekar endast på nya elementet nu
            }
            first = el; 
        }

        /*ELLER
        for(int i = 0; i < n; i++)
            list.add(i);*/

    }
      
    public boolean find(int item){
        Cell el = first;   
        while(el != null){
            if(el.head == item)
                return true;
            el = el.next;
        }
        return false;
    }

    public int length(){
        int number = 0;
        Cell el = first; //första elementet i listan
        while (el != null){    //om elementet pekar till en annan element, om == null då är det sista elementet
            number++;   //increment counter
            el = el.next; //gå till nästa cell med sin nya tail being nxt.tail 
        }
        return number;
    }

    public void remove(int item){
        Cell el = first;
        while(el != null){
            if(el.head == item){
                if(el.next != null)
                   el.next.prev =  el.prev;

                if(el.prev != null)
                    el.prev.next = el.next;
                else
                    first = el.next;

                el.next = null;
                el.prev = null;
            }
            el = el.next;
        }
        return;
    }

    public void insertFirst(Cell item){
        if(first != null){  
            item.next = first;   
            first.prev = item;
            
            item.prev = null;
            //first = item;  
        }
        first = item;    
    }

    public void unlink (Cell del){
        if(del.next != null)
            del.next.prev = del.prev;
        
        if(del.prev != null)
            del.prev.next = del.next;
        else
            first = del.next;

        //del.prev = null;
        //del.next = null;
    }

    public void printList(){
        System.out.print("[");

        Cell el = first; //first to last, forward
        while(el != null){
            System.out.print(el.head + ", ");
            el = el.next;
        }

        System.out.print("]");

    }



        
}