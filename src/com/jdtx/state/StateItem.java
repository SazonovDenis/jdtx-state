package com.jdtx.state;

import java.util.*;

public interface StateItem {

    void setValue(String key, Object value);

    Object getValue(String key);

    Map<String, Object> getValues();

    void incValue(String key, double incValue);

    /**
     * Запуск таймера.
     * Отмечается время начала работы.
     */
    void start();

    /**
     * Запуск таймера.
     * Отмечается время остановки работы.
     */
    void stop();

    /**
     * @return True, если запущен
     */
    boolean isStarted();

    /**
     * Время работы таймера.
     *
     * @return время работы, миллисекунд.
     */
    long getDuration();

    Date getStart();

    Date getStop();

}
