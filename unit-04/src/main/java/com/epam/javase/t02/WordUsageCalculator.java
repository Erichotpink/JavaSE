package com.epam.javase.t02;

import java.io.*;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.epam.javase.t01.*;

/**
 * Created by aivanov on 3/12/2017.
 */
public abstract class WordUsageCalculator {

    /**
     * Reads the source file and writes keyword usage statistics to the specified output file.
     *
     * @param input source file
     * @param output output file
     */
    public static void getKeywordsUsageStat(File input, File output) {

        try (BufferedReader in = new BufferedReader(new FileReader(input));
             Writer out = new FileWriter(output)) {

            StringBuffer data = new StringBuffer();
            String oneLine = "";

            while((oneLine = in.readLine()) != null) {
                data.append(oneLine);
            }

            out.write(countKeywordsStat(data.toString()));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Count keywords usage statistics for the specified string.
     *
     * @param str string to be parsed
     * @return String with results in the following format 'keyword(String):count(int);'
     */
    public static String countKeywordsStat(String str) {
        StringBuffer data = new StringBuffer();
        Set<String> set = JavaKeywords.getJavaKeywords();

        for (String s : set) {
            int result = KeywordsReader.countUsageStat(s, str);
            if (result > 0) {
                data.append(s + ":" + result + ";");
            }
        }

        return data.toString();
    }

    /**
     * Parse the specified string and count occurrences of the specified keyword.
     *
     * @param keyword keyword to be counted
     * @param str source to be parsed
     * @return number of occurrences
     */
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
