package com.cpprac.systemDesign;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author priyesh.mishra
 */
public class Multithreading {

    public static void main(String[] args) throws InterruptedException {
        Obj ob = new Obj(10);
        List<Thread> list = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Thread(ob::increment));
        }
//        for (int i = 0; i < 10; i++) {
//            list.get(i).run();
//        }
//        for (int i = 0; i < 10; i++) {
//            list.get(i).join();
//        }
        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.SECONDS);
        System.out.println(ob.i);
        ob.increment();
        System.out.println(ob.i);
    }
}
class Obj {
    int i;
    Obj(int in) {
        i = in;
    }
    public void increment() {
        i = i+1;
        System.out.println("Incrementing "+i);
    }
}
