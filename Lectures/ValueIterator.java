class Keylist ValueIterator implements Iterable<V>{ //ValueIterator  interface

    private Cell<K,V> cur;

    public ValueIterator(){
        cur = first; 
    }

}