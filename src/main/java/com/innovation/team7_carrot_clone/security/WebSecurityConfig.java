package com.innovation.team7_carrot_clone.security;

import com.innovation.team7_carrot_clone.jwt.JwtAuthenticationFilter;
import com.innovation.team7_carrot_clone.jwt.JwtTokenProvider;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

//import com.innovation.team7_carrot_clone.jwt.JwtTokenProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//@Configuration
//@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    public void configure(WebSecurity web) {
//        web.ignoring().requestMatchers(new RequestMatcher[]{PathRequest.toStaticResources().atCommonLocations()});
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//// 어떤 요청이든 '인증'
//                .anyRequest().authenticated()
//                .and()
//// 로그인 기능 허용
//                .formLogin()
//                .defaultSuccessUrl("/")
//                .permitAll()
//                .and()
//// 로그아웃 기능 허용
//                .logout()
//                .permitAll();
//    }
//
//    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
//        this.jwtTokenProvider = jwtTokenProvider;
//    }
//}
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public void configure(WebSecurity web) {
//        web.ignoring().requestMatchers(new RequestMatcher[]{PathRequest.toStaticResources().atCommonLocations()});
        web.ignoring().requestMatchers(new RequestMatcher[]{PathRequest.toStaticResources().atCommonLocations()});
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        ((HttpSecurity) ((HttpSecurity) ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
                ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
                        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
                                ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
                                        ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)
                                                http.authorizeRequests().antMatchers(new String[]{"/user/**"})).permitAll()
                                                .antMatchers(new String[]{"**"})).permitAll()
                                        .antMatchers(new String[]{"/"})).permitAll()
                                .antMatchers(HttpMethod.GET, new String[]{"/api/posts"}))
                        .permitAll().anyRequest()).authenticated()
                .and())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and())
                .addFilterBefore(new JwtAuthenticationFilter(this.jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
    }

    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
}
