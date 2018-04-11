package com.shoron.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		  BCryptPasswordEncoder encoder = passwordEncoder();
		auth.inMemoryAuthentication().withUser("shoron").password(encoder.encode("shoron")).roles("USER");
//		auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//		auth.inMemoryAuthentication().withUser("hasan").password("hasan").roles("USER");
	}
//    @Override
//    protected void registerAuthentication(AuthenticationManagerBuilder registry) throws Exception {
//        BCryptPasswordEncoder encoder = passwordEncoder();
//        registry
//                .inMemoryAuthentication()
//                    .passwordEncoder(encoder)
//                    .withUser("user").password(encoder.encode("password")).roles("USER");
//    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/", "/*todo*/**")
				.access("hasRole('USER')").and().formLogin();
	}
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
