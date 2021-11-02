package main;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

class KeyNotFoundException extends RuntimeException {}

public class HashTable<TKey, TValue> extends AbstractMap<TKey, TValue> implements Iterable<TValue>
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
            table[hash].addFirst(new HashPair(key, value));
            return value;
        }
        else
        {
            return find.value = value;
        }
    }
    public TValue get(Object key)
    {
        var hash = hashFunc(key);
        var find = table[hash].stream().dropWhile(x -> !x.key.equals(key)).findFirst();
        find.orElseThrow(() -> new KeyNotFoundException());
        
        return find.get().value;

    }
    public class HashPair implements Entry<TKey, TValue>
    {
        TKey key;
        TValue value;
        HashPair(TKey key, TValue val)
        {
            this.key = key;
            this.value = val;
        }
        @Override
        public TKey getKey()
        {
            return key;
        }
        @Override
        public TValue getValue()
        {
            return value;
        }
        @Override
        public TValue setValue(TValue value)
        {
            this.value = value;
            return value;
        }
        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }
    
    @Override
    public Set<Entry<TKey, TValue>> entrySet() {
        var entrySet = new HashSet<Entry<TKey, TValue>>();
        for (var i : table)
        for (var j : i)
            entrySet.add(j);
        return entrySet();
    }
    @Override
    public Iterator<TValue> iterator() {
        return entrySet().stream().map(x -> x.getValue()).iterator();
    }

}
