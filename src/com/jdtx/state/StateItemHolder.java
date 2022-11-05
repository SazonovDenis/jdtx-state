package com.jdtx.state;

@Deprecated
public interface StateItemHolder {

    /**
     * Создать и запустить таймер.
     * Новый или существующий стоящий таймер - запускается.
     * Существующий запущенный таймер - продолжает работу, вызов метода игнорируется.
     *
     * @param timerName имя таймера
     * @return данные нового или уже сущесвующего таймера
     */
    StateItem start(String timerName);

    /**
     * Остановить таймер.
     * Существующий запущенный таймер - остановится.
     * Существующий стоящий таймер - продолжит стоять, вызов метода игнорируется.
     * Таймер не существует - вызов метода игнорируется.
     *
     * @param timerName имя таймера
     * @return данные таймера или null, если таймера нет
     */
    StateItem stop(String timerName);

    /**
     * Удалить таймер
     *
     * @param timerName
     */
    void remove(String timerName);

    /**
     * Таймер по имени
     *
     * @param timerName имя таймера
     * @return данные таймера или null, если таймера нет
     */
    StateItem get(String timerName);

}
