package pro.abacus.webRestProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import pro.abacus.webRestProject.Repositories.UserRepository;
import pro.abacus.webRestProject.Services.WebUserDetailsService;



@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private WebUserDetailsService userDetailsService;
	
	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

	// Override to configure user-details services
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService)
		.passwordEncoder(getPasswordEncoder());
	}

	    //Override to configure how requests are secured by interceptors.
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	        http.csrf().disable();
	        http
	        //demands that all HTTP requests coming into the application be authenticated. 
	        .authorizeRequests()
            .antMatchers("/webService/**")  
            .authenticated()
            .anyRequest()
            .permitAll()
            .and()
            .formLogin()
             .loginPage("/login")
            .permitAll()
            .and()
            .logout()
            .logoutSuccessUrl("/");
	        /*.and()
	        .requiresChannel()
	        .antMatchers("/registration").requiresSecure();*/
	     
	    }
  
	private PasswordEncoder getPasswordEncoder() {
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence charSequence) {
				return charSequence.toString();
			}

			@Override
			public boolean matches(CharSequence charSequence, String s) {
				return true;
			}
		};
	}
	 @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	    }

}
