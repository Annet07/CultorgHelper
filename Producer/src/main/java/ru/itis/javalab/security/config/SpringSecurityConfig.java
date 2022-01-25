package ru.itis.javalab.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import ru.itis.javalab.security.token.JwtLogoutFilter;
import ru.itis.javalab.security.token.TokenAuthenticationProvider;
import ru.itis.javalab.security.token.AccessTokenFilter;
import ru.itis.javalab.security.token.RefreshTokenFilter;

@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtLogoutFilter jwtLogoutFilter;

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
                .addFilterAt(jwtLogoutFilter, LogoutFilter.class)
                .addFilterAt(accessTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(refreshTokenFilter, AccessTokenFilter.class)
                .authorizeRequests()
                .antMatchers("/file/concertApplication").authenticated()
                .antMatchers("/file/nightSetup").authenticated()
                .antMatchers("/file/concertCostume").authenticated()
                .antMatchers("/file/tehGroup").authenticated()
                .antMatchers("/update").authenticated()
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
