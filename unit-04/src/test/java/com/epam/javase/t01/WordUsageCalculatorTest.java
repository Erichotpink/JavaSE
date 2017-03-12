package com.epam.javase.t01;

import com.epam.javase.t01.ByteStreamStatisticsWriter;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/12/2017.
 */
public class WordUsageCalculatorTest {
    @Test
    public void getKeywordsUsageStat() throws Exception {
        String src = "C:/Temp/ByteStreamStatisticsWriter.java";
        String dst = "C:/Temp/Result.txt";

        File source = new File(src);
        File dest = new File(dst);

        ByteStreamStatisticsWriter.getKeywordsUsageStat(source, dest);

        assertTrue(dest.exists());
    }

}