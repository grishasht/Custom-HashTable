package model;

import model.exception.FullHashMapException;
import java.util.Objects;

/**
 * My custom hash map with open addressing.
 * According to task it works for key - int and value - long.
 * But, if you add <K, V> generic to map declaration, it will work
 * for all types of key and value.
 */
public class HashMap implements Map {
    private Node[] nodes;
    private int size = 0;

    public HashMap(int nodesSize) {
        this.nodes = new Node[nodesSize];
    }

    /**
     * Checks for hash map capacity and if it's full throws an exception.
     * Then gets keys hash code and generates buckets index for key.
     * If the index isn't free, finds next free position in buckets.
     * When position is found creates new node in bucket and saves
     * the hash map size.
     */
    @Override
    public void put(int key, long value) {
        if (nodes != null)
        try {
            if (size == nodes.length)
                throw new FullHashMapException("All hash map buckets are full");


            int keyHash = Objects.hashCode(key);
            int index = keyHash & (nodes.length - 1);
            boolean isFull = false;

            int i = 1;
            while (nodes[index] != null) {

                if (nodes[index].getHash() == Objects.hashCode(key)) {
                    isFull = true;
                    break;
                }

                index = changeIndex(keyHash, i);

                i++;
            }

            nodes[index] = new Node(keyHash, key, value);
            if (!isFull)
                size++;
        } catch (FullHashMapException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return value by key.
     * Finds index of the key in buckets in the same way as it was
     * calculated while putting.
     * Stops recursion if all items were taken over.
     *
     * Overloaded method for user executing.
     */
    @Override
    public Long get(int key) {
        return this.get(key, 0);
    }

    private Long get(int key, int i) {
        int keyHash = Objects.hashCode(key);
        int index = changeIndex(keyHash, i);

        if (nodes[index] == null || i == nodes.length) {
            return null;
        } else if (nodes[index].getHash() == keyHash) {
            return nodes[index].getValue();
        } else {
            return get(key, ++i);
        }
    }

    /**
     * Just returns calculated size of hash map.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Changes index of bucket using Linear Probing
     * Hi = (Hash(X) + i) % HashMapSize
     */
    private int changeIndex(int keyHash, int i) {
        return (keyHash + i) & (nodes.length - 1);
    }

    /**
     * Temp method for auto-changing size of the nodes array.
     * Works for put() method, but it makes impossible to
     * realize the get() method.
     */
    private void expandNodes() {
        Node[] tmp = nodes;
        nodes = new Node[(int) (nodes.length * 1.5)];
        if (tmp.length >= 0)
            System.arraycopy(tmp, 0, nodes, 0, tmp.length);
    }

}
