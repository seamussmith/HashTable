package main;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Set;

public class HashTable<TKey, TValue> extends AbstractMap
{
    LinkedList<HashPair>[] table = new LinkedList[37];
    public HashTable()
    {
        for (int i = 0; i < table.length; ++i)
        {
            table[i] = new LinkedList<HashPair>();
        }
    }
    int hashFunc(Object o)
    {
        return Math.abs(o.hashCode()) % table.length;
    }
    @Override
    public Object put(Object key, Object value)
    {
        var hash = hashFunc(key);
        var find = table[hash].stream().dropWhile(x -> !x.key.equals(key)).findFirst().orElse(null);
        if (find == null)
            table[hash].addLast(new HashPair(key, value));
        else
            find.value = value;
        return find;
    }
    @Override
    public Object get(Object key)
    {
        var hash = hashFunc(key);
        var find = table[hash].stream().dropWhile(x -> !x.key.equals(key)).findFirst();

        return find.orElseThrow(() -> new IndexOutOfBoundsException());

    }
    public class HashPair
    {
        Object key;
        Object value;
        HashPair(Object key, Object val)
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
