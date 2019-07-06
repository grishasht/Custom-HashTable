package controller;

import model.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        HashMap map = new HashMap();

        map.put(-27, 125l);
        map.put(-2, 125l);
        map.put(0, 125l);
        map.put(0, 999l);
        map.put(4, 125l);
        map.put(11, 125l);
        map.put(34, 125l);

        Random random = new Random();
        for (int i = 0; i < 23; i++){
            map.put(random.nextInt(250), (long)i*2);
        }


  //      System.out.println(map.size());
        System.out.println(map.toString() + "\n" + map.size());
    }
}
