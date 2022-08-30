package com.example.shop;

//import com.example.shop.service.SecurityService;
//import com.example.shop.service.SecurityServiceImpl;
//import com.example.shop.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;
import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/products/**", "/orders/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/", "/css/**", "/js/**").permitAll()
            .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
            .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }

    @Override
    public void configure(WebSecurity web) {
        web.debug(false);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("password")
                .roles("ADMIN")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user2")
                .password("password")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("SELECT username, password, active FROM lab3_ko_users WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, permissions FROM lab3_ko_users WHERE username = ?");
    }
}
