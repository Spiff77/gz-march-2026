package io.treelevel.training.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 3 — Generic Methods
 *
 * Write generic static methods that work with any type.
 */
public class ListUtils {

    /**
     * TODO: Make this method generic.
     *       It should return the first element of any list, or null if the list is empty.
     *       Replace Object with a type parameter T.
     */
    public static Object findFirst(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * TODO: Make this method generic.
     *       It should return the last element of any list, or null if the list is empty.
     *       Replace Object with a type parameter T.
     */
    public static Object findLast(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /**
     * TODO: Make this method generic.
     *       It should return a new list containing only elements at even indices (0, 2, 4, ...).
     */
    public static List everyOther(List list) {
        List result = new ArrayList();
        for (int i = 0; i < list.size(); i += 2) {
            result.add(list.get(i));
        }
        return result;
    }
}
