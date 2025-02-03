package send.it.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import send.it.Entity.Users;


public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);
}