package ru.itis.telegram;

import ru.itis.core.entities.Query;
import ru.itis.telegram.exception.DoTaskException;

import java.util.List;

/**
 * Created by Aydar Farrakhov on 10.11.16.
 */
public interface IDatabaseService {

    /**
     * Возвращает сохраненные пользователями запросы
     *
     * @return список сохраненных запросов
     * @throws DoTaskException ошибка при получении запросов
     */
    List<Query> getQueries() throws DoTaskException;

    /**
     * Запускает SQL запрос
     *
     * @param query SQL запрос для запуска
     * @return результат выполнения SQL запроса
     * @throws DoTaskException ошибка при выполнении запроса
     */
    String runCustomQuery(String query) throws DoTaskException;

    /**
     * Запускает сохраненный SQL запрос
     *
     * @param id идентификатор SQL запроса
     * @return результат выполнения SQL запроса
     * @throws DoTaskException ошибка при выполнении запроса
     */
    String runStoredQuery(Long id) throws DoTaskException;

    /**
     * Сохраняет новый SQL запрос
     *
     * @param text SQL запрос
     * @throws DoTaskException ошибка при сохранении запроса
     */
    void storeNewQuery(String text) throws DoTaskException;

    /**
     * Удаляет SQL запрос
     *
     * @param id идентификатор SQL запроса
     * @throws DoTaskException ошибка при удалении запроса
     */
    void deleteStoredQuery(Long id) throws DoTaskException;

    /**
     * Возвращает сохраненный SQL запрос
     *
     * @param id идентификатор SQL запроса
     * @return SQL запрос
     * @throws DoTaskException ошибка при получении SQL запроса
     */
    Query getQuery(Long id) throws DoTaskException;

    /**
     * Добавляет нового пользователя, если он еще не был добавлен
     *
     * @param id идентификатор чата пользователя
     * @param username username нового пользователя
     * @param firstName Имя нового пользователя
     * @param lastName Фамилия нового пользователя
     * @throws DoTaskException ошибка при добавлении пользователя
     */
    void addIfNotExist(Long id, String username, String firstName, String lastName) throws DoTaskException;
}
