package com.epam.javase.t01;

import java.io.*;

/**
 * Represents a class to get statistics of usage java keywords in the specified file.
 *
 * Created by aivanov on 3/10/2017.
 */
public class ByteStreamStatisticsWriter {

    /**
     * Reads the source file and writes keyword usage statistics to the specified output file.
     *
     * @param input source file
     * @param output output file
     */
    public static void getKeywordsUsageStat(File input, File output) {

        try (InputStream in = new BufferedInputStream(new FileInputStream(input));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(output))) {

            String data = "";
            byte[] buffer = new byte[1024];
            int bytesRead = 0;

            while((bytesRead = in.read(buffer)) > 0) {
                data += new String(buffer, 0, bytesRead);
            }

            byte[] result = JavaKeywordsUsageCalculator.getStatistics(data).getBytes();
            out.write(result);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

