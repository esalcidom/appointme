package com.service.appointmentme.config;

import com.service.appointmentme.user.CustomUserDetails;
import com.service.appointmentme.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private SCryptPasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails customUserDetails = userService.loadUserByUsername(username);

        switch(customUserDetails.getUser().getAlgorithm()){
            case BCRYPT:
                return checkPassword(customUserDetails, password, bCryptPasswordEncoder);
            case SCRYPT:
                return checkPassword(customUserDetails, password, sCryptPasswordEncoder);
            default:
                throw new BadCredentialsException("Bad credentials passed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private Authentication checkPassword(CustomUserDetails customUserDetails, String password, PasswordEncoder encoder){

        if(encoder.matches(password, customUserDetails.getPassword())){
            logger.info("password matched!");
            return new UsernamePasswordAuthenticationToken(
              customUserDetails.getUsername(),
              customUserDetails.getPassword(),
              customUserDetails.getAuthorities()
            );
        } else {
            throw new BadCredentialsException("Username or Password are not correct");
        }
    }
}
