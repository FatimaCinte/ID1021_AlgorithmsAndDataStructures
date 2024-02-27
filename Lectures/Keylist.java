import java.util.Iterator;


/*class lectur{

    void add(Integer k,String v);
    
    String lookup(Integer k);

    void remove(Integer k);
    
}*/


/*class Keylist{
    Cell first;
    Integer length;

    public Keylist(){
        first = null;
        length = 0;
    }

    public void add(Integer key, String name){
        first = new Cell(key, name, first);
        length++;
    }

    //n jämförelser
    public String lookup(Integer k){
        for (Cell cur=first; cur !=null; cur = cur.next){
            if(cur.key == k){
                return cur.value;
            }
        }
        return null;
    }

    //log(n) jämförelser
    public String binarySearch(Integer k){

        return null;
    }

    public void remove(Integer k){
        Cell prev = null;
          for (Cell cur=first; cur !=null; cur = cur.next){
            if(cur.key == k){
                if(prev != null)
                    prev.next = cur.next;
                else
                    first = cur.next;
                return;
            }
            prev = cur;
        }
    }


}*/


//class Keylist <K,V>{
class Keylist <K extends Comparable<K>,V>{


    Cell<K,V> first;
    Integer length;

    public Keylist(){
        first = null;
        length = 0;
    }

    public void add(K key, V name){
        first = new Cell<K,V>(key, name, first);
        length++;
    }

    //n jämförelser
    public V lookup(K k){
        for (Cell<K,V> cur=first; cur !=null; cur = cur.next){
            //if(cur.key == k){
            if(cur.key.equals(k)){
                return cur.value;
            }
        }
        return null;
    }

    //log(n) jämförelser
    public V binarySearch(K k){

        return null;
    }

    public void remove(K k){
        Cell<K,V> prev = null;
          for (Cell<K,V> cur=first; cur !=null; cur = cur.next){
            if(cur.key == k){
                if(prev != null)
                    prev.next = cur.next;
                else
                    first = cur.next;
                return;
            }
            prev = cur;
        }
    }

    public Iterator<V> iterator(){
        return new ValueIterator();
    }


}

