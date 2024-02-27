class Queue{

    Node first;
    Node last;

    private class Node {
        Integer item;
        Node next;

        private Node(Integer item, Node nxt) {
            this.item = item;
            this.next = nxt;
        }
    }

    public void enqueue(Integer itm){
        Node nxt = new Node(itm, null);

        if(last != null)
            last.next = nxt;
        last = nxt;
        if(first == null)
            first = nxt;
    }

    public Integer dequeue(){
        if(first == null)
            return null;
        
        Integer itm = first.item;
        first = first.next;

        if(first == null)
            last = null;
        return itm;
    }

    public boolean empty(){
        return (first == null);
    }

    public static void main(String[] args){
        /*Queue q = new Queue();

        q.enqueue(10);
        q.enqueue(11);
        q.enqueue(12);

        System.out.println("item removed " + q.dequeue());
        System.out.println("item removed " + q.dequeue());
        System.out.println("item removed " + q.dequeue());

        q.enqueue(99);
        System.out.println("item removed " + q.dequeue());*/
        ArrayQ q = new ArrayQ();

        q.enqueue(10);
        q.enqueue(11);
        q.enqueue(12);

        System.out.println("item removed " + q.dequeue());
        System.out.println("item removed " + q.dequeue());
        
        q.enqueue(13);
        System.out.println("item removed " + q.dequeue());

    }
}