package com.markosdev.clinic.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreatePatientDTO(
		@NotBlank(message = "Nome é obrigatório")
		@Pattern(
			    regexp = "^[A-Za-zÀ-ÿ\\s]+$",
			    message = "O nome deve conter apenas letras.")
		String name,
		
		@Email(message = "E-mail inválido")
		@NotBlank(message = "E-mail é obrigatório")
		String email,
		
		@NotBlank(message = "Telefone é obrigatório")
		@Pattern(
		        regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$",
		        message = "Telefone inválido")
		String phone,
		
		@NotBlank(message = "Endereço é obrigatório")
		String address,
		String notes) {

}
