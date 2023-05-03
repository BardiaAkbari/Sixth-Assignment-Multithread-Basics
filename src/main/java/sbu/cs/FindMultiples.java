package sbu.cs;

/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FindMultiples {

    // TODO create the required multithreading class/classes using your preferred method.

    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */

    public int getSum(int n) {
        int sum = 0;
        ThreadHandling.setArr(sum);
        Lock lock = new ReentrantLock();
        ArrayList<Thread> threads = new ArrayList<>();
        for(int i = 0; i < n / 1000 + 1; i++) {
            ThreadHandling threadHandling;
            if (i == n / 1000) {
                threadHandling = new ThreadHandling(i * 1000, i * 1000 + n % 1000, lock);
            }
            else {
                threadHandling = new ThreadHandling(i * 1000, (i + 1) * 1000 - 1, lock);
            }
            Thread thread = new Thread(threadHandling);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return ThreadHandling.getArr();
    }

    public static void main(String[] args) {

    }
}
