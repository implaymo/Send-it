package send.it.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import send.it.Entity.Users;
import send.it.Repository.UsersRepository;

@Service
public class UsersService {

    static final Logger logger = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersRepository usersRepository;

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public Users getUserById(int id) {
        return usersRepository.findById(id).orElse(null);
    }
}