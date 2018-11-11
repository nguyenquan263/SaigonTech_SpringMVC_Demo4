package vn.edu.saigontech.SpringMVCDemo.configurations;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class accessDeniedEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res,
			AuthenticationException authException) throws IOException, ServletException {


		res.setStatus(HttpStatus.BAD_REQUEST.value());
		res.getWriter().write("Access Denied");
		res.flushBuffer();
		
	}

}