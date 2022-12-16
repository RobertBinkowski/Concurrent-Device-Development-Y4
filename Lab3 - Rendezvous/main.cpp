#include "Semaphore.h"
#include <pthread.h>
#include <iostream>
#include <thread>
#include <chrono>

/*! \class Signal
    \brief An Implementation of a Rendezvous using Semaphores

   Uses C++11 features such as mutex and condition variables to implement an example of a rendezvous for threads

*/
// I create a barrier to threads
pthread_barrier_t barrier;
/*! displays a message that is split in to 2 sections to show how a rendezvous works*/
void taskOne(std::shared_ptr<Semaphore> firstSem, std::shared_ptr<Semaphore> secondSem, int delay)
{
  std::this_thread::sleep_for(std::chrono::seconds(delay));
  std::cout << "Task One has arrived! " << std::endl;
  // THIS IS THE RENDEZVOUS POINT!
  pthread_barrier_wait(&barrier);
  std::cout << "Task One has left!" << std::endl;
}
/*! displays a message that is split in to 2 sections to show how a rendezvous works*/
void taskTwo(std::shared_ptr<Semaphore> firstSem, std::shared_ptr<Semaphore> secondSem, int delay)
{
  std::this_thread::sleep_for(std::chrono::seconds(delay));
  std::cout << "Task Two has arrived " << std::endl;
  // THIS IS THE RENDEZVOUS POINT!
  pthread_barrier_wait(&barrier);
  std::cout << "Task Two has left " << std::endl;
}
/**
 * @brief Task to lock two tasks and display them in a specific order
 *
 * @return int
 */
int main(void)
{
  pthread_barrier_init(&barrier, NULL, 2);
  std::thread threadOne, threadTwo;
  std::shared_ptr<Semaphore> sem1(new Semaphore);
  std::shared_ptr<Semaphore> sem2(new Semaphore);
  int taskOneDelay = 5;
  int taskTwoDelay = 1;
  /**< Launch the threads  */
  threadOne = std::thread(taskTwo, sem1, sem2, taskTwoDelay);
  threadTwo = std::thread(taskOne, sem1, sem2, taskOneDelay);
  std::cout << "Launched from the main\n";
  threadOne.join();
  threadTwo.join();
  pthread_barrier_destroy(&barrier);
  return 0;
}
