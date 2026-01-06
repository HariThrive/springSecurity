package com.springSecurity.springSecurity.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import com.springSecurity.springSecurity.utils.JwtFilter;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http,JwtFilter jwtFilter)throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
		.cors(cors -> cors.configurationSource(corsConfiguration()))
			.authorizeHttpRequests(auth ->auth
			.requestMatchers("/auth/**").permitAll()
//			 .requestMatchers("/product/**").authenticated()
			.anyRequest().authenticated())
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() throws Exception {
		return new BCryptPasswordEncoder();
	}
	
	@Bean // method used for communication b/w front and backend 
	public CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("*"));//orgin website 
		configuration.setAllowedMethods(List.of("GET","POST","PUT"));
		configuration.setAllowedHeaders(List.of("*"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", configuration);
		
		return (CorsConfigurationSource) source;
		
	}
}
