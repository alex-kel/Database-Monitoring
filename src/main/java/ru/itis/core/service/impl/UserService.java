package ru.itis.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.itis.core.constants.CommonConstants;
import ru.itis.core.entities.User;
import ru.itis.core.service.IUserService;

import java.util.List;

/**
 * Created by Alex on 23.12.16.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private ConfiguredDatabasesService configuredDatabasesService;

    @Override
    public List<User> getAllChats(long databaseId) {
        JdbcTemplate jdbcTemplate = configuredDatabasesService.getJdbcTemplateForDb(databaseId);
        return jdbcTemplate.query(CommonConstants.GET_ALL_USERS, new BeanPropertyRowMapper(User.class));
    }
}
