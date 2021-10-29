package main;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.IntStream;

public class HashTable<TKey, TValue> extends AbstractMap<TKey, TValue>
{
    final int TABLE_SIZE = 37;
    LinkedList<HashPair>[] table;
    public HashTable()
    {
        // G O D
        table = (LinkedList<HashPair>[])IntStream
                .rangeClosed(0, 37)
                .mapToObj(x -> new LinkedList<HashPair>())
                .toArray();
    }
    int hashFunc(Object o)
    {
        return Math.abs(o.hashCode()) % table.length;
    }
    @Override
    public TValue put(TKey key, TValue value)
    {
        var hash = hashFunc(key);
        var find = table[hash].stream().dropWhile(x -> !x.key.equals(key)).findFirst().orElse(null);
        if (find == null)
            table[hash].addLast(new HashPair(key, value));
        else
            find.value = value;
        return find.value;
    }
    @Override
    public TValue get(Object key)
    {
        var hash = hashFunc(key);
        var find = table[hash].stream().dropWhile(x -> !x.key.equals(key)).findFirst();
        find.orElseThrow(() -> new IndexOutOfBoundsException());
        
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
        @Override
        public boolean equals(Object obj)
        {
            var x = (TKey)obj;
            return (TKey)key == x;
        }
    }
    @Override
    public Set entrySet() {
        // TODO Auto-generated method stub
        return null;
    }
}
