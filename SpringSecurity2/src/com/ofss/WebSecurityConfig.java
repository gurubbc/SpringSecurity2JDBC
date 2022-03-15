package com.ofss;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.builders.*;  
import org.springframework.security.config.annotation.web.builders.HttpSecurity;  
import org.springframework.security.config.annotation.web.configuration.*;  
import org.springframework.security.core.userdetails.User;  
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;  

/** This configuration creates a Servlet Filter known as the springSecurityFilterChain. 
 * It is responsible for protecting the application URLs, validating submit username and password, redirecting to the login form etc. 
 * 
 * @author admin
 *
 */
@Configuration
@EnableWebSecurity  
@ComponentScan("com.ofss")  
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {  

	
	@Autowired
	private DataSource dataSource;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/private")
//		.hasRole("USER")
		.hasAnyRole("USER","ADMIN") // this will permit any user who are in the role of either USER or ADMIN
		.and()
		.httpBasic();
		

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Entering configure method");

		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username,password,enabled from users where username=?")
		.authoritiesByUsernameQuery("select username,role from user_roles where username=?")
		.passwordEncoder(new BCryptPasswordEncoder());

	}
	
	 @Bean
	    public PasswordEncoder passwordEncoder() {
		 System.out.println("Returning BCryptPasswordEncoder object");
	        return new BCryptPasswordEncoder();
	    }

}  