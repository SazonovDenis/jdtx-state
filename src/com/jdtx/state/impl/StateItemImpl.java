package com.jdtx.state.impl;


import com.jdtx.state.*;
import com.jdtx.ut.*;

import java.util.*;

public class StateItemImpl implements StateItem {


    private Map<String, Object> values;
    private boolean started;

    public StateItemImpl() {
        values = new HashMap<>();
        started = false;
    }

    public void setValue(String key, Object value) {
        values.put(key, value);
    }

    public Object getValue(String key) {
        return values.get(key);
    }

    public Map getValues() {
        return values;
    }

    public void incValue(String key, double incValue) {
        values.put(key, UtData.doubleValueOf(values.get(key)) + incValue);
    }

    public void start() {
        // Таймер запущен?
        if (!started) {
            // Таймер не запущен - запускаем
            values.put("__start__", new Date());
            values.put("__stop__", null);
            values.put("__steps__", UtData.longValueOf(values.get("__steps__")) + 1);
            //
            started = true;
        }
    }

    public void stop() {
        long duration = getDuration();
        //
        values.put("__stop__", new Date());
        values.put("__duration__", duration);
        //
        started = false;
    }

    public boolean isStarted() {
        return started;
    }

    public long getDuration() {
        // Накопленное время за предыдущие запуски
        long duration = UtData.longValueOf(values.get("__duration__"));

        // Для работающего таймера прибавим время с последнего запуска
        if (started) {
            Date startDt = getStart();
            duration = duration + new Date().getTime() - startDt.getTime();
        }

        //
        return duration;
    }

    public Date getStart() {
        return UtData.dateTimeValueOf(values.get("__start__"));
    }

    public Date getStop() {
        return UtData.dateTimeValueOf(values.get("__stop__"));
    }


}
