package LabSeven;

public class Main {

    /**
     * @param queue
     * @author Robert Binkowski - C00237917
     *         Date - 21/1/22
     *         Purpouse - This is the program that will attempt to fix the dining
     *         table problem with philosophers and forks
     * 
     */
    public static void main(String[] args) throws Exception {

        Philosopher[] philosophers = new Philosopher[5];
        Object[] forks = new Object[philosophers.length];

        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            philosophers[i] = new Philosopher(leftFork, rightFork);

            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}
