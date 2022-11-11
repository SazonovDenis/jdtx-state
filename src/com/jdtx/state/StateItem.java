package com.jdtx.state;

import java.util.*;

/**
 * Таймер + атрибуты
 */
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

    /**
     * @return время последнего начала работы
     */
    Date getStart();

    /**
     * @return время остановки работы
     */
    Date getStop();

}
