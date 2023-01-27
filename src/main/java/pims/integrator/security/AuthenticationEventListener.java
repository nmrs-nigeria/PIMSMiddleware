package pims.integrator.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pims.integrator.entity.User;
import pims.integrator.entity.UserAttempt;
import pims.integrator.exception.APIException;
import pims.integrator.repository.UserAttemptRepository;
import pims.integrator.repository.UserRepository;


import java.util.Date;

@Component()
public class AuthenticationEventListener
        implements AuthenticationEventPublisher
{



    //
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserAttemptRepository userAttemptRepository;

    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000;
    static final Logger logger = LoggerFactory.getLogger(AuthenticationEventListener.class);

    @Override
    public void publishAuthenticationSuccess(Authentication authentication) {
        logger.info("User has been logged in Successfully :" +authentication.getName());
        //userAttemptsService.resetFailAttempts(authentication.getName());
    }


    @Override
    public void publishAuthenticationFailure(AuthenticationException exception, Authentication authentication) {
        logger.info("User Login failed :" +authentication.getName());
        UserAttempt userAttempt = null;
        User user = userRepository.findByUsername(authentication.getName()).get();//orElseThrow(() -> new ResourceNotFoundException("User not found with email: ",authentication.getName()));
        if(user !=null)
            userAttempt = userAttemptRepository.findByUser(user);

        if (user != null) {
            if (user.isEnabled() && user.isAccountNonLocked()) {
                if(userAttempt != null){
                    if (userAttempt.getFailedAttempt() < MAX_ATTEMPTS - 1) {
                        //userService.increaseFailedAttempts(user);
                        userAttempt.setFailedAttempt(userAttempt.getFailedAttempt()+1);
                        userAttemptRepository.save(userAttempt);
                    } else {
                       // userService.lock(user);
                        user.setAccountNonLocked(false);
                        userRepository.save(user);
                        userAttempt.setFailedAttempt(userAttempt.getFailedAttempt()+1);
                        userAttemptRepository.save(userAttempt);
                        exception = new LockedException("Your account has been locked due to 5 failed attempts."
                                + " It will be unlocked after 24 hours.");
                    }
                }
                else {
                    UserAttempt userAttemptObject = new UserAttempt();
                    userAttemptObject.setFailedAttempt(1);
                    userAttemptObject.setLockTime(new Date());
                    userAttemptObject.setUser(user);
                    userAttemptRepository.save(userAttemptObject);
                }
            } else if (!user.isAccountNonLocked()) {
                if (unlockWhenTimeExpired(userAttempt, user)) {
                    exception = new LockedException("Your account has been unlocked. Please try to login again.");
                }
                else
                    throw new APIException(HttpStatus.LOCKED,"Your account has been locked due to 3 failed attempts."
                            + " It will be unlocked after 24 hours.");
            }

        }


    }

        public boolean unlockWhenTimeExpired(UserAttempt userAttempt, User user) {
        long lockTimeInMillis = userAttempt.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            userAttempt.setLockTime(null);
            userAttempt.setFailedAttempt(0);

            userRepository.save(user);
            userAttemptRepository.save(userAttempt);
            return true;
        }

        return false;
    }
}