package com.bookstore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bookstore.security.SecurityUtility;
import com.bookstore.service.UserSecurityService;


// The @EnableWebSecurity is a marker annotation. It allows Spring to find (
// it's a @Configuration and, therefore, @Component) 
// and automatically apply the class to the global WebSecurity.

// @EnableGlobalMethodSecurity Enables Spring Security global method security 
// similar to the <global-method-security> xml support.
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private Environment environment;

	@Autowired
	private UserSecurityService userSecurityService;
	
	private BCryptPasswordEncoder passwordEncoder() {
		return SecurityUtility.passwordEncoder();
	}
	
	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/image/**",
			"/",
			"/myAccount"
	};
	
	
	//http.antMatcher() tells Spring to only configure HttpSecurity if the path matches this pattern.
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.
				authorizeRequests().
				//antMatchers("/**")
				antMatchers(PUBLIC_MATCHERS).
				permitAll().anyRequest().authenticated();
		
		httpSecurity
				.csrf().disable().cors().disable()
				.formLogin().failureUrl("/login?error").defaultSuccessUrl("/")
				.loginPage("/login").permitAll()
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/?logout").deleteCookies("remember-me").permitAll()
				.and()
				.rememberMe();
	}
	
	
	/**
	 * {@link SecurityBuilder} used to create an {@link AuthenticationManager}. Allows for
	 * easily building in memory authentication, LDAP authentication, JDBC based
	 * authentication, adding {@link UserDetailsService}, and adding
	 * {@link AuthenticationProvider}'s.
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
		authenticationManagerBuilder
			.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
	}
}









