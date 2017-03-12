package com.epam.javase.t02;

import com.epam.javase.t01.JavaKeywordsUsageCalculator;

import java.io.*;
import java.util.Objects;

/**
 * Created by aivanov on 3/10/2017.
 */
public class CharacterStreamStatisticsWriter {

    /**
     * Reads the source file and writes keyword usage statistics to the specified output file.
     *
     * @param input source file
     * @param output output file
     */
    public static void getKeywordsUsageStat(File input, File output) {

        try (BufferedReader in = new BufferedReader(new FileReader(input));
             BufferedWriter out = new BufferedWriter(new FileWriter(output))) {

            StringBuilder data = new StringBuilder();
            String oneLine = "";

            while(!Objects.isNull(oneLine = in.readLine())) {
                data.append(oneLine);
            }

            out.write(JavaKeywordsUsageCalculator.getStatistics(data.toString()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
