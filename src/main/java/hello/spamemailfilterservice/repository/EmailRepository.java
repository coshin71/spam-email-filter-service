package hello.spamemailfilterservice.repository;

import hello.spamemailfilterservice.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
