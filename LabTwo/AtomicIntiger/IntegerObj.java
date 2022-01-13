/**
*
*       @author     : Robert Binkowksi - C00237917
*       Date        : 13/1/22
*       Topic       : Using AtomicIntiger to increment an intiger between threads
*/
package LabTwo.AtomicIntiger;

import java.util.concurrent.atomic.AtomicInteger;

class IntegerObj {
    AtomicInteger value = new AtomicInteger(1);

    IntegerObj(AtomicInteger val) {
        this.value = val;
    }

    AtomicInteger inc() {
        value.incrementAndGet();
        return this.value;
    }
}