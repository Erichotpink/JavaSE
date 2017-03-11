package com.epam.javase.t01;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/11/2017.
 */
public class KeywordsReaderTest {
    @Test
    public void TestIfResultFileCreated() throws Exception {
        String src = "C:/Temp/KeywordsReader.java";
        String dst = "C:/Temp/Result.txt";

        File source = new File(src);
        File dest = new File(dst);

        KeywordsReader.getKeywordsUsageStat(source, dest);

        assertTrue(dest.exists());
    }

    @Test
    public void TestIfEmptyResultFileCreated() throws Exception {
        String src = "C:/Temp/Empty.java";
        String dst = "C:/Temp/Empty.txt";

        File source = new File(src);
        source.createNewFile();
        File dest = new File(dst);

        KeywordsReader.getKeywordsUsageStat(source, dest);

        assertTrue(dest.exists());
    }

    @Test
    public void testDataFormatPositiveCase() throws Exception {
        String str = "public void main(String... args)";
        String pattern = "public:1;void:1;";

        assertTrue(KeywordsReader.countKeywordsStat(str).equals(pattern));
    }

    @Test
    public void testDataFormatNegativeCase() throws Exception {
        String str = "publicd dvoid main(String... args)";
        String pattern = "";

        assertTrue(KeywordsReader.countKeywordsStat(str).equals(pattern));
    }

    @Test
    public void testResultStatisticsPositiveAndNegativeCases() throws Exception {

        String str = "public static void main(String... args)";

        assertTrue(KeywordsReader.countUsageStat("public", str) == 1);
        assertTrue(KeywordsReader.countUsageStat("static", str) == 1);
        assertTrue(KeywordsReader.countUsageStat("void", str) == 1);

        str = "public static void main(String... args){try{}catch{}tttcatch catchb}";

        assertTrue(KeywordsReader.countUsageStat("try", str) == 1);
        assertTrue(KeywordsReader.countUsageStat("catch", str) == 1);
    }

}