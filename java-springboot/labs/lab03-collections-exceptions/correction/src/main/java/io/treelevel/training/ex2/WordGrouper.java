package io.treelevel.training.ex2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordGrouper {

    /**
     * Groups a list of words by their first letter (case-insensitive).
     *
     * Example input:  ["apple", "avocado", "banana", "blueberry", "cherry"]
     * Example output: { 'a': ["apple", "avocado"], 'b': ["banana", "blueberry"], 'c': ["cherry"] }
     *
     * Words with the same first letter go into the same list.
     * Ignore empty strings.
     */
    public static Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        Map<Character, List<String>> result = new HashMap<>();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }
            char key = Character.toLowerCase(word.charAt(0));
            if (!result.containsKey(key)) {
                result.put(key, new ArrayList<>());
            }
            result.get(key).add(word);
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> words = List.of(
            "apple", "avocado", "banana", "blueberry",
            "cherry", "apricot", "blackberry", "clementine"
        );

        Map<Character, List<String>> grouped = groupByFirstLetter(words);

        for (Map.Entry<Character, List<String>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
