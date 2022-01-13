/**
*
*       @author      : Robert Binkowksi - C00237917
*       Date        : 13/1/22
*       Topic       : Locking threads with Mutex
*/
package LabTwo.Mutex;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static final int MAX_T = 4;

    public static void main(String[] args) {
        IntegerObj total = new IntegerObj(0);

        Runnable r1 = new Task("task 1", total);
        Runnable r2 = new Task("task 2", total);
        Runnable r3 = new Task("task 3", total);
        Runnable r4 = new Task("task 4", total);

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);

        pool.shutdown();

        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("total is: " + total.value);
    }
}