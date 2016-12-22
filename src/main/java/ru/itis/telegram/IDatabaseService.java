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
     * @param database база данных для запроса
     * @return список сохраненных запросов
     * @throws DoTaskException ошибка при получении запросов
     */
    List<Query> getQueries(Long database) throws DoTaskException;

    /**
     * Запускает SQL запрос
     *
     * @param query SQL запрос для запуска
     * @param database база данных для запроса
     * @return результат выполнения SQL запроса
     * @throws DoTaskException ошибка при выполнении запроса
     */
    String runCustomQuery(String query, Long database) throws DoTaskException;

    /**
     * Запускает сохраненный SQL запрос
     *
     * @param id идентификатор SQL запроса
     * @param database база данных для запроса
     * @return результат выполнения SQL запроса
     * @throws DoTaskException ошибка при выполнении запроса
     */
    String runStoredQuery(Long id, Long database) throws DoTaskException;

    /**
     * Сохраняет новый SQL запрос
     *
     * @param query SQL запрос
     * @param database база данных для запроса
     * @throws DoTaskException ошибка при сохранении запроса
     */
    void storeNewQuery(Query query, Long database) throws DoTaskException;

    /**
     * Удаляет SQL запрос
     *
     * @param id идентификатор SQL запроса
     * @param database база данных для запроса
     * @throws DoTaskException ошибка при удалении запроса
     */
    void deleteStoredQuery(Long id, Long database) throws DoTaskException;

    /**
     * Возвращает сохраненный SQL запрос
     *
     * @param id идентификатор SQL запроса
     * @param database база данных для запроса
     * @return SQL запрос
     * @throws DoTaskException ошибка при получении SQL запроса
     */
    Query getQuery(Long id, Long database) throws DoTaskException;

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
