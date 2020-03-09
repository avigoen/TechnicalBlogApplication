package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Users;
import technicalblog.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(Users users) {
        return users.getUsername().equals("validuser");
    }

    public void registerUser(Users newUser) {
        userRepository.registerUser(newUser);
    }
}
