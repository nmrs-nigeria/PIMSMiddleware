package pims.integrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pims.integrator.entity.VerificationToken;


public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(String token);
}
