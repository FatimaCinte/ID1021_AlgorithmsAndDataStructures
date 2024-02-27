class heap{
    Node root; 

    private class Node {
        int prio;
        //int item;
        int size;
        Node left, right;

        private Node(int p) {
            prio = p;
            size = 1;
            //item = itm;
            left = null;
            right = null;
        }

        /*private void add(int pr){
            size++;
            if(pr < prio){
                int temp = prio;
                prio = pr;
                pr = temp;
            }
            if(right != null){
                Node tmp = left;
                right.add(pr);
                left = right;
                right = tmp;
            }
            else
                right = new Node(pr);
        
        }*/

        private void add(int pr){
            size++;
            if(pr < prio){
                int temp = prio;
                prio = pr;
                pr = temp;
            }
            if(right != null){
                Node tmp = left;
                right.add(pr);
                left = right;
                right = tmp;
            }
            else
                right = new Node(pr);
        
        }


        private Node remove(){  //returnerar vad som blev kvar (null elr nåt annat)
            size--;

            if(right == null)
                return left;
            else{
                if(left == null)
                    return right;
                else{
                    if (left.prio < right.prio){
                        prio = left.prio;
                        left = left.remove();
                    }
                    else{
                        prio = right.prio;
                        right = right.remove();
                    }
                    return this;
                }
            }
        }
    }

    private heap(){
        root = null;
    }

    public void enqueue(int pr){
        if(root == null)
        root = new Node(pr);
    else
        root.add(pr);
    }

    //gå igenom o jämför, 
    public int dequeue(){
        if(root == null)
        return -1;

        int p = root.prio;
        root = root.remove();
        return p;

    }

    public static void main(String[] args){
        
        heap q = new heap();

        q.enqueue(10);
        q.enqueue(22);
        q.enqueue(44);
        q.enqueue(65);
        q.enqueue(2);
        q.enqueue(33);


        System.out.println("item removed " + q.dequeue() );
        System.out.println("item removed " + q.dequeue() );
        System.out.println("item removed " + q.dequeue() );
        System.out.println("item removed " + q.dequeue() );
        System.out.println("item removed " + q.dequeue() );
        System.out.println("item removed " + q.dequeue() );
        
        /*heap q = new heap();

        q.enqueue(12);
        q.enqueue(20);
        q.enqueue(10);

        int p = heap.dequeue();
        while(p != -1){
            System.out.println();
            p = heap.dequeue();
        }*/

    }

}