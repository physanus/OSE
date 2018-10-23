package de.danielprinz.technikum.wordcounter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class MapUtils {

    /**
     * Sorts a copy of the Map by its value in reverse order
     * @param map The map which should be sorted
     * @param <K>
     * @param <V>
     * @return The sorted copy
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue((o1, o2) -> -1 * o1.compareTo(o2)));

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

}
