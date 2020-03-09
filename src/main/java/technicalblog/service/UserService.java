package technicalblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import technicalblog.model.Users;
import technicalblog.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users login(Users users) {
        Users existingUser = userRepository.checkUser(users.getUsername(), users.getPassword());
        if (existingUser == null) {
            return null;
        }
        return existingUser;
    }

    public void registerUser(Users newUser) {
        userRepository.registerUser(newUser);
    }
}
