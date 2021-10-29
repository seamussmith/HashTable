package main;

import java.util.LinkedList;
import java.util.stream.IntStream;

class KeyNotFoundException extends RuntimeException {}

public class HashTable<TKey, TValue>
{ 
    final int TABLE_SIZE = 37;
    LinkedList<HashPair>[] table = new LinkedList[TABLE_SIZE];
    public HashTable()
    {
        for (int i = 0; i < TABLE_SIZE; ++i)
        {
            table[i] = new LinkedList<>();
        }
    }
    int hashFunc(Object o)
    {
        return Math.abs(o.hashCode()) % table.length;
    }
    public TValue put(TKey key, TValue value)
    {
        var hash = hashFunc(key);
        var find = table[hash].stream().dropWhile(x -> !x.key.equals(key)).findFirst().orElse(null);
        if (find == null)
        {
            table[hash].addLast(new HashPair(key, value));
            return value;
        }
        else
            return find.value = value;
    }
    public TValue get(Object key)
    {
        var hash = hashFunc(key);
        var find = table[hash].stream().dropWhile(x -> !x.key.equals(key)).findFirst();
        find.orElseThrow(() -> new KeyNotFoundException());
        
        return find.get().value;

    }
    public class HashPair
    {
        TKey key;
        TValue value;
        HashPair(TKey key, TValue val)
        {
            this.key = key;
            this.value = val;
        }
    }
}
