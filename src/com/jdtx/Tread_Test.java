package com.jdtx;

import com.jdtx.state.*;
import com.jdtx.state.impl.*;
import com.jdtx.tree.*;
import org.apache.commons.io.*;
import org.json.simple.*;

import java.io.*;
import java.util.*;

public class Tread_Test {

    public static void main(String[] args) {
        System.out.println("Hello");

        Thread thread2 = new Thread(new MyThreadWork("1 xxx", 50, 100));
        Thread thread1 = new Thread(new MyThreadWork("2 xxx-yyy", 200, 20));
        Thread thread3 = new Thread(new MyThreadWork("3 xxx-yyy-zzz", 900, 30));
        Thread thread4 = new Thread(new MyThreadWork(null, 900, 40));
        Thread threadControl = new Thread(new MyThreadControl());

        threadControl.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    static class MyThreadControl implements Runnable {

        StateItemStack state;

        public MyThreadControl() {
            state = StateItemStackThread.stateItemStack;
        }

        public void run() {
            while (true) {
                try {
                    ITreeNode<StateItem> root = state.getAll();

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

    static class MyThreadWork extends Thread {

        String name;
        int sleepDuration;
        int stepsCount;

        public MyThreadWork(String name, int sleepDuration, int stepsCount) {
            super();
            this.name = name;
            this.sleepDuration = sleepDuration;
            this.stepsCount = stepsCount;
        }

        public void run() {
            StateItemStackNamed state = StateItemStackThread.stateItemStack;

            while (true) {
                // Начинаем цикл
                System.out.println("Name: " + name + ", start: " + new Date());
                state.start(name);
                state.get().setValue("info", name);
                state.get().incValue("total", 1);
                state.get().setValue("step", 0);
                state.get().setValue("stepsCount", stepsCount);

                // Выполняем цикл
                for (int i = 0; i < stepsCount; i++) {
                    System.out.println("Name: " + name + ", step: " + i + "/" + stepsCount);
                    state.get().incValue("step", 1);
                    doSleep();
                }

                // Ждем перед следующим циклом
                System.out.println("Name: " + name + ", steps done: " + new Date());
                state.stop();
                doSleep();
                doSleep();
                doSleep();
                doSleep();
            }
        }

        private void doSleep() {
            try {
                Thread.sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }

}
