package com.byrEE;

/**
 * author byrEE
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigureAdapter{

	@Bean
	public UserService userService(){
		return new UserService();
	}

	@Bean
	public TokenBasedRememberMeServices rememberMeServices{
		return new TokenBasedRememberMeServices("remember-me-key",userService());
	}

	@Bean
	public PasswordEncoder passwordEncoder{
		return new StandardPasswordEncoder();
	}

	@Override 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.eraseCredentials(true)
			.userDetailsService(userService())
			.passwordEncoder(passwordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.authorizeRequests()
				.antMatchers("/admin/**").authenticated()
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.failureUrl("/login?error=1")
				.loginProcessingUrl("/authenticate")
				.and()
			.logout()
				.logoutUrl("/logout")
				.permitAll()
				.logoutSucessfulUrl("/login?logout")
				.and()
			.rememberMe()
				.rememberMeServices(rememberMeServices())
				.key("remember-me-key");

	}

	@Override
	protected void configure(WebSecurity web) throws Exception{
		web
			.ignoring()
				.antMatchers("/static/**","/resources/**");
	}
}
