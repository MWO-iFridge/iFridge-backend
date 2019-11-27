package mwo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import mwo.security.CustomEntryPoint;
import mwo.security.CustomFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	@Autowired
    private CustomEntryPoint customEntryPoint;
	
	@Autowired
	private UserDetailsService userDetailsService; //CustomDetailsService

	@Autowired
	private CustomFilter customFilter;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authManager) throws Exception {
		authManager.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

	    httpSecurity.csrf().disable()
	    .authorizeRequests().antMatchers("/authenticate").permitAll().
	    anyRequest().authenticated().and().
	    exceptionHandling().authenticationEntryPoint(customEntryPoint).and().sessionManagement()
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