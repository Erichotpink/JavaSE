package com.epam.javase.t02;

import com.epam.javase.t01.KeywordsReader;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/12/2017.
 */
public class WordUsageCalculatorTest {
    @Test
    public void getKeywordsUsageStat() throws Exception {
        String src = "C:/Temp/KeywordsReader.java";
        String dst = "C:/Temp/Result.txt";

        File source = new File(src);
        File dest = new File(dst);

        KeywordsReader.getKeywordsUsageStat(source, dest);

        assertTrue(dest.exists());
    }

}