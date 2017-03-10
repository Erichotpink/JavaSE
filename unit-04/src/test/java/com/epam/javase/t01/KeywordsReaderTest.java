package com.epam.javase.t01;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/11/2017.
 */
public class KeywordsReaderTest {
    @Test
    public void countKeywordsStat() throws Exception {

    }

    @Test
    public void PositiveAndNegativeCountUsageStatTests() throws Exception {

        String str = "public static void main(String... args)";

        assertTrue(KeywordsReader.countUsageStat("public", str) == 1);
        assertTrue(KeywordsReader.countUsageStat("static", str) == 1);
        assertTrue(KeywordsReader.countUsageStat("void", str) == 1);

        str = "public static void main(String... args){try{}catch{}tttcatch catchb}";

        assertTrue(KeywordsReader.countUsageStat("try", str) == 1);
        assertTrue(KeywordsReader.countUsageStat("catch", str) == 1);
    }

}