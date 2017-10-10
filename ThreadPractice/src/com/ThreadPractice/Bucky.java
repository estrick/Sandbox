package com.ThreadPractice;

public class Bucky {

    public static void main(String[] args) {

        Thread t1 = new Thread(new Apple("One"));
        Thread t2 = new Thread(new Apple("Two"));
        Thread t3 = new Thread(new Apple("Three"));
        t1.start();
        t2.start();
        t3.start();

    }

}
