package pims.integrator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pims.integrator.entity.Role;
import pims.integrator.entity.User;
import pims.integrator.exception.APIException;
import pims.integrator.repository.UserRepository;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws APIException {
       // User user = userRepository.findByUsernameOrEmail(userNameOrEmail,userNameOrEmail).orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: "+userNameOrEmail));
        User user = userRepository.findByUsernameOrEmail(userNameOrEmail,userNameOrEmail).orElseThrow(() -> new APIException(HttpStatus.BAD_REQUEST,"User not found with username or email: "+userNameOrEmail));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(),
                                                                      user.isAccountNonLocked(),mapRolesToAuthority(user.getRoles()));
    }

    private Collection <? extends GrantedAuthority> mapRolesToAuthority(Set<Role> roles){
        return  roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
