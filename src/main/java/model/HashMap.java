package model;

import java.util.Arrays;
import java.util.Objects;

public class HashMap {
    private Node[] nodes = new Node[16];
    private int size = 0;

    public void put(int key, long value) {
        int keyHash = Objects.hashCode(key);
        int index = keyHash & (nodes.length - 1);
        boolean isExist = false;

        for (int i = 1; nodes[index] != null; i++) {

            if (nodes[index].getHash() == Objects.hashCode(key)) {
                isExist = true;
                break;
            }

            index = changeIndex(key, i);

            if (i == (nodes.length - 1)) expandNodes();
        }


        //if (!isExist) {
        nodes[index] = new Node(keyHash, key, value);
        //}
    }

    public long get(int key) {
        long value = 0;
        return value;
    }

    public int size() {
        for (Node node : nodes) {
            if (node != null) this.size++;
        }
        return this.size;
    }

    private int changeIndex(int key, int i) {
        return (Objects.hashCode(key) + i * i) & (nodes.length - 1);
    }

    private void expandNodes() {
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
