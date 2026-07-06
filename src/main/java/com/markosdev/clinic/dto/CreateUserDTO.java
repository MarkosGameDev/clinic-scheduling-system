package com.markosdev.clinic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
		
		@NotBlank(message = "Nome é obrigatório")
		String name, 
		
		@Email(message = "E-mail inválido")
		@NotBlank(message = "E-mail é obrigatório")
		String email,

		@NotBlank(message = "Senha é obrigatória")
		@Size(min = 8, message = "A senha deve ter mais de 8 digitos")
		String password, 
		
		Boolean isAdmin) {

}
