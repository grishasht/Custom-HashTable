package controller;

import model.HashMap;
import model.Map;

public class Main {
    public static void main(String[] args) {
        Map map = new HashMap(128);

        for (int i = 0; i < 65; i++) {
            map.put(i * i, i);
        }

        //map.put(3, 3);

        Long result = map.get(3);
        System.out.println(result);
    }
}
