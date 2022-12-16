// #include "Semaphore.h"
#include "Semaphore.cpp"
#include <iostream>
#include <thread>
#include <unistd.h>

/*!   \class Signal
 *    \brief An Implementation of Threads Using Semaphores
 *
 *   Uses C++11 features such as mutex and condition variables to implement Semaphores in thread functions
 *
 */
std::mutex mutex;
/**
 * @brief Task to print out 1st
 *
 * @param theSemaphore
 * @param delay
 */
void taskOne(std::shared_ptr<Semaphore> theSemaphore, int delay)
{
  mutex.lock();
  sleep(delay);
  std::cout << "I ";
  std::cout << "must ";
  std::cout << "print ";
  std::cout << "first" << std::endl;
  mutex.unlock();
}

/**
 * @brief Task To Print out last
 *
 * @param theSemaphore
 */
void taskTwo(std::shared_ptr<Semaphore> theSemaphore)
{
  mutex.lock();
  std::cout << "This ";
  std::cout << "will ";
  std::cout << "appear ";
  std::cout << "second" << std::endl;
  mutex.unlock();
}
/**
 * @brief Using Semaphores, the threads are locked to display them sequentially.
 *
 * @return int
 */
int main(void)
{
  std::thread threadOne, threadTwo;
  std::shared_ptr<Semaphore> sem(new Semaphore);
  // Launch the threads
  int taskOneDelay = 5;
  threadTwo = std::thread(taskOne, sem, taskOneDelay);
  threadOne = std::thread(taskTwo, sem);
  std::cout << "Launched from the main\n\n";
  // Wait for the threads to finish
  threadTwo.join();
  threadOne.join();
  return 0;
}
