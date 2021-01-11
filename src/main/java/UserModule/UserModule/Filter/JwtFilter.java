package UserModule.UserModule.Filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import UserModule.UserModule.Service.MyUserDetailsService;
import UserModule.UserModule.Util.JwtUtil;


/**
 * class JwtFilter
 * 
 * @created By Dinesh J
 * @created Date 09/12
 * @description used for filter all request and checks whether request contain the authorization header
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
	
	/**
	 * JwtUtil bean
	 */
	private final JwtUtil jwtUtil;
	
	/**
	 * MyUserDetailsService bean
	 */
	private final MyUserDetailsService myUserDetailsService;
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param jwtUtil
	 * @param myUserDetailsService
	 * @description used for Dependency injection
	 */
	@Autowired
	public JwtFilter(JwtUtil jwtUtil, MyUserDetailsService myUserDetailsService)
	{
		this.jwtUtil = jwtUtil;
		this.myUserDetailsService = myUserDetailsService;
	}
	

	/**
	 * function doFilterInternal
	 * 
	 * @param request,response,filterChain
	 * @param myUserDetailsService
	 * @description used to check each request contains authorization header or not
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");

		String token = null;
		String userName = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(token);
		}

		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);

			if (jwtUtil.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}


}
