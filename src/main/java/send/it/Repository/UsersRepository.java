package send.it.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import send.it.Entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}