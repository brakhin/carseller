package ru.bgbrakhi.carseller.service;

import ru.bgbrakhi.carseller.models.User;

public interface IUserService {
    User findByLogin(String login);
}
