package io.treelevel.training.ex2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordGrouper {

    /**
     * Groups a list of words by their first letter.
     *
     * Example input:  ["apple", "avocado", "banana", "blueberry", "cherry"]
     * Example output: { 'a': ["apple", "avocado"], 'b': ["banana", "blueberry"], 'c': ["cherry"] }
     *
     * Words with the same first letter go into the same list.
     * Ignore empty strings.
     */
    public static Map<Character, List<String>> groupByFirstLetter(List<String> words) {
        // TODO: implement

        // Hint: for each word, extract the first character with word.charAt(0)
        // Check if the map already has a list for that character
        // If not, create a new ArrayList and put it in the map
        // Then add the word to that list

        return null;
    }

    public static void main(String[] args) {
        List<String> words = List.of(
            "apple", "avocado", "banana", "blueberry",
            "cherry", "apricot", "blackberry", "clementine"
        );

        Map<Character, List<String>> grouped = groupByFirstLetter(words);

        // Print each group
        for (Map.Entry<Character, List<String>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
