class Cell{ 
        int head;
        Cell prev;
        Cell next;

        Cell(int val){//, Cell prv, Cell nxt){
            this.head = val;
            this.prev = null;
            this.next = null;
        }
    }