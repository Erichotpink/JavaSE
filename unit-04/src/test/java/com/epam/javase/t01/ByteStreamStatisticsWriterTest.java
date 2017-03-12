package com.epam.javase.t01;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/11/2017.
 */
public class ByteStreamStatisticsWriterTest {
    @Test
    public void TestIfResultFileCreated() throws Exception {
        String src = "C:/Temp/ByteStreamStatisticsWriter.java";
        String dst = "C:/Temp/Result.txt";

        File source = new File(src);
        File dest = new File(dst);

        ByteStreamStatisticsWriter.getKeywordsUsageStat(source, dest);

        assertTrue(dest.exists());
    }

    @Test
    public void TestIfEmptyResultFileCreated() throws Exception {
        String src = "C:/Temp/Empty.java";
        String dst = "C:/Temp/Empty.txt";

        File source = new File(src);
        source.createNewFile();
        File dest = new File(dst);

        ByteStreamStatisticsWriter.getKeywordsUsageStat(source, dest);

        assertTrue(dest.exists());
    }



}