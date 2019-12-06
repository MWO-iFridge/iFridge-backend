package mwo.configuration;

import mwo.security.CustomEntryPoint;
import mwo.security.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
    private CustomEntryPoint customEntryPoint;

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService; //CustomDetailsService

	@Autowired
	private CustomFilter customFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManager) throws Exception {
		authManager.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

	    httpSecurity.
	    cors().and().
	    csrf().disable()
	    .authorizeRequests()
        .antMatchers("/hidden").authenticated()
        .antMatchers("/create-recipe").authenticated()
        .anyRequest().permitAll().and()
        .exceptionHandling().authenticationEntryPoint(customEntryPoint).and().sessionManagement()
	    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	  
	    httpSecurity.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
  
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
  
}