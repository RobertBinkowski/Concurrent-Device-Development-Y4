/**
*
*       @author     : Robert Binkowksi - C00237917
*       Date        : 13/1/22
*       Topic       : 
*/
package LabThree;

public class ReusableBarrier {

    private int n;
    private int count;
    private ReusableBarrier mutex;
    private ReusableBarrier barrier1;
    private ReusableBarrier barrier2;

    ReusableBarrier(int n) {
        this.n = n;
        count = 0;
        mutex = new ReusableBarrier(1); // mutual exclusion token
        barrier1 = new ReusableBarrier(0); // barrier closed at start
        barrier2 = new ReusableBarrier(0); // barrier closed at start
    }

    public void phase1() throws InterruptedException {
        mutex.acquire();
        count += 1;
        if (count == n)
            barrier1.release(n); // unlock first barrier for n threads
        mutex.release();
        barrier1.acquire();
    }

    private void release() {
    }

    private void release(int n) {
    }

    private void acquire() {
    }

    public void phase2() throws InterruptedException {
        mutex.acquire();
        count -= 1;
        if (count == 0)
            barrier2.release(n); // unlock second barrier for n threads
        mutex.release();
        barrier2.acquire();

    }

    public void b_wait() throws InterruptedException {
        phase1(); // all go through barrier
        phase2(); // all reset the barrier for reuse
    }
}