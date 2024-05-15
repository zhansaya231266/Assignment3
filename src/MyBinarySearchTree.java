import java.util.ArrayList;
import java.util.List;

public class MyBinarySearchTree<K extends Comparable<K>, V>  {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            left = right = null;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node current, K key, V val) {
        if(current == null) {
            size++;
            return new Node(key, val);
        }
        int comp = key.compareTo(current.key);
        if(comp < 0) {
            current.left = put(current.left, key, val);
        }
        else {
            current.right = put(current.right, key, val);
        }
        return current;
    }

    public V get(K key) {
        Node current = root;
        while(current != null) {
            int comp = key.compareTo(current.key);
            if(comp < 0) {
                current = current.left;
            }
            else if(comp > 0) {
                current = current.right;
            }
            else {
                return current.val;
            }
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node current, K key) {
        if(current == null) {
            return null;
        }
        int comp = key.compareTo(current.key);
        if(comp < 0) {
            current.left = delete(current.left, key);
        }
        else if(comp > 0) {
            current.right = delete(current.right, key);
        }
        else {
            if(current.left == null && current.right == null) {
                size--;
                return null;
            }
            if(current.right == null) {
                size--;
                return current.left;
            }
            if(current.left == null) {
                size--;
                return current.right;
            }
            current.key = smallestKey(current.left);
            current.val = smallestValue(current.left);
            current.left = delete(current.left, current.key);
        }
        return current;
    }

    private K smallestKey(Node node) {
        return node.right == null ? node.key : smallestKey(node.right);
    }

    private V smallestValue(Node node) {
        return node.right == null ? node.val : smallestValue(node.right);
    }

    public int size() {
        return size;
    }

    public Iterable<HashNode<K,V>> iterator() {
        List<HashNode<K,V>> keys = new ArrayList<>();
        inorderTraversal(root, keys);
        return keys;
    }

    public List<HashNode<K, V>> inorderTraversal() {
        List<HashNode<K, V>> keys = new ArrayList<>();
        inorderTraversal(root, keys);
        return keys;
    }

    private void inorderTraversal(Node node, List<HashNode<K,V>> keys) {
        if(node == null) {
            return;
        }
        inorderTraversal(node.left, keys);
        keys.add(new HashNode<> (node.key, node.val));
        inorderTraversal(node.right, keys);
    }

    public static class HashNode<K, V> {
        private K key;
        private V value;

        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public Node getRoot() {
        return root;
    }
}
