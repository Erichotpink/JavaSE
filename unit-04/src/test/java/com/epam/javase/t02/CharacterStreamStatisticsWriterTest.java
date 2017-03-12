package com.epam.javase.t02;

import com.epam.javase.t01.ByteStreamStatisticsWriter;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/12/2017.
 */
public class CharacterStreamStatisticsWriterTest {
    @Test
    public void TestIfResultFileCreatedAndNotEmpty() throws Exception {
        String src = "C:/Temp/JavaKeywords.java";
        String dst = "C:/Temp/Result.txt";

        File source = new File(src);
        File dest = new File(dst);

        ByteStreamStatisticsWriter.writeResult(source, dest);

        assertTrue(dest.exists());
        assertTrue(dest.length() > 0);
    }

    @Test
    public void TestIfEmptyResultFileCreated() throws Exception {
        String src = "C:/Temp/Empty.java";
        String dst = "C:/Temp/Empty.txt";

        File source = new File(src);
        source.createNewFile();
        File dest = new File(dst);

        ByteStreamStatisticsWriter.writeResult(source, dest);

        assertTrue(dest.exists());
    }
}