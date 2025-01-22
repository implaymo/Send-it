package send.it.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import send.it.DatabaseTables.MessagesTable;

public interface MessagesRepository extends JpaRepository<MessagesTable, Long> {
}
