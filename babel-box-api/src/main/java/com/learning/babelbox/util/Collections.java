package com.learning.babelbox.util;

import java.util.LinkedHashSet;

import static java.util.Arrays.asList;

public class Collections<T> {

    public static <T> LinkedHashSet<T> asLinkedSet(T... elements) {
        LinkedHashSet<T> result = new LinkedHashSet<T>();
        result.addAll(asList(elements));
        return result;
    }
}
