//public class Trees<K,V>{
public class Trees{

    Node root;
    
    private class Node {
        Integer key;
        Integer value;
        Node left, right;

        private Node(Integer k, Integer v) {
            key = k;
            value = v;
        }

        private void add(Integer k, Integer v){
            if(key == k)
                value = v;
            else if(key > k){
                if(left != null)
                    left.add(k, v);
                else
                    left = new Node(k,v);                    
                }
            else{
                if(left != null)
                    left.add(k, v);
                else
                    left = new Node(k,v);     
            }
        }
        
    //ändra alla Integer till oberoende parameter, <K, V> 
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

        private Node delete ( Integer k ) {
            if (key == k ) {
            // what do we do ?????????????
            if ( this . left == null )
                return this . right ;
            if ( this . right == null )
                return this . left ;
                //////????????????
            }

            if(key > k){
                if(left != null)
                    left = left.delete(k);
                else
                    if(left != null)
                        left = left.delete(k);
            }
            return this;
        }
            
    }

    public void add(Integer k, Integer val){
        if(root != null){
            root.add(k,val);
        }
        else{
            root = new Node(k, val);
        }
    }

    
    /*public void add_inc(int k, int val){
        if(root != null){
            Node cur = root;
            Node prv = null;
            while(cur != null){
                if(cur.key == k)
                    cur.value = val;
                else if(cur.key > k){
                    prv = cur;
                    cur = cur.left;
                }
                else{
                    prv = cur;
                    cur = cur.right;
                }
            }
            if(prv != null){
                //  ....mf ...övning he said
            }
        }
        else{
            root = new Node(k, val);
        }
    }*/

    public Integer lookup (Integer k ) {
        if(root != null)
            return root.lookup(k);
        else
            return null;
    }

    public void delete(Integer key){
        if ( root == null )
            root = root.delete ( key );
    }

}