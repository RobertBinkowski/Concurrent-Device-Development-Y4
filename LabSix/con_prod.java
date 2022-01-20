package LabSix;

/**
 * @param producer-consumer
 * @author Robert Binkowski - C00237917
 *         Date - 20/1/22
 *         Purpouse - These are the barebones of the consumer and producer
 * 
 */

class Consumer implements Runnable {
    Queue queue;

    Consumer(Queue qeue) {
        this.queue = qeue;
        new Thread(this, "Consumer").start();
    }

    public void run() {
        for (int i = 0; i < 5; i++)
            queue.get();
    }
}

class Producer implements Runnable {
    Queue queue;

    Producer(Queue q) {
        this.queue = q;
        new Thread(this, "Producer").start();
    }

    public void run() {
        for (int i = 0; i < 5; i++)
            queue.put(i);
    }
}