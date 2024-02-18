package com.forofica.uce.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class AuthTokenFilter extends OncePerRequestFilter {

	// Filtra las peticiones, filtra los request
	// Si el token es valido deja pasar el request

	@Autowired
	private JwtUtils jwtUtils;

	private static final Logger LOG = LoggerFactory.getLogger(JwtUtils.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		LOG.error(this.jwtUtils.getClass().toString() + "..............................");
		try {
			String token = this.parseJwt(request);
			if (token != null && this.jwtUtils.validateJwtToken(token)) {
				// Como el token es valido le voy a autenticar
				String nombre = this.jwtUtils.getUserNameFromJwtToken(token);

				// Le autenticamos
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(nombre,
						null, new ArrayList<>());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// Seteamos la autenticacion en la sesion
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			LOG.error("No se pudo realizar la autenticacion con el token enviado: {}", e.getMessage());
		}
		
		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String valorCompleto = request.getHeader("Authorization");
		if (StringUtils.hasText(valorCompleto) && valorCompleto.startsWith("Bearer ")) {
			return valorCompleto.substring(7, valorCompleto.length());
		}
		return null;
	}
}
