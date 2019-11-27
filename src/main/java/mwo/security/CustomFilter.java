package mwo.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import mwo.auth.TokenHelper;
import mwo.service.CustomUserDetailsService;

@Component
public class CustomFilter extends OncePerRequestFilter {

		@Autowired
		private CustomUserDetailsService customUserDetailsService;

		@Autowired
		private TokenHelper tokenHelper;

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
				throws ServletException, IOException {

			String requestToken = request.getHeader("Bearer");

			String username = null;
			String token = null;
		
			if (requestToken != null) {
				try {
					username = tokenHelper.getUsernameFromToken(token);
				} catch (Exception e) {
					//handle no header
				}
			}

			// Once we get the token validate it.
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);

				if (tokenHelper.validateToken(token, userDetails)) {

					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
			chain.doFilter(request, response);
		}

	

}
