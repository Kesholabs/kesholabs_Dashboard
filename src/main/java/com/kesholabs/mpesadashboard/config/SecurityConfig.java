package com.kesholabs.mpesadashboard.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/" };

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                .antMatchers("/signup/**","/signin/**","/forgot/**","/reset/**").permitAll()
                .antMatchers(CLASSPATH_RESOURCE_LOCATIONS).permitAll()
                .antMatchers("/admin/**", "**/admin/**").hasRole("ADMIN")

                .and()
                .formLogin()
                .loginPage("/signin/**").permitAll()
                .failureUrl("/signin/admin?error=loginError")
                .defaultSuccessUrl("/profile/admin")
                .usernameParameter("userID")
                .passwordParameter("password")

                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("**/logout"))
                .logoutSuccessUrl("/signin/**").permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")

                .and()
                .exceptionHandling()
                .accessDeniedPage("/401")

                .and()
                .csrf().disable();

        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(2)
                .expiredUrl("/session/admin?timeout=adminTimeout");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(CLASSPATH_RESOURCE_LOCATIONS);
    }
}
