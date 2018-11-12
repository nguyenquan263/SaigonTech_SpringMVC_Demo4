package vn.edu.saigontech.SpringMVCDemo.configurations;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.google.gson.Gson;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
	public JwtLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		LoginRequest logReq = getLoginRequest(request);
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(logReq.getUsername(),
				logReq.getPassword());
		return getAuthenticationManager().authenticate(authRequest);

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(response, authResult.getName());
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		TokenAuthenticationService.failAuthentication(response, failed.getMessage());
	}

	private LoginRequest getLoginRequest(HttpServletRequest request) {
		BufferedReader reader = null;
		LoginRequest loginRequest = null;
		try {
			reader = request.getReader();
			Gson gson = new Gson();
			loginRequest = gson.fromJson(reader, LoginRequest.class);
		} catch (IOException ex) {
			logger.error(null, ex);
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				logger.error(null, ex);
			}
		}

		if (loginRequest == null) {
			loginRequest = new LoginRequest();
		}
		return loginRequest;
	}

}