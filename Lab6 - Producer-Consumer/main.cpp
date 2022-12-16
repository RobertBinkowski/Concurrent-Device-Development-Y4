#include <cstdlib>
#include <iostream>
#include <mutex>
#include <thread>
#include <chrono>
using namespace std;

std::mutex mtx;
bool ready = false;
int printData = 0;
int sleepTime = 1;
int noOfRepeats = 20;
char letters[] = "abcdefghijklmnopqrstuvwxyz";

int printProduce();
void printReceive(char data);

/**
 * @brief Consumer Data
 *
 */
void cons()
{
  for (int i = 0; i < noOfRepeats; i++)
  {
    while (!ready)
    {
      std::this_thread::sleep_for(std::chrono::seconds(sleepTime));
    }
    std::unique_lock<std::mutex> ul(mtx);
    printReceive(printData);
    ready = false;
  }
}

/**
 * @brief Producer produces data by calling the randomiser
 *
 */
void prod()
{
  for (int i = 0; i < noOfRepeats; i++)
  {
    std::unique_lock<std::mutex> ul(mtx);

    printData = printProduce();
    ready = true;
    ul.unlock();
    while (ready)
    {
      std::this_thread::sleep_for(std::chrono::seconds(sleepTime));
    }
  }
}

int main()
{
  std::thread t1(cons);
  std::thread t2(prod);
  t1.join();
  t2.join();
  return 0;
}

/**
 * @brief Create & Print random Data Entry
 *
 * @return int
 */
int printProduce()
{
  char randomLetter = letters[rand() % 26];
  std::cout << "Data Produced: " << randomLetter << "\n";
  return randomLetter;
}

/**
 * @brief Print Specific Data
 *
 * @param data
 */
void printReceive(char data)
{
  std::cout << "Data Received: " << data << "\n";
}