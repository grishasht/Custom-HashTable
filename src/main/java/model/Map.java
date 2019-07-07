package model;

public interface Map {

    void put(int key, long value);

    Long get(int key);

    int size();
}
