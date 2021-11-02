package main;

import java.util.HashMap;

public class App
{
    public static void main(String[] args) 
    {
        var x = new HashTable<String, Integer>();
        x.put("wow", 2);
        System.out.println(x.get("wow"));
    }
}
