package com.jdtx.state;

import com.jdtx.tree.*;
import junit.framework.*;
import org.apache.commons.io.*;
import org.json.simple.*;

import java.io.*;
import java.util.*;

public class StateItemContainer_Test extends TestCase {

    int n = 0;

    public void test1() throws Exception {
        System.out.println("TreeItemContainer");

        // Простейший одиночный counter - подчиненные процессы
        StateItemContainer stateMain = new StateItemContainer();
        pauseAndPrint(stateMain.getRoot());

        stateMain.start();
        stateMain.current().setValue("name", "task #1");
        pauseAndPrint(stateMain.getRoot());
        stateMain.current().incValue("count", 1);
        pauseAndPrint(stateMain.getRoot());
        stateMain.current().incValue("count", 1);
        pauseAndPrint(stateMain.getRoot());

        // Запуск дочернего процесса - спуск вглубь на 2-й уровень
        stateMain.start();
        stateMain.current().setValue("name", "task #2");
        pauseAndPrint(stateMain.getRoot());
        stateMain.current().incValue("count", 10);
        pauseAndPrint(stateMain.getRoot());
        stateMain.current().incValue("count", 10);
        pauseAndPrint(stateMain.getRoot());

        // Остановка дочернего процесса - подъем со 2-го на 1-й уровень
        stateMain.stop();
        pauseAndPrint(stateMain.getRoot());

        pauseAndPrint(stateMain.getRoot());

        stateMain.stop();
        pauseAndPrint(stateMain.getRoot());

        pauseAndPrint(stateMain.getRoot());
    }

    public void test_wait() throws Exception {
        System.out.println("TreeItemContainer");
        
        // Простейший одиночный counter - подчиненные процессы
        StateItemContainer stateMain = new StateItemContainer();
        pauseAndPrintFile(stateMain.getRoot(), 1);

        stateMain.start();
        stateMain.current().setValue("name", "task #1");
        pauseAndPrintFile(stateMain.getRoot(), 1);
        for (int i = 0; i < 10; i++) {
            stateMain.current().incValue("count", 1);
            pauseAndPrintFile(stateMain.getRoot(), 1);
        }

        // Запуск дочернего процесса - спуск вглубь на 2-й уровень
        stateMain.start();
        stateMain.current().setValue("name", "task #2");
        stateMain.current().incValue("total", 200);
        pauseAndPrintFile(stateMain.getRoot(), 1);
        for (int i = 0; i < 20; i++) {
            stateMain.current().incValue("count", 10);
            pauseAndPrintFile(stateMain.getRoot(), 1);
        }

        // Остановка дочернего процесса - подъем со 2-го на 1-й уровень
        stateMain.stop();
        pauseAndPrintFile(stateMain.getRoot(), 1);

        pauseAndPrintFile(stateMain.getRoot(), 1);

        stateMain.stop();
        pauseAndPrintFile(stateMain.getRoot(), 1);
    }

    private void pauseAndPrint(ITreeNode<StateItem> root) throws Exception {
        Thread.sleep(200);

        Map map = UtStateCnv.stateItemToMap(root);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject.toJSONString());
        //n = n + 1;
        //FileUtils.writeStringToFile(new File("temp/" + n + ".json"), jsonObject.toJSONString());
    }

    private void pauseAndPrintFile(ITreeNode<StateItem> root, int n) throws Exception {
        Thread.sleep(200);

        Map map = UtStateCnv.stateItemToMap(root);
        JSONObject jsonObject = new JSONObject(map);
        System.out.println(jsonObject.toJSONString());

        FileUtils.writeStringToFile(new File("temp/" + n + ".json"), jsonObject.toJSONString());
    }

}
