/**
*
*       @author      : Robert Binkowksi - C00237917
*       Date        : 13/1/22
*       Topic       : Locking threads with Mutex
*/
package LabTwo.Mutex;

import java.util.concurrent.locks.ReentrantLock;

class IntegerObj {
    int value;
    private ReentrantLock reentrantLock = new ReentrantLock();

    IntegerObj(int val) {
        this.value = val;
    }

    int inc() {
        try {
            reentrantLock.lock();
            this.value++;
            return this.value;
        } finally {
            reentrantLock.unlock();
        }

    }
}