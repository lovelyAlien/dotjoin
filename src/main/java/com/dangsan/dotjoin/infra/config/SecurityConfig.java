package com.dangsan.dotjoin.infra.config;

import com.dangsan.dotjoin.infra.jwt.JwtAccessDeniedHandler;
import com.dangsan.dotjoin.infra.jwt.JwtAuthenticationEntryPoint;
import com.dangsan.dotjoin.infra.jwt.JwtSecurityConfig;
import com.dangsan.dotjoin.infra.jwt.TokenProvider;
import com.dangsan.dotjoin.infra.oauth.CustomOAuth2UserService;
import com.dangsan.dotjoin.infra.oauth.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CustomOAuth2UserService oAuth2UserService;
    private final OAuth2SuccessHandler successHandler;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
//                .antMatchers(
//                        "/h2-console/**"
//                        ,"/favicon.ico"
//                        ,"/error"
//                )
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {



        httpSecurity
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf().disable()
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // enable h2-console
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("/api/user/sign-up", "/api/user/login").permitAll()
                .antMatchers("/api/user/**").authenticated()
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                //.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
                .antMatchers("/api/admin/**").access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/");


        httpSecurity.oauth2Login()
                .loginPage("/login") //구글 로그인이 완료된 후 후처리가 필요함.
                .successHandler(successHandler)
                .userInfoEndpoint()
                .userService(oAuth2UserService);

        httpSecurity.apply(new JwtSecurityConfig(tokenProvider));

    }


}
