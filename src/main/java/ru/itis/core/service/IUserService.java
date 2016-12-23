package ru.itis.core.service;

import ru.itis.core.entities.User;

import java.util.List;

/**
 * Created by Alex on 23.12.16.
 */
public interface IUserService {
    List<User> getAllChats(long databaseId);
}
