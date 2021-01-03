package com.pk.hotelmanagement.security;


import com.pk.hotelmanagement.users.roles.Role;
import com.pk.hotelmanagement.users.vo.Email;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/hotel").permitAll()  //for testing purposes
                .antMatchers("/rooms").permitAll()   //for testing purposes
                .antMatchers("/storages").permitAll()//for testing purposes
                .antMatchers("/reservations", "/reservations/*").hasAuthority(Role.USER.name())
                .antMatchers("/admin", "/admin/**").hasAuthority(Role.ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTFilter(), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    public static Email getPrincipal() {
        return (Email) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
