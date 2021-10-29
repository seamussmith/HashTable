package main;

import java.util.HashMap;

public class App
{
    public static void main(String[] args) 
    {
        var x = new HashMap<Integer, Integer>();
        x.put(2, 2);
        System.out.println(x.get(2));
        System.out.println(x.containsKey(2));
    }
}
