package ru.itis.javalab.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.itis.javalab.security.token.AccessTokenFilter;
import ru.itis.javalab.security.token.RefreshTokenFilter;
import ru.itis.javalab.security.token.TokenAuthenticationProvider;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private AccessTokenFilter accessTokenFilter;

    @Autowired
    private RefreshTokenFilter refreshTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .addFilterAt(accessTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(refreshTokenFilter, AccessTokenFilter.class)
                .authorizeRequests()
                .antMatchers("/file/concertApplication").authenticated()
                .antMatchers("/file/nightSetup").hasAnyAuthority()
                .antMatchers("/file/concertCostume").hasAnyAuthority()
                .antMatchers("/file/tehGroup").hasAnyAuthority()
                .antMatchers("/update").hasAnyAuthority()
                .antMatchers("/sign-up").permitAll()
                .antMatchers("/sign-in").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(tokenAuthenticationProvider);
    }


}
