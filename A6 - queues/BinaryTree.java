import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.tree.TreeNode;


public class BinaryTree implements Iterable<Integer> {
    Node root;

    public Iterator<Integer> iterator() {
        return new TreeIterator();
    }
    
    public class Node {
        public Integer key;
        public Integer value;
        public Node left, right;
        
        private Node(Integer k, Integer val) {
            key = k;
            value = val;
            this.left = this.right = null;
        }

        private void add(Integer k, Integer val){
            if(key == k)
                value = val;
            else if(key > k){
                if(left != null)
                    left.add(k, val);
                else
                    left = new Node(k, val);
            }
            else{
                if(right != null)
                    right.add(k, val);
                else
                    right = new Node(k, val);
            }
            
        }

        private Integer lookup(Integer k){
            if(key == k)
                return value;
            else if(key > k){
                if(left != null)
                    return left.lookup(k);
                else
                    return null;
            }
            else{
                if(right != null)
                    return right.lookup(k);
                else
                    return null;
            }
        }

        public void print() {
            if(left != null)
                left.print();
            
            System.out.println(" key: " + key + "\tvalue: " + value);
            
            if(right != null)
                right.print();
        }
    }

    public class TreeIterator implements Iterator<Integer> {
            
            private Node next;
            private Queue<Node> queue;

            public TreeIterator() {
                queue = new Queue<Node>();

                if(root != null){
                    queue.enqueue(root);
                }
                else
                    queue = null;
                
            }

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }
            

            @Override
            public Integer next() {
                if (queue.isEmpty()) 
                    return null; 

                Node cur = queue.dequeue();

                if (cur.left != null) 
                    queue.enqueue(cur.left);
                
                if (cur.right != null) 
                    queue.enqueue(cur.right);

                return cur.value;
            }
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        }
  
    public BinaryTree() {
        root = null;
    }

    public void add(Integer key, Integer value){
        if(root == null)
            root = new Node(key, value);
        else
            root.add(key,value);

    }

    public Integer lookup (Integer k ) {
        if(root != null)
            return root.lookup(k);
        else
            return null;
    }

    public static void main(String[] args){
        
        BinaryTree tree = new BinaryTree();
        tree.add(5,105);
        tree.add(2,102);
        tree.add(7,107);
        tree.add(1,101);
        tree.add(8,108);
        tree.add(6,106);
        tree.add(3,103);

        for (Integer i : tree)
            System.out.println("next value " + i);

    }


    
}