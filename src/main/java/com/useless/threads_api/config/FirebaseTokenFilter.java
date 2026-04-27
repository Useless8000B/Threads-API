package com.useless.threads_api.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirebaseTokenFilter extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
				String header = request.getHeader("Authorization");

				if (header != null && header.startsWith("Bearer ")) {
					String token = header.substring(7);
					try {
						FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
						request.setAttribute("uid", decodedToken.getUid());
						UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
							decodedToken.getUid(),
							null,
							new ArrayList<>()
						);
						SecurityContextHolder.getContext().setAuthentication(auth);
					} catch (Exception e) {}
				}
		filterChain.doFilter(request, response);
	}
}
