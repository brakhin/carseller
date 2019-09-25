package ru.bgbrakhi.carseller.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bgbrakhi.carseller.models.User;

public interface IUserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
}
