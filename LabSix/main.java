package LabSix;

class Main {

    /**
     * @param main
     * @author Robert Binkowski - C00237917
     *         Date - 20/1/22
     *         Purpouse - Project created to solve the producer consumer problem
     * 
     */
    public static void main(String args[]) {
        Queue queue = new Queue();
        new Consumer(queue);
        new Producer(queue);
    }
}