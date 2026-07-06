package com.markosdev.clinic.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserDTO(
		
		@NotBlank(message = "Nome é obrigatório")
		String name,
		
		@NotBlank(message = "Senha é obrigatória")
		@Size(min = 8, message = "A senha deve ter mais de 8 digitos")
		String password) {

}
