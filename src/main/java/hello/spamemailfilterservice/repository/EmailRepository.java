package hello.spamemailfilterservice.repository;

import hello.spamemailfilterservice.entity.Email;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {

    Page<Email> findByReceiver(Pageable pageable, String receiver);
}
