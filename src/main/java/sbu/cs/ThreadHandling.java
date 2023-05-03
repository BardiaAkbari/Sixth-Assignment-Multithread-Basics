package sbu.cs;

import java.util.concurrent.locks.Lock;

public class ThreadHandling implements Runnable {
    private final int startPoint;
    private final int endPoint;
    private static int arr;
    private final Lock lock;

    public ThreadHandling(int startPoint, int endPoint, Lock lock) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.lock = lock;
    }
    @Override
    public void run() {
        for(int  i = startPoint; i <= endPoint; i++) {
            if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
                this.lock.lock();
                arr = arr + i;
                this.lock.unlock();
            }
        }
    }
    // Setter
    public static void setArr(int arr) {
        ThreadHandling.arr = arr;
    }
    // Getter
    public static int getArr() {
        return ThreadHandling.arr;
    }
}
