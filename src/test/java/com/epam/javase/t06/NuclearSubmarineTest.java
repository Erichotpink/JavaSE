package com.epam.javase.t06;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aivanov on 2/28/2017.
 */
public class NuclearSubmarineTest {

    @Test
    public void startEngineOnNuclearSubmarine() throws Exception {
         NuclearSubmarine submarine = new NuclearSubmarine();

         submarine.startEngine();
         assertTrue(submarine.isRunning());

         submarine.stopEngine();
         assertFalse(submarine.isRunning());
    }
}