package LabSix;

import java.util.concurrent.Semaphore;

/**
 * @param queue
 * @author Robert Binkowski - C00237917
 *         Date - 20/1/22
 *         Purpouse - This is the barebone of the queue created for the consumer
 *         and producer
 * 
 */
class Queue {
    int item;

    /*
     * semCon initialized with 0 permits
     * to ensure put() executes first
     */
    static Semaphore semaphoreOne = new Semaphore(0);

    static Semaphore semaphoreTwo = new Semaphore(1);

    void get() {
        try {
            /*
             * Before consumer can consumes the item,
             * it must get a permit from the first semaphore
             */
            semaphoreOne.acquire();
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e.getLocalizedMessage());
        }

        System.out.println("- Consumer - consumed : " + item);

        /*
         * Once the item is consumed by the consumer semaphore two is released by the
         * producer
         */
        semaphoreTwo.release();
    }

    void put(int item) {
        try {
            /*
             * Before producer can produce an item,
             * it must get permission from semaphoreTwo
             */
            semaphoreTwo.acquire();
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e.getLocalizedMessage());
        }

        this.item = item;

        System.out.println("+ Producer - produced : " + item);

        semaphoreOne.release();
    }
}