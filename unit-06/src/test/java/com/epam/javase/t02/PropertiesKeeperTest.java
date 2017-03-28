package com.epam.javase.t02;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by aivanov on 3/15/2017.
 */
public class PropertiesKeeperTest {

    File twoKeys = new File("src/test/resources/com/epam/javase/t02/twoKeysWithEqualsValues.txt");

    @Test
    public void shouldRewriteValueIfMeetEqualKey() throws Exception {
        PropertiesKeeper keeper = new PropertiesKeeper(twoKeys);
        assertThat(keeper.getValue("capital"), equalTo("Saint-Petersburg"));
    }
}