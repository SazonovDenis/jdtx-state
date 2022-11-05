package com.jdtx.state;

/**
 * Помогает строить дерево из счетчиков
 */
public interface StateItemStack {

    /**
     * Создать и запустить новый элемент,
     * элемент кладется на вершину стека.
     *
     * @param timerName имя таймера или null
     * @return экземпляр нового таймера
     */
    StateItem push(String timerName);

    /**
     * Остановить таймер.
     * Запущенный таймер - остановится, извлекается со стека.
     *
     * @param timerName имя таймера или null
     * @return таймер
     */
    StateItem pop(String timerName);

    /**
     * Возвращмет таймер по имени или с вершины стека.
     *
     * @param timerName имя таймера или null
     * @return таймер
     */
    StateItem get(String timerName);

    /**
     *
     */
    StateItem add(String timerName);

    /**
     * Удаляет элемент и всех его потомков
     */
    void remove(String timerName);


}
