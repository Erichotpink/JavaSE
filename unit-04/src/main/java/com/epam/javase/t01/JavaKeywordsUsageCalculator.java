package com.epam.javase.t01;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class to get Java keywords set.
 *
 * Created by aivanov on 3/10/2017.
 */
public final class JavaKeywordsUsageCalculator {

    public static String getStatistics(String str) {
        ResourceBundle bundle = PropertyResourceBundle.getBundle("com.epam.javase.t01.javakeywords");

        Set<String> keywords = new TreeSet<>();
        bundle.keySet().forEach(s -> keywords.addAll(Arrays.asList(bundle.getString(s).split(";"))));

        StringBuilder result = new StringBuilder();
        for (String s : keywords) {
            int i = getOneWordUsageStatistics(s, str);
            if (i > 0) {
                result.append(s + ":" + i + System.lineSeparator());
            }
        }
        return result.toString();
    }

    /**
     * Parse the specified string and count occurrences of the specified keyword.
     *
     * @param keyword keyword
     * @param str string to be parsed
     * @return number of occurrence
     */
    public static int getOneWordUsageStatistics(String keyword, String str) {
        int count = 0;

        String pattern = "\\b(" + keyword + ")\\b";

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);

        while (m.find()) {
            count++;
        }

        return count;
    }
}
