package com.parcaune.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.parcaune.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)   //permitts preAuthorisation
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired  //spring instantiate
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                //csrf generation
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                //.and()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/api/v1/student/**").permitAll()  // will protect the url(api for students(Role based authentification))
               // .antMatchers("/api/**").hasRole(STUDENT.name())  // will protect the url(api for students(Role based authentification))

                /*
                .antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(COURSE_WRITE.getPermissions()) // the path hier should have the COURSE_WRITE authority(role) thanks to antmatchers
                .antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(COURSE_WRITE.getPermissions()) // will protect the url(api for students(Role based authentification))
                .antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(COURSE_WRITE.getPermissions())// will protect the url(api for students(Role based authentification))
                .antMatchers(HttpMethod.GET,"/management/api/**").hasAnyRole(ADMINTRAINEE.name(), ADMIN.name())
                 */

                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();  // Basic Authentification
        /*
                .formLogin() // form based authentification
                    .loginPage("/login")
                    .permitAll()
                    .defaultSuccessUrl("/courses", true) //introducing a new login page in App and permitting all urlsdefau
                    .and()
                   // .rememberMe(); // default for 2 weeks
                    .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) //permitts to prolonge the sessionID
                .key("somethingverysecured")  // permitts to ash the credentials in md5 Hash
                .rememberMeParameter("remember-me")
                .and()
                .logout()
                .logoutUrl("/logout")
                //line 63 is used onlybecause the

                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/configurers/LogoutConfigurer.html
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");//at this stage the cookies are indirectly deleted
                    //.passwordParameter("password")
                   // .usernameParameter("username");

         */
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/console/**"); // For H2 DB not to be protected
    }

    @Override
    @Bean // creates an object that bean will manage of type UserDetailsService
    //the method userDetail to retrieve a student from Db
    public UserDetailsService userDetailsServiceBean() throws Exception {
        UserDetails montheuser = User.builder()
                .username("Monthe")
                .password(passwordEncoder.encode("password"))
                //.roles(STUDENT.name()) //spring understands this line as ROLE_STUDENT
                .authorities(STUDENT.getGrantedAuthorities())
                .build();


        UserDetails tianiuser = User.builder()
                .username("Tiani")
                .password(passwordEncoder.encode("Tiani21"))
                //.roles(ADMIN.name(), ADMINTRAINEE.name()) // Role ADMIN
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails Aliceuser = User.builder()
                .username("Alice")
                .password(passwordEncoder.encode("Alice21"))
                //.roles(ADMINTRAINEE.name()) //Role Admintrainee
                .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();


        return new InMemoryUserDetailsManager(montheuser, tianiuser, Aliceuser);
    }

    //configureGlobal configure which class springboot should use for a user to get logged in{mechanism}
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

}
