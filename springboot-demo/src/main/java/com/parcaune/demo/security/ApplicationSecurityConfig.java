package com.parcaune.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.parcaune.demo.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private  final PasswordEncoder passwordEncoder;

    @Autowired  //spring instantiate
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())  // will protect the url(api for students(Role based authentification))
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean // creates an object that bean will manage of type UserDetailsService
    //the method userDetail to retrieve a student from Db
    public UserDetailsService userDetailsServiceBean() throws Exception {
       UserDetails montheuser = User.builder()
                .username("Monthe")
                .password(passwordEncoder.encode("password"))
                .roles(STUDENT.name()) //spring understands this line as ROLE_STUDENT
               .build();


        UserDetails tianiuser = User.builder()
                .username("Tiani")
                .password(passwordEncoder.encode("Tiani21"))
                .roles(ApplicationUserRole.ADMIN.name()) // Role ADMIN
                .build();

        UserDetails Aliceuser = User.builder()
                .username("Alice")
                .password(passwordEncoder.encode("Alice21"))
                .roles(ApplicationUserRole.ADMINTRAINEE.name()) //Role Admintrainee
                .build();


        return new InMemoryUserDetailsManager(montheuser,tianiuser,Aliceuser);




    }




}
