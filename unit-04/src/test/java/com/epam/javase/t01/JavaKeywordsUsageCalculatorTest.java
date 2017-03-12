package com.epam.javase.t01;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 3/12/2017.
 */
public class JavaKeywordsUsageCalculatorTest {

    @Test
    public void testDataFormatPositiveCase() throws Exception {
        String str = "public void main(String... args)";
        String pattern = "public:1;void:1;";

        assertTrue(JavaKeywordsUsageCalculator.getStatistics(str).contains("public:1"));
        assertTrue(JavaKeywordsUsageCalculator.getStatistics(str).contains("void:1"));
    }

    @Test
    public void testDataFormatNegativeCase() throws Exception {
        String str = "publicd dvoid main(String... args)";
        String pattern = "";

        assertTrue(JavaKeywordsUsageCalculator.getStatistics(str).equals(pattern));
    }

    @Test
    public void testStatisticsPositiveAndNegativeCases() throws Exception {

        String str = "public static void main(String... args){switch(str){case 'a': break;default:break}";

        assertTrue(JavaKeywordsUsageCalculator.getOneWordUsageStatistics("public", str) == 1);
        assertTrue(JavaKeywordsUsageCalculator.getOneWordUsageStatistics("switch", str) == 1);
        assertTrue(JavaKeywordsUsageCalculator.getOneWordUsageStatistics("default", str) == 1);
        assertTrue(JavaKeywordsUsageCalculator.getOneWordUsageStatistics("break", str) == 2);

        str = "public static void main(String... args){try{}catch{}tttcatch catchb tryme()}";

        assertTrue(JavaKeywordsUsageCalculator.getOneWordUsageStatistics("try", str) == 1);
        assertTrue(JavaKeywordsUsageCalculator.getOneWordUsageStatistics("catch", str) == 1);
    }

}