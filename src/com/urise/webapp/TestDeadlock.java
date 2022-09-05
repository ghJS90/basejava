package com.urise.webapp;

public class TestDeadlock {
    public static final Object o1 = new Object();
    public static final Object o2 = new Object();

    public static void main(String[] a) {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();
        t1.start();
        t2.start();
    }

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (o1) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("Не увидим это сообщение");
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (o2) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("Не увидим это сообщение");
                }
            }
        }
    }
}
