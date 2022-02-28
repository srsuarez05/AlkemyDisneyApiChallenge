package com.ssuarez.DisneyApiJava.secutiry;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssuarez.DisneyApiJava.entities.Role;
import com.ssuarez.DisneyApiJava.entities.UserEntity;
import com.ssuarez.DisneyApiJava.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado con ese username o email: "+ usernameOrEmail));
		
		return new User(userEntity.getEmail(), userEntity.getPassword(), mapperRoles(userEntity.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapperRoles(Set<Role> roles){
		return roles.stream().map(rol -> new SimpleGrantedAuthority(rol.getName())).collect(Collectors.toList());
	}

}
