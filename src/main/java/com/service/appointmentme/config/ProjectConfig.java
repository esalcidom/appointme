package com.service.appointmentme.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

/*    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SCryptPasswordEncoder sCryptPasswordEncoder(){
        return new SCryptPasswordEncoder();
    }*/

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.csrf().disable();
        http
            .authorizeRequests()
            .mvcMatchers(HttpMethod.POST, "/appointment")
                .hasAuthority("WRITE")
            .mvcMatchers(HttpMethod.GET, "/appointment")
                .hasAnyAuthority("WRITE","READ")
            .mvcMatchers(HttpMethod.PUT, "/appointment/{id}")
                .hasAuthority("WRITE");
    }

}
