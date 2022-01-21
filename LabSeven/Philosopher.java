package LabSeven;

import java.util.concurrent.Semaphore;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @param queue
 * @author Robert Binkowski - C00237917
 *         Date - 21/1/22
 *         Purpouse - This is the barebone of the Philosopher
 * 
 */
public class Philosopher implements Runnable {

    private Object leftFork;
    private Object rightFork;

    public Philosopher(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException {
        System.out.println(
                Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }

    @Override
    public void run() {
        int run = 5;
        try {
            while (run != 0) {
                run--;
                doAction(System.nanoTime() + ": Thinking ????");
                synchronized (leftFork) {
                    doAction(
                            System.nanoTime()
                                    + ": Picked up + left fork");
                    synchronized (rightFork) {
                        // eating
                        doAction(
                                System.nanoTime()
                                        + ": Picked up + right fork - eating");

                        doAction(
                                System.nanoTime()
                                        + ": Put down - right fork");
                    }

                    doAction(
                            System.nanoTime()
                                    + ": Put down - left fork. Back to thinking???");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}