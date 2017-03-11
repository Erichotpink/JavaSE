package com.epam.javase.t02;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a class to get usage statistics of the keywords in the specified string.
 *
 * Created by aivanov on 3/10/2017.
 */
public final class WordsUsageCalculator {

    private WordsUsageCalculator() {}

    /**
     * Parse the specified string using the pattern and count occurrences of the specified keywords.
     *
     * @param keywords keywords
     * @param str string to be parsed
     * @param pattern pattern
     * @return number of occurrence
     */
    public static Map<String, Integer> getMultipleWordUsageStatistics(Set<String> keywords, String str, String pattern) {
        Map<String, Integer> result = new HashMap<>();

        for (String s : keywords) {
            result.put(s, getOneWordUsageStatistics(s, str, pattern));
        }

        return result;
    }

    /**
     * Parse the specified string using the pattern and count occurrences of the specified keyword.
     *
     * @param keyword keyword
     * @param str string to be parsed
     * @param pattern pattern
     * @return number of occurrence
     */
    public static int getOneWordUsageStatistics(String keyword, String str, String pattern) {
        int count = 0;

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);

        while (m.find()) {
            count++;
        }

        return count;
    }
}
