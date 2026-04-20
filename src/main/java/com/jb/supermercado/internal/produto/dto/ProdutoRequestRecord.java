package com.jb.supermercado.internal.produto.dto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoRequestRecord(
        @NotBlank(message = "O campo nome e obrigatorio")
        String nome,
        String descricao,
        Double preco,
        Integer quantidadeEstoque,
        String status) {
}
