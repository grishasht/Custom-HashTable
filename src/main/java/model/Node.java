package model;

class Node {
    private int hash;
    private int key;
    private long value;

    Node(int hash, int key, long value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }


    int getHash() {
        return hash;
    }

    int getKey() {
        return key;
    }

    long getValue() {
        return value;
    }
}
