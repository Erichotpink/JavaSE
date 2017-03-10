package com.epam.javase.t01;

import java.io.*;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a class to get statistics of usage java keywords in the specified file.
 *
 * Created by aivanov on 3/10/2017.
 */
public class KeywordsReader {

    public static void getKeywordsUsageStat(File input, File output) {

        try (InputStream in = new FileInputStream(input);
            OutputStream out = new FileOutputStream(output)) {

        } catch (IOException ex) {
        }
    }

    public static String countKeywordsStat(String str) {
        StringBuffer data = new StringBuffer();
        Set<String> set = JavaKeywords.getJavaKeywords();

        for (String s : set) {
            int result = KeywordsReader.countUsageStat(s, str);
            data.append(s + ":" + result + ";");
        }

        return data.toString();
    }

    public static int countUsageStat(String keyword, String str) {
        String pattern = "(\\b)"+ keyword + "(\\b)";
        int count = 0;

        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);

        while (m.find()) {
            count++;
        }

        return count;
    }
}

