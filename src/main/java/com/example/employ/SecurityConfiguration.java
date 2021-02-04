package com.example.employ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
   UserDetailsService userDetailsService;

    // For authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(userDetailsService);
    }

    //Setup for authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       // super.configure(http);
        http.authorizeRequests()
                //.antMatchers("/user/**").hasAnyRole("USER","ADMIN")
               //.antMatchers("/admin/**").hasRole("ADMIN")
              //  .antMatchers("/user/**").hasAnyRole("ADMIN","USER")
               .antMatchers("/user/**").permitAll()
                .and().formLogin();
    }

    @Bean
   public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
   }
}
