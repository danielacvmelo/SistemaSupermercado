package com.jb.supermercado.internal.produto.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestRecord(
        @NotBlank(message = "O campo nome e obrigatorio")
        String nome,
        @NotBlank(message = "O campo descricao e obrigatorio")
        String descricao,
        @NotNull(message = "O campo preco e obrigatorio")
        @DecimalMin(value = "0.01", message = "O preco deve ser maior que zero")
        Double preco,
        @NotNull(message = "O campo quantidadeEstoque e obrigatorio")
        @Min(value = 0, message = "A quantidade em estoque nao pode ser negativa")
        Integer quantidadeEstoque,
        @NotBlank(message = "O campo status e obrigatorio")
        String status) {
}
