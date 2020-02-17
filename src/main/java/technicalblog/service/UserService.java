package technicalblog.service;

import org.springframework.stereotype.Service;
import technicalblog.model.Users;

@Service
public class UserService {
    public boolean login(Users users){
        if(users.getUsername().equals("validuser")){
            return true;
        }
        else {
            return false;
        }
    }
}
