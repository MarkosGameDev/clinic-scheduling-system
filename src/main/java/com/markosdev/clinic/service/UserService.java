package com.markosdev.clinic.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.markosdev.clinic.dto.CreateUserDTO;
import com.markosdev.clinic.dto.UpdateUserDTO;
import com.markosdev.clinic.entity.User;
import com.markosdev.clinic.exception.EmailAlreadyExistsException;
import com.markosdev.clinic.exception.UserNotFoundException;
import com.markosdev.clinic.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Long createUser(CreateUserDTO createUserDTO) {
		
		if(userRepository.existsByEmail(createUserDTO.email())) {
			throw new EmailAlreadyExistsException("Já existe um usuário cadastrado com esse e-mail.");
		}
		
		var entity = new User(null, 
				createUserDTO.name(), 
				createUserDTO.email(),
				createUserDTO.password(),
				createUserDTO.isAdmin(),
				Instant.now(),
				null);
		
		var userSaved = userRepository.save(entity);
		
		return userSaved.getId();
	}
	
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("Usuário não encontrado.");
		}
		
		return user.get();
	}
	
	public List<User> listUser(){
		return userRepository.findAll();
		
	}
	
	public void updateUserById(Long id, UpdateUserDTO updateUserDTO) {
		var userEntity = userRepository.findById(id);
		
		if(userEntity.isPresent()) {
			var user = userEntity.get();
			
			if(updateUserDTO.name() != null) {
				user.setName(updateUserDTO.name());
			}
			
			if(updateUserDTO.password() != null) {
				user.setPassword(updateUserDTO.password());
			}
			
			userRepository.save(user);
		}
		
	}
	
	public void deleteById(Long id) {
		
		var userExists = userRepository.existsById(id);
		
		if(userExists) {
			userRepository.deleteById(id);
		}
	}
}
