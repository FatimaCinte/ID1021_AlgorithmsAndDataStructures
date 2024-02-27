import java.util.Iterator;

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
            private Stack<Node> stack;

            public TreeIterator() {
                stack = new Stack<Node>();

                if(root ==null){
                    stack = null;
                }
                else{
                    Node cur = root;
                    while(cur.left != null){
                        stack.push(cur);
                        cur = cur.left;
                    }
                    next = cur;
                }

            }

            @Override
            public boolean hasNext() {
                return next != null;

            }

            @Override
            public Integer next() {
                Integer ret = next.value;
                if(next.right == null)
                        next = stack.pop();
                else{
                    Node cur = next.right;
                    while(cur.left != null){
                        stack.push(cur);
                        cur = cur.left;
                    }
                    next = cur;
                }
                
                return ret;
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

    /*public BinaryTree makeTree(Integer n, Integer[] keys, BinaryTree tree) {

        for(Integer i = 0; i < n; i++)
            tree.add(keys[i], i);
        
        return tree;
    }*/


}