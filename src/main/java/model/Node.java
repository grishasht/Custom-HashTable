package model;

public class Node {
    private int hash;
    private int key;
    private long value;

    public Node(int hash, int key, long value) {
        this.hash = hash;
        this.key = key;
        this.value = value;
    }


    public int getHash() {
        return hash;
    }

    public int getKey() {
        return key;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "hash=" + hash +
                "key=" + key +
                "value=" + value + "\n";
    }
}
