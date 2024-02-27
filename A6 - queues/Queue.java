public class Queue<T> {
    
    Node first;
    Node last;

    private class Node {
        T item;
        Node next;

        private Node(T item, Node nxt) {
            this.item = item;
            this.next = nxt;
        }
    }

    public Queue(){
        this.first = null;
        this.last = null;
    }

    //lägger till i slutet
    public void enqueue(T itm){
        Node nxt = new Node(itm, null);

        if(last != null)
            last.next = nxt;
        last = nxt;
        if(first == null)
            first = nxt;
    }

    //tar bort i början
    public T dequeue(){
        if(first == null)
            return null;
        
        T itm = first.item;
        first = first.next;

        if(first == null)
            last = null;

        return itm;
    }

    public boolean isEmpty(){
        return (first == null);
    }
    public static void main(String[] args){
        /*Queue<Integer> q = new Queue<Integer>();

        q.enqueue(10);
        q.enqueue(11);
        q.enqueue(12);
        q.enqueue(13);
        System.out.println("first item added " + q.first.item);
        System.out.println("last item added " + q.last.item);

        System.out.println("item removed " + q.dequeue());
        System.out.println("item removed " + q.dequeue());
        System.out.println("item removed " + q.dequeue());

        q.enqueue(99);
        System.out.println("item removed " + q.dequeue());*/
        Queue<Integer> q = new Queue<Integer>();

        q.enqueue(10);
        q.enqueue(11);
        q.enqueue(12);

        System.out.println("item removed " + q.dequeue() );
        System.out.println("item removed " + q.dequeue() );
        System.out.println("item removed " + q.dequeue() );

        q.enqueue(13);
        System.out.println("item removed " + q.dequeue() );

        
        /*BinaryTree tree = new BinaryTree();
        tree.add(5,105);
        tree.add(2,102);
        tree.add(7,107);
        tree.add(1,101);
        tree.add(8,108);
        tree.add(6,106);
        tree.add(3,103);

        for (Integer i : tree)
            System.out.println("next value " + i);*/

    }
}