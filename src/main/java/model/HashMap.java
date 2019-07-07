package model;

import model.exception.FullHashMapException;

import java.util.Arrays;
import java.util.Objects;

public class HashMap implements Map{
    /**
     * My custom hash map with open addressing.
     * According to task it works for key - int and value - long.
     * But, if you add <K, V> generic to map declaration, it will work
     * for all types of key and value.
     */
    private Node[] nodes;
    private int size = 0;

    public HashMap(int nodesSize) {
        this.nodes = new Node[nodesSize];
    }

    @Override
    public void put(int key, long value) {
        /**
         * Checks for hash map capacity and if it's full throws an exception.
         * Then gets keys hash code and generates buckets index for key.
         * If the index isn't free, finds next free position in buckets.
         * When position is found creates new node in bucket and saves
         * the hash map size.
         */
        if (size == nodes.length)
            try {
                throw new FullHashMapException("All hash map buckets are full");
            } catch (FullHashMapException e) {
                e.printStackTrace();
            }

        int keyHash = Objects.hashCode(key);
        int index = keyHash & (nodes.length - 1);

        int i = 1;
        while (nodes[index] != null) {

            if (nodes[index].getHash() == Objects.hashCode(key)) break;

            index = changeIndex(keyHash, i);

            i++;
        }

        nodes[index] = new Node(keyHash, key, value);
        size++;
    }

    @Override
    public Long get(int key) {
        /**
         * Overloaded method for user executing.
         */
        return this.get(key, 0);
    }

    private Long get(int key, int i) {
        /**
         * @return value by key.
         * Finds index of the key in buckets in the same way as it was
         * calculated while putting.
         * Stops recursion if all items were taken over.
         */
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

    @Override
    public int size() {
        /**
         * Just returns calculated size of hash map.
         */
        return this.size;
    }

    private int changeIndex(int keyHash, int i) {
        /**
         * Changes index of bucket using Linear Probing
         * Hi = (Hash(X) + i) % HashMapSize
         */
        return (keyHash + i) & (nodes.length - 1);
    }

    private void expandNodes() {
        /**
         * Temp method for auto-changing size of the nodes array.
         * Works for put() method, but it makes impossible to
         * realize the get() method.
         */
        Node[] tmp = nodes;
        nodes = new Node[(int) (nodes.length * 1.5)];
        if (tmp.length >= 0)
            System.arraycopy(tmp, 0, nodes, 0, tmp.length);
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes)
                .replace("[", "")
                .replace("]", "")
                .replace("null,", "null\n")
                .replace(",", "")
                .replace(" ", "")
                + "\nnodes size = " + nodes.length;
    }
}
