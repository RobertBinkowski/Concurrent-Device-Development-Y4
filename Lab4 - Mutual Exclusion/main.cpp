#include "Semaphore.cpp"
#include <iostream>
#include <thread>
#include <vector>

static const int num_threads = 100;
Semaphore mutex(1);
int sharedVariable = 0;

/*!
 *    \fn updateTask
 *    \brief An Implementation of Mutual Exclusion using Semaphores
 *
 *   Uses C++11 features such as mutex and condition variables to implement
 *   an example of a rendezvous for threads
 *
 */

// displays a message that is split in to 2 sections to show how a rendezvous works
void updateTask(std::shared_ptr<Semaphore> firstSem, int numUpdates)
{

  for (int i = 0; i < numUpdates; i++)
  {
    mutex.Wait();
    std::cout << sharedVariable << std::endl;
    sharedVariable++;
    mutex.Signal();
  }
}
/**
 * @brief Main to count tp to 1000 and locking each
 *        thread to display them in a sequential manner
 *
 * @return int
 */
int main(void)
{
  std::vector<std::thread> vt(num_threads);
  std::shared_ptr<Semaphore> aSemaphore(new Semaphore);
  /**< Launch the threads  */
  int i = 0;
  for (std::thread &t : vt)
  {
    t = std::thread(updateTask, aSemaphore, 1000);
  }
  std::cout << "Launched from the main\n";
  /**< Join the threads with the main thread */
  for (auto &v : vt)
  {
    v.join();
  }
  std::cout << sharedVariable << std::endl;
  return 0;
}
