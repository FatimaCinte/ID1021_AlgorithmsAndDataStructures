class Cell<K,V>{ 
    K key;
    V value;
    Cell<K,V> next;

    Cell(K val, V v, Cell<K,V> n){//, Cell prv, Cell nxt){
        this.key = val;
        this.value = v;
        this.next = n;
    }
}