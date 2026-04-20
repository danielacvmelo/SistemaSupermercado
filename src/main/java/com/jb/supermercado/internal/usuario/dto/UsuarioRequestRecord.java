package com.jb.supermercado.internal.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioRequestRecord(
        @NotBlank(message = "O campo nome e obrigatorio")
        String nome,
        @NotBlank(message = "O campo email e obrigatorio")
        @Email(message = "O email deve ser valido")
        String email,
        @NotBlank(message = "O campo senha e obrigatorio")
        String senha,
        @NotBlank(message = "O campo status e obrigatorio")
        String status
) {
}

