package ru.bgbrakhi.carseller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.bgbrakhi.carseller.models.User;
import ru.bgbrakhi.carseller.repository.IUserRepository;

@Service()
@Repository
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
