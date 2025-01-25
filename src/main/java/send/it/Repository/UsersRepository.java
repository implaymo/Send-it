package send.it.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import send.it.DatabaseTables.UsersTable;


public interface UsersRepository extends JpaRepository<UsersTable, Integer> {

}