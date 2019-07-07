package model;

import model.exception.FullHashMapException;

import java.util.Arrays;
import java.util.Objects;

public class HashMap {
    private Node[] nodes;
    private int size = 0;

    public HashMap(int nodesSize) {
        this.nodes = new Node[nodesSize];
    }

    public void put(int key, long value) {
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

            if (nodes[index].getHash() == Objects.hashCode(key)) {
                break;
            }

            index = changeIndex(keyHash, i);

            i++;
        }

        nodes[index] = new Node(keyHash, key, value);
        size++;
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

    public Long get(int key) {
        return this.get(key, 0);
    }

    public int size() {
//        for (Node node : nodes) {
//            if (node != null) this.size++;
//        }
        return this.size;
    }

    private int changeIndex(int keyHash, int i) {
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
