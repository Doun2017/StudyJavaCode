package com.example.doun.chapter21concurrency;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Doun on 2017/9/3.
 */
class ThreadMethod {
    private ExecutorService exe;

    public ThreadMethod() {
        exe = Executors.newCachedThreadPool();
    }

    public Future<Integer> runTask(int numbers) {
        return exe.submit(new FibonacciSum(numbers));
    }
    public void shutdown(){
        exe.shutdown();
    }
}

public class Exercise10 {
    public static void main(String[] args) {
        ThreadMethod threadMethod = new ThreadMethod();
        ArrayList<Future<Integer>> results = new ArrayList<>();
        for (int i=0;i<10; i++) {
            results.add(threadMethod.runTask(i+4));
        }
        for (Future<Integer> fs : results)
            try {
                // get() blocks until completion:
                System.out.println("result of TaskWithResult " + fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                threadMethod.shutdown();
            }
    }

}
/*

0thread:  [1, 1, 2, 3]
8thread:  [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144]
5thread:  [1, 1, 2, 3, 5, 8, 13, 21, 34]
4thread:  [1, 1, 2, 3, 5, 8, 13, 21]
6thread:  [1, 1, 2, 3, 5, 8, 13, 21, 34, 55]
3thread:  [1, 1, 2, 3, 5, 8, 13]
7thread:  [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89]
1thread:  [1, 1, 2, 3, 5]
result of TaskWithResult 7
result of TaskWithResult 12
9thread:  [1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233]
2thread:  [1, 1, 2, 3, 5, 8]
result of TaskWithResult 20
result of TaskWithResult 33
result of TaskWithResult 54
result of TaskWithResult 88
result of TaskWithResult 143
result of TaskWithResult 232
result of TaskWithResult 376
result of TaskWithResult 609

* */