/**
*
*       @author      : Robert Binkowksi - C00237917
*       Date        : 13/1/22
*       Topic       : Locking threads with Mutex
*/
package LabTwo.Mutex;

/**
 *
 * @author Robert Binkowski
 */
public class Task implements Runnable {
    private String name;
    private IntegerObj total;

    public Task(String task_1, IntegerObj total) {
        name = task_1;
        this.total = total;
    }

    public void run() {

        try {

            for (int i = 0; i <= 500; i++) {

                total.inc();

                if (i % 100 == 0) {
                    Thread.sleep(100);

                }

            }
            System.out.println(name + " complete");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}