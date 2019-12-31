package com.bistu.thread.cores.cas;

import java.util.concurrent.atomic.AtomicReference;

public class UseAtomicReference2 {

    private static AtomicReference<Person> aRperson;

    public static void main(String[] args) throws  InterruptedException{
        Person person = new Person("Tom", 18);
        aRperson = new AtomicReference<>(person);
        System.out.println("Atomic Person is " + aRperson.get().toString());

        Thread t1 = new Thread(new Task1());
        Thread t2 = new Thread(new Task2());

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        Thread.sleep(500);
        System.out.println("Now Atomic Person is " + aRperson.get().toString());
    }

    static class Task1 implements Runnable{
        public void run(){
            System.out.println("ret = " +
                    // C A S 原子性操作
                    aRperson.compareAndSet(
                            aRperson.get(),
                            new Person("Tom", aRperson.get().getAge() + 1)
                    ));
            System.out.println("Thread1 Atomic Referneces " +
                    aRperson.get().toString());
        }
    }

    static class Task2 implements Runnable{
        @Override
        public void run() {
            System.out.println("ret = " +
                    aRperson.compareAndSet(
                            aRperson.get(),
                            new Person("Tom", aRperson.get().getAge() + 2)
                    ));
            System.out.println("Thread2 Atomic Person " +
                    aRperson.get().toString());
        }
    }
}
