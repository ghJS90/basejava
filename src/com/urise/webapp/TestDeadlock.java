package com.urise.webapp;

public class TestDeadlock {
    public static final Object o1 = new Object();
    public static final Object o2 = new Object();

    public static void testMethod(Object obj1, Object obj2) {
        synchronized (obj1) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("Не увидим это сообщение");
            }
        }
    }

    public static void main(String[] a) {

        Thread t1 = new Thread(() -> testMethod(o1, o2));
        Thread t2 = new Thread(() -> testMethod(o2, o1));

        t1.start();
        t2.start();
    }
}

