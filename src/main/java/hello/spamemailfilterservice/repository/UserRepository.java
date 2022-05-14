package hello.spamemailfilterservice.repository;

import hello.spamemailfilterservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
