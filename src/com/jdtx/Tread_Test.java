package com.jdtx;

import com.jdtx.state.*;
import com.jdtx.tree.*;
import org.apache.commons.io.*;
import org.json.simple.*;

import java.io.*;
import java.util.*;

public class Tread_Test {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello");

        StateItemContainer state = new StateItemContainer();

        Thread thread2 = new Thread(new MyThread(state, 50, "1 xxx"));
        Thread thread1 = new Thread(new MyThread(state, 200, "2 xxx-yyy"));
        Thread thread3 = new Thread(new MyThread(state, 900, "3 xxx-yyy-zzz"));
        Thread threadControl = new Thread(new MyThreadControl(state));

        state.start();
        state.current().setValue("name", "main");

        threadControl.start();
        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class MyThreadControl implements Runnable {

        StateItemContainer state;

        public MyThreadControl(StateItemContainer state) {
            this.state = state;
        }

        public void run() {
            while (true) {
                try {
                    ITreeNode<StateItem> root = state.getRoot();

                    Map map = UtStateCnv.stateItemToMap(root);
                    JSONObject jsonObject = new JSONObject(map);
                    //System.out.println(jsonObject.toJSONString());
                    FileUtils.writeStringToFile(new File("temp/state.json"), jsonObject.toJSONString());

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

    static class MyThread extends Thread {

        StateItemContainer state;
        int sleep;
        String name;

        public MyThread(StateItemContainer state, int sleep, String name) {
            super();
            this.state = state;
            this.sleep = sleep;
            this.name = name;
        }

        public void run() {
            Thread current = Thread.currentThread();


            while (true) {
                state.start();
                state.current().setValue("name", name);
                state.current().setValue("count", 0);

                for (int i = 0; i < 1000; i++) {
                    System.out.println("Date: " + new Date() + ", thread: " + current + ", name: " + name);
                    state.current().incValue("count", 1);
                    doSleep();
                }

                state.stop();
                doSleep();
                doSleep();
                doSleep();
                doSleep();
            }
        }

        private void doSleep() {
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

}
