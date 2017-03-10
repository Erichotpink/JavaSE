package com.epam.javase.t01;

import java.util.*;

/**
 * Utility class to get Java keywords list.
 *
 * Created by aivanov on 3/10/2017.
 */
public final class JavaKeywords {

    public static Set<String> getJavaKeywords() {
        ResourceBundle bundle = PropertyResourceBundle.getBundle("com.epam.javase.t01.javakeywords");

        Set<String> keywords = new TreeSet<>();
        bundle.keySet().forEach(s -> keywords.addAll(Arrays.asList(bundle.getString(s).split(";"))));

        return keywords;
    }
}