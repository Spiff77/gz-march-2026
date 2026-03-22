package io.treelevel.training.generics;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    public static <T> T findFirst(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static <T> T findLast(List<T> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public static <T> List<T> everyOther(List<T> list) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i += 2) {
            result.add(list.get(i));
        }
        return result;
    }
}
