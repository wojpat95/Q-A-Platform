package QAPlatform.service;

import QAPlatform.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}