public class MyHashTable<K, V> {
    private class HashNode<K,V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }
    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable() {
        chainArray = (HashNode<K, V>[]) new HashNode[11];
        size = 0;
    }

    public MyHashTable(int M) {
        chainArray = (HashNode<K, V>[]) new HashNode[M];
        size = 0;
    }

    private int hash(K key) {
        int hashCode = key.hashCode();
        return hashCode % size;
    }

    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> newNode = chainArray[index];

        if(chainArray[index] == null) {
            chainArray[index] = newNode;
        }
        else {
            HashNode<K, V> current = chainArray[index];
            while(current.next != null) {
                if(current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];

        while(current != null) {
            if(current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> previous = null;

        while(current != null) {
            if(current.key.equals(key)) {
                if(previous == null) {
                    chainArray[index] = current.next;
                }
                else {
                    previous.next = current.next;
                }
                size--;
                return current.value;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(V value) {
        int index = 0;
        while(index < chainArray.length) {
            HashNode<K, V> current = chainArray[index];
            while(current != null) {
                if(current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
            index++;
        }
        return false;
    }

    public K getKey(V value) {
        int index = 0;
        while(index < chainArray.length) {
            HashNode<K, V> current = chainArray[index];
            while(current != null) {
                if(current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
            index++;
        }
        return null;
    }

}
