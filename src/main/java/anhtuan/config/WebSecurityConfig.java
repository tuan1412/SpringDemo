package anhtuan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public Md5PasswordEncoder passwordEncoder() {
		return new Md5PasswordEncoder();
	}
	

	@Configuration
	@Order(1)
	public static class AdminSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
		private UserDetailsService userDetailsService;

		@Autowired
		private Md5PasswordEncoder passwordEncoder;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
	          	http.antMatcher("/admin/**")
	          	.authorizeRequests()
	          	.antMatchers("/admin/login")
	          	.permitAll()
	          	.antMatchers("/admin/**")
	          	.access("hasRole('ROLE_ADMIN')")
	          	     
				.and()
		        .formLogin()
		        .loginPage("/admin/login")
		        .loginProcessingUrl("/admin/login")
		        .failureUrl("/admin/login?error=loginError")
		        .defaultSuccessUrl("/admin/user")
		           
		        .and()
		        .exceptionHandling()
		        .accessDeniedPage("/403")
			
				.and()
				.csrf().disable();
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
			auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
		}
	}

	@Configuration
	@Order(2)
	public static class UserSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
		@Autowired
		private UserDetailsService userDetailsService;

		@Autowired
		private Md5PasswordEncoder passwordEncoder;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/login")
				.permitAll()
				.antMatchers("/welcome")
				.access("hasRole('ROLE_MEMBER')")
				
				.and()
				.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.failureUrl("/login?error=loginError")
				.defaultSuccessUrl("/welcome")
				
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				
				.and()
				.exceptionHandling()
				.accessDeniedPage("/403")

				.and()
				.csrf().disable();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
			auth.inMemoryAuthentication().withUser("member").password("123456").roles("MEMBER");
		}	
	}
}
