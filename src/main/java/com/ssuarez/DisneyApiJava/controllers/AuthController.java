package com.ssuarez.DisneyApiJava.controllers;

import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssuarez.DisneyApiJava.dto.JWTAuthResponseDTO;
import com.ssuarez.DisneyApiJava.dto.LoginDTO;
import com.ssuarez.DisneyApiJava.dto.RegisterDTO;
import com.ssuarez.DisneyApiJava.entities.Role;
import com.ssuarez.DisneyApiJava.entities.UserEntity;
import com.ssuarez.DisneyApiJava.repositories.RoleRepository;
import com.ssuarez.DisneyApiJava.repositories.UserRepository;
import com.ssuarez.DisneyApiJava.secutiry.JwtTokenProvider;

@RestController
@RequestMapping("auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;	
	
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@Valid @RequestBody LoginDTO loginDTO){
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Obtener el token del jwtTokenProvider
		String token = jwtTokenProvider.generatedToken(authentication);
		
		return ResponseEntity.ok(new JWTAuthResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO){
		if (userRepository.existsByUsername(registerDTO.getUsername())) {
			return new ResponseEntity<>("El username ya existe", HttpStatus.BAD_REQUEST);
		}
		
		if (userRepository.existsByEmail(registerDTO.getEmail())) {
			return new ResponseEntity<>("El email ya existe", HttpStatus.BAD_REQUEST);
		}
		
		UserEntity user = new UserEntity();
		user.setName(registerDTO.getName());
		user.setUsername(registerDTO.getUsername());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		
		Role roles = roleRepository.findByName("ROLE_USER").get();
		user.setRoles(Collections.singleton(roles));
		
		userRepository.save(user);
		return new ResponseEntity<>("Usuario registrado exitosamente!", HttpStatus.OK);				
	}

}

