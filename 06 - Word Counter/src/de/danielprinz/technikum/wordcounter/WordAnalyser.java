package de.danielprinz.technikum.wordcounter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by el17x002 on 16.10.2018.
 */
public class WordAnalyser {

    /**
     * Calculates the number of occurrences of all the given words in the string
     * @param string The String to be analysed
     * @return A map containing the words and their count
     */
    public static Map<String, Integer> getOccurrences(String string) {

        Map<String, Integer> occurrences = new HashMap<>();

        String[] splitString = string.split("[ \n\t]");
        for(String word : splitString) {
            // clean up the string
            word = word.replaceAll(",", "").replaceAll("\\.", "").replaceAll("\\s", "").toLowerCase();
            if(word.length() == 0) continue;

            occurrences.merge(word, 1, Integer::sum);
        }

        return occurrences;
    }

}
