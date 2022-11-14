package com.jdtx;

import com.jdtx.state.*;
import com.jdtx.state.impl.*;
import com.jdtx.tree.*;
import org.apache.commons.io.*;
import org.json.simple.*;

import java.io.*;
import java.util.*;

public class Tread_Test {

    public static StateItemStackThread stateItemStackThread = new StateItemStackThread();

    public static void main(String[] args) {
        Thread thread2 = new Thread(new MyThreadWork("1 xxx", 50, 100));
        Thread thread1 = new Thread(new MyThreadWork("2 xxx-yyy", 200, 20));
        Thread thread3 = new Thread(new MyThreadWork("3 xxx-yyy-zzz", 900, 30));
        Thread thread4 = new Thread(new MyThreadWork(null, 900, 40));
        Thread threadControl1 = new Thread(new MyThreadControl("state1"));
        Thread threadControl2 = new Thread(new MyThreadControl("state2"));

        threadControl1.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        threadControl2.start();
    }

    static class MyThreadControl implements Runnable {

        private String name;
        private int sleepDuration = 1;

        public MyThreadControl(String name) {
            this.name = name;
        }

        public void run() {
            File outFile = new File("temp/" + name + ".json");
            System.out.println("Started: " + this.getClass().getName() + ", file: " + outFile.getAbsolutePath());

            //
            while (true) {
                try {
                    StateItemStack state = stateItemStackThread;
                    ITreeNode<StateItem> root = state.getAll();

                    Map map = UtStateCnv.stateItemToMap(root);
                    JSONObject jsonObject = new JSONObject(map);
                    FileUtils.writeStringToFile(outFile, jsonObject.toJSONString());

                    //

                    //
                    try {
                        Thread.sleep(sleepDuration);
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
            System.out.println("Started: " + this.getClass().getName() + ", name: " + name);

            //
            StateItemStackNamed state = stateItemStackThread;

            //
            while (true) {
                // Начинаем цикл
                //System.out.println("Name: " + name + ", start: " + new Date());
                state.start(name);
                state.get().setValue("info", name);
                state.get().incValue("total", 1);
                state.get().setValue("step", 0);
                state.get().setValue("stepsCount", stepsCount);

                // Выполняем цикл
                for (int i = 0; i < stepsCount; i++) {
                    //System.out.println("Name: " + name + ", step: " + i + "/" + stepsCount);
                    state.get().incValue("step", 1);
                    doSleep();
                }

                // Ждем перед следующим циклом
                //System.out.println("Name: " + name + ", steps done: " + new Date());
                state.stop();
                doSleep();
                doSleep();
                doSleep();
                doSleep();
            }
        }

        private void doSleep() {
/*
            try {
                Thread.sleep(sleepDuration);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
*/
        }
    }

}
