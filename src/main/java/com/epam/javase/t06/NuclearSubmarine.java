package com.epam.javase.t06;

/**
 * Created by aivanov on 2/28/2017.
 */
public class NuclearSubmarine {

    private boolean isRunning;
    private NuclearSubmarineEngine engine;

    public NuclearSubmarine() {
        engine = new NuclearSubmarineEngine();
    }

    public boolean isRunning() {return isRunning;}

    public void startEngine() {
        engine.run();
    }

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

        public void stop() {
            if (NuclearSubmarine.this.isRunning) {
                NuclearSubmarine.this.isRunning = !NuclearSubmarine.this.isRunning;
                System.out.println("Engine stopped");
            }
        }
    }
}
