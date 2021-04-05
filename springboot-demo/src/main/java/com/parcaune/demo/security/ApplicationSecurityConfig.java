package com.parcaune.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.parcaune.demo.security.ApplicationUserPermission.*;
import static com.parcaune.demo.security.ApplicationUserRole.*;
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
                .csrf().disable()// TODO
                .authorizeRequests()
                /*
                .antMatchers("/","index","/css/*","/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())  // will protect the url(api for students(Role based authentification))
                .antMatchers("/management/**").hasRole(STUDENT.name())  // will protect the url(api for students(Role based authentification))
                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermissions()) // the path hier should have the COURSE_WRITE authority(role) thanks to antmatchers
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermissions()) // will protect the url(api for students(Role based authentification))
                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermissions())// will protect the url(api for students(Role based authentification))
                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMINTRAINEE.name(), ADMIN.name())

                 */
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
               .authorities(STUDENT.getGrantedAuthorities())
               .build();


        UserDetails tianiuser = User.builder()
                .username("Tiani")
                .password(passwordEncoder.encode("Tiani21"))
                .roles(ADMIN.name()) // Role ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails Aliceuser = User.builder()
                .username("Alice")
                .password(passwordEncoder.encode("Alice21"))
                .roles(ADMINTRAINEE.name()) //Role Admintrainee
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();


        return new InMemoryUserDetailsManager(montheuser,tianiuser,Aliceuser);




    }




}
