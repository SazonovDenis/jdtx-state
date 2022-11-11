package com.jdtx.state;

import com.jdtx.state.impl.*;
import com.jdtx.tree.*;
import junit.framework.*;
import org.apache.commons.io.*;
import org.json.simple.*;

import java.io.*;
import java.util.*;

public class StateItemStack_Test extends TestCase {

    int n = 0;

    public void test_startStop() throws Exception {
        System.out.println("StateItemStack");
        StateItemStack stateMain;

        // Повторные задачи
        System.out.println();
        System.out.println("Повторные задачи");
        //
        stateMain = new StateItemStackImpl();
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.start();
        stateMain.get().setValue("info", "task #1");
        pauseAndPrint(stateMain.getAll());
        stateMain.stop();
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.start();
        stateMain.get().setValue("info", "task #2");
        pauseAndPrint(stateMain.getAll());
        stateMain.stop();
        pauseAndPrint(stateMain.getAll());


        // Вложенные задачи
        System.out.println();
        System.out.println("Вложенные задачи");
        //
        stateMain = new StateItemStackImpl();
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.start();
        stateMain.get().setValue("info", "task #3");
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.start();
        stateMain.get().setValue("info", "task #4");
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.stop();
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.stop();
        pauseAndPrint(stateMain.getAll());
    }

    public void test_startStopNamed() throws Exception {
        System.out.println("StateItemStackNamed");
        StateItemStackNamed stateMain;

        // Повторные задачи
        System.out.println();
        System.out.println("Повторные задачи");
        //
        stateMain = new StateItemStackNamedImpl();
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.start("task");
        stateMain.get().setValue("info", "task #1");
        pauseAndPrint(stateMain.getAll());
        stateMain.stop();
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.start("task");
        stateMain.get().setValue("info", "task #2");
        pauseAndPrint(stateMain.getAll());
        stateMain.stop();
        pauseAndPrint(stateMain.getAll());


        // Вложенные задачи
        System.out.println();
        System.out.println("Вложенные задачи");
        //
        stateMain = new StateItemStackNamedImpl();
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.start("task");
        stateMain.get().setValue("info", "task #3");
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.start("task");
        stateMain.get().setValue("info", "task #4");
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.stop();
        pauseAndPrint(stateMain.getAll());
        //
        stateMain.stop();
        pauseAndPrint(stateMain.getAll());
    }

    public void test1() throws Exception {
        System.out.println("TreeItemContainer");

        // Простейший одиночный counter - подчиненные процессы
        StateItemStack stateMain = new StateItemStackImpl();
        pauseAndPrint(stateMain.getAll());

        stateMain.start();
        stateMain.get().setValue("info", "task #1");
        pauseAndPrint(stateMain.getAll());
        stateMain.get().incValue("count", 1);
        pauseAndPrint(stateMain.getAll());
        stateMain.get().incValue("count", 1);
        pauseAndPrint(stateMain.getAll());

        // Запуск дочернего процесса - спуск вглубь на 2-й уровень
        stateMain.start();
        stateMain.get().setValue("info", "task #2");
        pauseAndPrint(stateMain.getAll());
        stateMain.get().incValue("count", 10);
        pauseAndPrint(stateMain.getAll());
        stateMain.get().incValue("count", 10);
        pauseAndPrint(stateMain.getAll());

        // Остановка дочернего процесса - подъем со 2-го на 1-й уровень
        stateMain.stop();
        pauseAndPrint(stateMain.getAll());

        pauseAndPrint(stateMain.getAll());

        stateMain.stop();
        pauseAndPrint(stateMain.getAll());

        pauseAndPrint(stateMain.getAll());
    }

    public void test_wait() throws Exception {
        System.out.println("TreeItemContainer");

        // Простейший одиночный counter - подчиненные процессы
        StateItemStack stateMain = new StateItemStackImpl();
        pauseAndPrintFile(stateMain.getAll(), 1);

        stateMain.start();
        stateMain.get().setValue("info", "task #1");
        pauseAndPrintFile(stateMain.getAll(), 1);
        for (int i = 0; i < 10; i++) {
            stateMain.get().incValue("count", 1);
            pauseAndPrintFile(stateMain.getAll(), 1);
        }

        // Запуск дочернего процесса - спуск вглубь на 2-й уровень
        stateMain.start();
        stateMain.get().setValue("info", "task #2");
        stateMain.get().incValue("total", 200);
        pauseAndPrintFile(stateMain.getAll(), 1);
        for (int i = 0; i < 20; i++) {
            stateMain.get().incValue("count", 10);
            pauseAndPrintFile(stateMain.getAll(), 1);
        }

        // Остановка дочернего процесса - подъем со 2-го на 1-й уровень
        stateMain.stop();
        pauseAndPrintFile(stateMain.getAll(), 1);

        pauseAndPrintFile(stateMain.getAll(), 1);

        stateMain.stop();
        pauseAndPrintFile(stateMain.getAll(), 1);
    }

    private void pauseAndPrint(ITreeNode<StateItem> root) throws Exception {
        Thread.sleep(200);

        Map map = UtStateCnv.stateItemToMap(root);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject.toJSONString());
    }

    private void pauseAndPrintFile(ITreeNode<StateItem> root, int n) throws Exception {
        Thread.sleep(200);

        Map map = UtStateCnv.stateItemToMap(root);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject.toJSONString());

        FileUtils.writeStringToFile(new File("temp/" + n + ".json"), jsonObject.toJSONString());
    }

}
