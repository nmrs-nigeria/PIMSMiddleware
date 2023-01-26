package pims.integrator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pims.integrator.entity.User;
import pims.integrator.entity.UserAttempt;


public interface UserAttemptRepository extends JpaRepository<UserAttempt, Integer> {
    UserAttempt findByUser(User user);
}
