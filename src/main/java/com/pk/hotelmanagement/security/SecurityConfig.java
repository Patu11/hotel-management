package com.pk.hotelmanagement.security;


import com.pk.hotelmanagement.users.roles.Role;
import com.pk.hotelmanagement.users.vo.Email;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(httpServletRequest -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
                    corsConfiguration.setAllowedMethods(Arrays.asList(
                            HttpMethod.GET.name(),
                            HttpMethod.HEAD.name(),
                            HttpMethod.POST.name(),
                            HttpMethod.PUT.name(),
                            HttpMethod.DELETE.name()));
                    return corsConfiguration;
                }
        ).and()
                .authorizeRequests()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers("/hotel", "/hotel/*").permitAll()  //for testing purposes
                .antMatchers("/rooms", "/rooms/*").permitAll()   //for testing purposes
                .antMatchers("/storages").permitAll()//for testing purposes
                .antMatchers("/photo", "/photo/*").permitAll()//for testing purposes
                .antMatchers("/hello").hasAuthority(Role.USER.name())
                .antMatchers("/reservations", "/reservations/*").hasAuthority(Role.USER.name())
                .antMatchers("/admin", "/admin/**").hasAuthority(Role.ADMIN.name())
                .antMatchers("/employees", "/employees/**").hasAuthority(Role.EMPLOYEE.name())
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
