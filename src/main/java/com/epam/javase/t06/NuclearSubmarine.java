package com.epam.javase.t06;

import com.epam.javase.t07.ClassDefenition;

/**
 * Represents a nuclear submarine. Contains methods startEngine/stopEngine to engine control and
 * isRunning to check engine status;
 *
 * Created by aivanov on 2/28/2017.
 */

@ClassDefenition (
        author = "Andrey Ivanov",
        created = "2/28/2017",
        version = 1
)
public class NuclearSubmarine {

    private boolean isRunning;
    private NuclearSubmarineEngine engine;

    public NuclearSubmarine() {
        engine = new NuclearSubmarineEngine();
    }

    /**
     * Check if the enginge is running;
     *
     * @return true if the submarine engine is started
     */
    public boolean isRunning() {return isRunning;}

    /**
     * Start engine.
     */
    public void startEngine() {
        engine.run();
    }

    /**
     * Stop engine.
     */
    public void stopEngine() {
        engine.stop();
    }

    private class NuclearSubmarineEngine {

        private void run() {
            if (!NuclearSubmarine.this.isRunning) {
                NuclearSubmarine.this.isRunning = !NuclearSubmarine.this.isRunning;
                System.out.println("Engine started");
            }
        }

        private void stop() {
            if (NuclearSubmarine.this.isRunning) {
                NuclearSubmarine.this.isRunning = !NuclearSubmarine.this.isRunning;
                System.out.println("Engine stopped");
            }
        }
    }
}
