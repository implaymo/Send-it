package send.it.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import send.it.Dto.UsersDto;


public interface UsersRepository extends JpaRepository<UsersDto, Integer> {
    Optional<UsersDto> findByEmail(String email);
}