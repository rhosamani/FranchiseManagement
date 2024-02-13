package com.franchise.management;
class ThreadCounter {
    private volatile int count;

    public void increment(){
        count++;
    }

    public void print(){
        System.out.println("Counter Value : "+count);
    }

}
public class ThreadTest {
    public static void main (String [] args){

        ThreadCounter threadCounter = new ThreadCounter();
        Thread t1 = new Thread(()-> {
            System.out.println("Entering Thread 1");
                for(int i=0; i<2000;i++){
                    threadCounter.increment();
                }

            System.out.println("Completed Thread 1");
        });
        t1.start();
        Thread t2 = new Thread(()-> {
            System.out.println("Entering Thread 2");
            for(int i=0; i<2000;i++){
                threadCounter.increment();
            }

            System.out.println("Completed Thread 2");
        });
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        threadCounter.print();
    }

}
